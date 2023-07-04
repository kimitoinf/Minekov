package kimit.minekov.Market;

import kimit.minekov.Minekov;
import kimit.minekov.PlayerInfo.PlayerInfo;
import kimit.minekov.PlayerInfo.PlayerInfoCommand;
import kimit.minekov.util.ConfigFile.ConfigFile;
import kimit.minekov.util.ConfigFile.ConfigFileProvider;
import kimit.minekov.util.InventoryPage.InventoryPage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Market extends ConfigFileProvider
{
	public static final String NAME = "Market";
	private static final String COUNT = "Count";
	private final InventoryPage MARKET;
	public static final int FEE = 10;
	public static final int LEFTCLICK = 1;
	public static final int SHIFTLEFTCLICK = 10;

	public Market(String filename)
	{
		super(filename);
		Minekov.INVENTORYPAGEMANAGER.Register(NAME, NAME);
		MARKET = Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(NAME);

		for (int loop = 0; loop != CONFIG.getInt(COUNT); loop++)
			MARKET.AddItem(CONFIG.getItemStack(Integer.toString(loop)));
	}

	public void Open(Player player)
	{
		MARKET.OpenInventory(player);
	}

	public void SellItem(Player player, ItemStack item, long price)
	{
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("판매자 : " + player.getName());
		lore.add("개당 가격 : " + Long.toString(price));
		lore.add("좌클릭으로 " + Integer.toString(LEFTCLICK) + "개 구매");
		lore.add("SHIFT + 좌클릭으로 " + Integer.toString(SHIFTLEFTCLICK) + "개 구매");
		meta.setLore(lore);
		item.setItemMeta(meta);
		MARKET.AddItem(item);
		long fee = (long)Math.ceil((double)(price * item.getAmount()) / FEE);
		Minekov.PLAYERS.get(player.getUniqueId()).addCash(-fee);
		player.sendMessage("수수료 " + Long.toString(fee) + "원이 지불되었습니다.");
		player.sendMessage("아이템 등록이 완료되었습니다.");
	}

	public void PurchaseItem(Player player, int page, int index, long price, int count)
	{
		ItemStack item = Minekov.MARKET.MARKET.getInventories().get(page).getItem(index).clone();
		ItemMeta meta = item.getItemMeta();
		List<String> lore = meta.getLore();
		String name = lore.get(0).split(" ")[2];
		Player seller = Bukkit.getPlayer(name);
		lore.clear();
		meta.setLore(lore);
		item.setItemMeta(meta);
		if (item.getAmount() > count)
			item.setAmount(count);
		count = item.getAmount();
		long total = count * price;
		Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(player.getUniqueId().toString()).AddItem(item);
		Minekov.PLAYERS.get(player.getUniqueId()).addCash(-total);
		String message = "아이템 " + item.getType().toString() + " " + Integer.toString(count) + "개를 " + Long.toString(total) + "원에 구매하였습니다.";
		player.sendMessage(message);
		player.sendMessage("구매한 아이템은 /" + PlayerInfoCommand.RECEIVE + " 명령어로 받을 수 있습니다.");
		Minekov.MARKET.MARKET.RemoveItem(page, index, count);
		if (seller != null)
		{
			Minekov.PLAYERS.get(seller.getUniqueId()).addCash(total);
			seller.sendMessage("플레이어 " + player.getName() + "님이 " + message);
		}
		else
		{
			ConfigFile config = new ConfigFile(Minekov.PLAYERSFOLDER + File.separator + Bukkit.getOfflinePlayer(name).getUniqueId().toString() + ".yml");
			config.Open();
			config.getConfig().set(PlayerInfo.CASHCONFIG, config.getConfig().getLong(PlayerInfo.CASHCONFIG) + total);
			config.Close();
		}
	}

	@Override
	public void Save()
	{
		ArrayList<ItemStack> items = MARKET.getItems();
		CONFIG.set(COUNT, items.size());
		for (int loop = 0; loop != items.size(); loop++)
			CONFIG.set(Integer.toString(loop), items.get(loop));
		super.Save();
	}
}
