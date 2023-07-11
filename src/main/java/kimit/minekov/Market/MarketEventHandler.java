package kimit.minekov.Market;

import kimit.minekov.Commands;
import kimit.minekov.Minekov;
import kimit.minekov.Util.InventoryPage.InventoryPage;
import kimit.minekov.Util.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MarketEventHandler implements Listener
{
	public static final String CANCELSELL = "취소";

	@EventHandler
	public void ClickInventory(InventoryClickEvent e)
	{
		if (e.getClickedInventory() != null && e.getView().getTitle().equals(Market.NAME) && e.getClickedInventory().equals(e.getView().getBottomInventory()))
			e.setCancelled(true);
		else if (e.getView().getTitle().equals(Market.NAME) && Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(e.getView().getTitle()) != null && e.getClickedInventory() != null && e.getClickedInventory().equals(e.getView().getTopInventory()) && e.getSlot() <= InventoryPage.LIMITPOS && e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR)
		{
			e.setCancelled(true);
			if (e.isLeftClick())
			{
				final int page = Integer.parseInt(e.getClickedInventory().getItem(InventoryPage.CURRENTPOS).getItemMeta().getLore().get(0).split(" ")[0]) - 1;
				final int index = e.getSlot();
				ItemMeta meta = e.getCurrentItem().getItemMeta();
				List<String> lore = meta.getLore();
				final long price = Long.parseLong(lore.get(1).split(" ")[3]);
				Player player = (Player) e.getWhoClicked();
				if (price > Minekov.PLAYERS.get(player.getUniqueId()).getGold())
					player.sendMessage("구입하고자 하는 물품의 가격이 소지한 골드보다 비싸 구매할 수 없습니다.");
				else if (!e.isShiftClick())
					Minekov.MARKET.PurchaseItem((Player) e.getWhoClicked(), page, index, price, Market.LEFTCLICK);
				else
					Minekov.MARKET.PurchaseItem((Player) e.getWhoClicked(), page, index, price, Market.SHIFTLEFTCLICK);

			}
		}
	}

	@EventHandler
	public void CloseInventory(InventoryCloseEvent e)
	{
		if (e.getView().getTitle().equals(Market.SELLINVENTORY))
		{
			Player player = (Player)e.getPlayer();
			int count = 0;
			ArrayList<ItemStack> items = new ArrayList<ItemStack>();
			for (ItemStack loop : e.getView().getTopInventory().getContents())
			{
				if (loop != null && loop.getType() != Material.AIR)
				{
					count++;
					items.add(loop);
				}
			}
			if (count == 0)
				player.sendMessage("판매하고자 하는 아이템을 인벤토리 한 칸에 올려놔 주십시오.");
			else if (count > 1)
			{
				player.sendMessage("판매하고자 하는 아이템을 인벤토리 한 칸에 올려놔 주십시오.");
				player.sendMessage("이전에 올려놓은 아이템은 /" + Commands.COMMANDS[9] + " 명령어로 다시 받을 수 있습니다.");
				for (ItemStack loop : items)
					Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(player.getUniqueId().toString()).AddItem(loop);
			}
			else
			{
				player.sendMessage("아이템 개당 판매 가격을 입력하여 주십시오.");
				player.sendMessage("수수료는 총 금액의 " + Market.FEE + "%가 부과됩니다.");
				player.sendMessage("등록을 취소하려면 " + CANCELSELL + " 를 입력하십시오.");
				Minekov.PLAYERS.get(player.getUniqueId()).MarketSell = items;
				Minekov.PLAYERS.get(player.getUniqueId()).OnMarketSell = true;
			}
		}
	}

	@EventHandler
	public void PlayerChat(AsyncPlayerChatEvent e)
	{
		if (Minekov.PLAYERS.get(e.getPlayer().getUniqueId()).OnMarketSell)
		{
			Player player = e.getPlayer();
			if (e.getMessage().equals(CANCELSELL))
				CancelSell(player);
			else if (!Util.IsNumberic(e.getMessage()) || Long.parseLong(e.getMessage()) < 0)
				player.sendMessage("0 이상의 정수로 입력하여 주십시오.");
			else if ((long)Math.ceil((double)Long.parseLong(e.getMessage()) / Market.FEE) > Minekov.PLAYERS.get(player.getUniqueId()).getGold())
			{
				player.sendMessage("수수료를 지불할 골드가 부족합니다.");
				CancelSell(player);
			}
			else
			{
				Minekov.MARKET.SellItem(player, Minekov.PLAYERS.get(player.getUniqueId()).MarketSell.get(0), Long.parseLong(e.getMessage()));
				Minekov.PLAYERS.get(player.getUniqueId()).OnMarketSell = false;
			}
			e.setCancelled(true);
		}
	}

	private void CancelSell(Player player)
	{
		player.sendMessage("시장 판매 등록을 취소했습니다.");
		player.sendMessage("판매하려던 아이템은 /" + Commands.COMMANDS[9] + " 명령어로 다시 받을 수 있습니다.");
		for (ItemStack loop : Minekov.PLAYERS.get(player.getUniqueId()).MarketSell)
			Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(player.getUniqueId().toString()).AddItem(loop);
		Minekov.PLAYERS.get(player.getUniqueId()).OnMarketSell = false;
		Minekov.PLAYERS.get(player.getUniqueId()).UpdateBoard();
	}
}
