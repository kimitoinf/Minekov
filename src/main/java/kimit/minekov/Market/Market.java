package kimit.minekov.Market;

import de.tr7zw.nbtapi.NBTItem;
import kimit.minekov.Commands;
import kimit.minekov.Minekov;
import kimit.minekov.PlayerInfo.PlayerInfo;
import kimit.minekov.Util.ConfigFile.ConfigFile;
import kimit.minekov.Util.ConfigFile.ConfigFileProvider;
import kimit.minekov.Util.InventoryPage.InventoryPage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Market extends ConfigFileProvider
{
	public static final String NAME = "Market";
	private static final String COUNT = "Count";
	private final InventoryPage MARKET;
	public static final int FEE = 10;
	public static final int LEFTCLICK = 1;
	public static final int SHIFTLEFTCLICK = 10;
	public static final String SELLINVENTORY = "Sell";
	public static final String SELLER = "Seller";

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
		lore.add("개당 가격 : " + price);
		lore.add("좌클릭으로 " + LEFTCLICK + "개 구매");
		lore.add("SHIFT + 좌클릭으로 " + SHIFTLEFTCLICK + "개 구매");
		if (meta.hasLore()) lore.addAll(meta.getLore());
		meta.setLore(lore);
		item.setItemMeta(meta);
		NBTItem nbt = new NBTItem(item);
		nbt.setUUID(SELLER, player.getUniqueId());
		nbt.applyNBT(item);
		MARKET.AddItem(item);
		long fee = (long)Math.ceil((double)(price * item.getAmount()) / FEE);
		Minekov.PLAYERS.get(player.getUniqueId()).addGold(-fee);
		player.sendMessage("수수료 " + fee + "골드가 지불되었습니다.");
		player.sendMessage("아이템 등록이 완료되었습니다.");
		Minekov.PLAYERS.get(player.getUniqueId()).UpdateBoard();
	}

	public void PurchaseItem(Player player, int page, int index, long price, int count)
	{
		ItemStack item = Minekov.MARKET.MARKET.getInventories().get(page).getItem(index).clone();
		ItemMeta meta = item.getItemMeta();
		List<String> lore = meta.getLore();
		meta.setLore(lore.subList(4, lore.size()));
		item.setItemMeta(meta);
		if (item.getAmount() > count)
			item.setAmount(count);
		count = item.getAmount();
		long total = count * price;
		NBTItem nbt = new NBTItem(item);
		UUID seller = nbt.getUUID(SELLER);
		nbt.removeKey(SELLER);
		nbt.applyNBT(item);
		Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(player.getUniqueId().toString()).AddItem(item);
		Minekov.PLAYERS.get(player.getUniqueId()).addGold(-total);
		String message = "아이템 " + item.getType() + " " + count + "개를 " + total + "골드에 구매하였습니다.";
		player.sendMessage(message);
		player.sendMessage("구매한 아이템은 /" + Commands.COMMANDS[9] + " 명령어로 받을 수 있습니다.");
		Minekov.MARKET.MARKET.RemoveItem(page, index, count);
		Minekov.PLAYERS.get(player.getUniqueId()).UpdateBoard();

		if (Minekov.PLAYERS.get(seller) != null)
		{
			Player sellerPlayer = Bukkit.getPlayer(seller);
			Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(seller.toString()).AddItem(PlayerInfo.GoldItem(total));
			sellerPlayer.sendMessage("플레이어 " + player.getName() + "님이 " + message);
			sellerPlayer.sendMessage("판매 골드은 /" + Commands.COMMANDS[9] + " 명령어로 받을 수 있습니다.");
			Minekov.PLAYERS.get(sellerPlayer.getUniqueId()).UpdateBoard();
		}
		else
		{
			ConfigFile config = new ConfigFile(Minekov.PLAYERSFOLDER + File.separator + seller.toString() + ".yml");
			config.Open();
			int receives = config.getConfig().getInt(PlayerInfo.RECEIVECOUNT);
			config.getConfig().set(PlayerInfo.RECEIVE + "." + receives, PlayerInfo.GoldItem(total));
			config.getConfig().set(PlayerInfo.RECEIVECOUNT, receives + 1);
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
