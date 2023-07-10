package kimit.minekov.PlayerInfo;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import kimit.minekov.Commands;
import kimit.minekov.Minekov;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInfoEventHandler implements Listener
{
	@EventHandler
	public void OnJoin(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		Minekov.PLAYERS.put(player.getUniqueId(), new PlayerInfo(player.getUniqueId()));
		int count = Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(player.getUniqueId().toString()).getItems().size();
		if (count > 0)
		{
			player.sendMessage(ChatColor.BOLD + "수신함에 받지 않은 아이템이 " + count + "종류 있습니다.");
			player.sendMessage(ChatColor.BOLD + "/" + Commands.COMMANDS[2] + " 명령어로 받을 수 있습니다.");
		}
		PlayerInfo playerInfo = Minekov.PLAYERS.get(player.getUniqueId());
		if (playerInfo.isInRaid())
			playerInfo.RAID.Resume();
	}

	@EventHandler
	public void OnQuit(PlayerQuitEvent e)
	{
		if (Minekov.PLAYERS.get(e.getPlayer().getUniqueId()).isInRaid())
			Minekov.PLAYERS.get(e.getPlayer().getUniqueId()).RAID.Pause();
		Minekov.PLAYERS.get(e.getPlayer().getUniqueId()).Save();
		Minekov.PLAYERS.remove(e.getPlayer().getUniqueId());
	}

	@EventHandler
	public void ClickInventory(InventoryClickEvent e)
	{
		if (e.getClickedInventory() != null && e.getView().getTitle().equals(PlayerInfo.RECEIVE) && e.getClickedInventory().equals(e.getView().getBottomInventory()))
			e.setCancelled(true);
	}

	@EventHandler
	public void CloseInventory(InventoryCloseEvent e)
	{
		if (e.getView().getTitle().equals(PlayerInfo.RECEIVE))
			Minekov.PLAYERS.get(e.getPlayer().getUniqueId()).UpdateBoard();
	}

	@EventHandler
	public void UseGold(PlayerInteractEvent e)
	{
		if (e.hasItem() && e.getPlayer().getInventory().getItemInMainHand().getType() == PlayerInfo.GOLDMATERIAL && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK))
		{
			ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
			NBTItem nbt = new NBTItem(item);
			if (nbt.hasTag(PlayerInfo.GOLDCONFIG))
			{
				long gold = nbt.getLong(PlayerInfo.GOLDCONFIG);
				Minekov.PLAYERS.get(e.getPlayer().getUniqueId()).addGold(gold);
				e.getPlayer().sendMessage(ChatColor.GOLD + Long.toString(gold) + " 골드를 획득했습니다.");
				item.setAmount(item.getAmount() - 1);
				Minekov.PLAYERS.get(e.getPlayer().getUniqueId()).UpdateBoard();
			}
		}
	}
}
