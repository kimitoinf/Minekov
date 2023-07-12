package kimit.minekov.Menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu
{
	private final Inventory MENU;
	public static final String NAME = "Menu";
	private static final String[] NAMES = {"RAID", "SHOP", "MARKET", "SELL", "RECEIVE"};
	private static final Material[] MATERIALS = {Material.DIAMOND_SWORD, Material.BOOK, Material.PAPER, Material.DIRT, Material.CHEST};

	public Menu()
	{
		MENU = Bukkit.createInventory(null, 9, NAME);

		for (int loop = 0; loop != NAMES.length; loop++)
		{
			ItemStack item = new ItemStack(MATERIALS[loop]);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(NAMES[loop]);
			item.setItemMeta(meta);
			MENU.setItem(loop, item);
		}
	}

	public Inventory getMenu()
	{
		return MENU;
	}
}
