package kimit.minekov.Shop;

import kimit.minekov.Minekov;
import kimit.minekov.Raid.RaidConfig;
import kimit.minekov.Util.ConfigFile.ConfigFileProvider;
import kimit.minekov.Util.InventoryPage.InventoryPage;
import kimit.minekov.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.HashMap;

public class Shop extends ConfigFileProvider
{
	public static final String NAME = "Shop";
	public static final String[] SHOP_NAME = {"Arthur", "화랑", "Issac", "Ἥφαιστος", "Iðunn", "Ranni", "Le Corbusier", "Patches"};
	private static final Material[] SHOP_MATERIAL = {Material.DIAMOND_SWORD, Material.ARROW, Material.DIAMOND_PICKAXE, Material.DIAMOND_CHESTPLATE, Material.APPLE, Material.POTION, Material.STONE_BRICKS, Material.EMERALD};
	public static final int[] SHOP_POS = new int[]{10, 12, 14, 16, 28, 30, 32, 34};
	public static final int CLICK = 1;
	public static final int SHIFT_CLICK = 10;
	private Inventory SHOP;
	private final ArrayList<InventoryPage> SHOP_PAGE = new ArrayList<>();

	public static final Material[][] SHOP_ITEM = {
			{Material.WOODEN_SWORD, Material.GOLDEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD},
			{Material.BOW, Material.CROSSBOW, Material.TRIDENT, Material.SHIELD},
			{Material.WOODEN_AXE, Material.WOODEN_SHOVEL, Material.WOODEN_PICKAXE, Material.GOLDEN_AXE, Material.GOLDEN_SHOVEL, Material.GOLDEN_PICKAXE, Material.STONE_AXE, Material.GOLDEN_SHOVEL, Material.GOLDEN_PICKAXE, Material.IRON_AXE, Material.IRON_SHOVEL, Material.IRON_PICKAXE},
			{Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS},
			{Material.PUFFERFISH, Material.DRIED_KELP, Material.BEETROOT, Material.SPIDER_EYE, Material.TROPICAL_FISH, Material.POISONOUS_POTATO, Material.COOKIE, Material.GLOW_BERRIES, Material.MELON_SLICE, Material.SWEET_BERRIES},
			{Material.POTION},
			{},
			{Material.STICK, Material.INK_SAC, Material.FEATHER, Material.FLINT, Material.STRING, Material.BONE, Material.SNOWBALL, Material.TORCH, Material.GUNPOWDER},
	};
	public static final double[][] ITEM_STAT = {
			{4, 4, 5, 6},
			{9, 9, 9, 40},
			{7, 2.5, 2, 7, 2.5, 2, 9, 3.5, 3, 9, 4.5, 4},
			{1, 3, 2, 1, 2, 5, 3, 1, 2, 6, 5, 2}
	};
	public final HashMap<Material, Integer> ITEM_PRICES = new HashMap<>(){
		{
			put(Material.PUFFERFISH, 35);
			put(Material.DRIED_KELP, 50);
			put(Material.BEETROOT, 50);
			put(Material.SPIDER_EYE, 35);
			put(Material.TROPICAL_FISH, 50);
			put(Material.POISONOUS_POTATO, 35);
			put(Material.COOKIE, 50);
			put(Material.GLOW_BERRIES, 50);
			put(Material.MELON_SLICE, 50);
			put(Material.SWEET_BERRIES, 50);
			put(Material.ROTTEN_FLESH, 50);
			put(Material.POTATO, 75);
			put(Material.CHICKEN, 80);
			put(Material.COD, 80);
			put(Material.MUTTON, 80);
			put(Material.SALMON, 80);
			put(Material.COOKED_CHICKEN, 150);
			put(Material.COOKED_COD, 150);
			put(Material.COOKED_SALMON, 150);
			put(Material.COOKED_MUTTON, 150);
			put(Material.BAKED_POTATO, 150);
			put(Material.GOLDEN_APPLE, 400);
			put(Material.ENCHANTED_GOLDEN_APPLE, 800);

			put(Material.STICK, 5);
			put(Material.INK_SAC, 5);
			put(Material.FEATHER, 10);
			put(Material.FLINT, 10);
			put(Material.STRING, 10);
			put(Material.BONE, 5);
			put(Material.SNOWBALL, 5);
			put(Material.TORCH, 20);
			put(Material.GUNPOWDER, 10);
			put(Material.COPPER_INGOT, 10);
			put(Material.GOLD_NUGGET, 35);
			put(Material.COAL, 30);
			put(Material.LEATHER, 50);
			put(Material.ARROW, 35);
			put(Material.PAPER, 30);
			put(Material.EXPERIENCE_BOTTLE, 90);
			put(Material.GOLD_INGOT, 300);
			put(Material.IRON_INGOT, 300);
			put(Material.EMERALD, 500);
			put(Material.ENDER_PEARL, 300);
			put(Material.LAPIS_LAZULI, 300);
			put(Material.ZOMBIE_HEAD, 1);
			put(Material.DIAMOND, 500);
			put(Material.OBSIDIAN, 500);
			put(Material.BLAZE_POWDER, 1000);
		}
	};
	public static final String DOT = ".";
	public static final String SELL_RATE = "SellRate";
	public final double V_SELL_RATE;
	public static final String PRICE_BONUS = "PriceBonus";
	public static final String[] MATERIAL_BONUS = {"IRON", "DIAMOND", "NETHERITE"};
	public final double[] V_MATERIAL_BONUS = new double[MATERIAL_BONUS.length];
	public final double PRICE_COEFFICIENT;
	public static final Material CRITERIA_MATERIAL = Material.WOODEN_SWORD;

	public Shop(String filename)
	{
		super(filename);

		V_SELL_RATE = CONFIG.getDouble(SELL_RATE);
		for (int loop = 0; loop != MATERIAL_BONUS.length; loop++)
			V_MATERIAL_BONUS[loop] = CONFIG.getDouble(PRICE_BONUS + DOT + MATERIAL_BONUS[loop]);

		PRICE_COEFFICIENT = 10 / GetValue(CRITERIA_MATERIAL, Minekov.RAIDCONFIG.V_DURABILITY_WEAPON_MATERIAL[Util.IsContain(CRITERIA_MATERIAL.name(), RaidConfig.WEAPON_MATERIAL)], ITEM_STAT[0][0]);
		SHOP = Bukkit.createInventory(null, 45, NAME);
		for (int loop = 0; loop != SHOP_NAME.length; loop++)
		{
			SHOP_PAGE.add(new InventoryPage(SHOP_NAME[loop]));
			ItemStack item = new ItemStack(SHOP_MATERIAL[loop]);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(SHOP_NAME[loop]);
			item.setItemMeta(meta);
			SHOP.setItem(SHOP_POS[loop], item);

			for (int loop2 = 0; loop2 != SHOP_ITEM[loop].length; loop2++)
			{
				Material material = SHOP_ITEM[loop][loop2];
				item = new ItemStack(material);
				meta = item.getItemMeta();
				int price = 0;

				switch (loop)
				{
					case 0:
					case 2:
						price = (int)Math.ceil(PRICE_COEFFICIENT * GetValue(material, Minekov.RAIDCONFIG.V_DURABILITY_WEAPON_MATERIAL[Util.IsContain(material.name(), RaidConfig.WEAPON_MATERIAL)], ITEM_STAT[loop][loop2]));
						break;
					case 1:
						price = (int)Math.ceil(PRICE_COEFFICIENT * GetValue(material, Minekov.RAIDCONFIG.V_DURABILITY_PROJECTILE_MATERIAL[Util.IsContain(material.name(), RaidConfig.PROJECTILE_MATERIAL)], ITEM_STAT[loop][loop2]));
						break;
					case 3:
						price = (int)Math.ceil(PRICE_COEFFICIENT * GetValue(material, Minekov.RAIDCONFIG.V_DURABILITY_ARMOR_MATERIAL[Util.IsContain(material.name(), RaidConfig.ARMOR_MATERIAL)], ITEM_STAT[loop][loop2]));
						break;
					case 5:
						PotionMeta potion = (PotionMeta)meta;
						potion.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
						item.setItemMeta(potion);
						price = 80;
						break; // need to generalize
					case 6:
						break;
					default:
						price = ITEM_PRICES.get(material);
				}
				ITEM_PRICES.put(material, price);
				ArrayList<String> lore = new ArrayList<>();
				lore.add("개당 가격 : " + price);
				lore.add("좌클릭으로 " + CLICK + "개 구매");
				lore.add("SHIFT + 좌클릭으로 " + SHIFT_CLICK + "개 구매");
				lore.add("우클릭으로 " + CLICK + "개 판매");
				lore.add("SHIFT + 우클릭으로 모두 판매");
				meta.setLore(lore);
				item.setItemMeta(meta);
				SHOP_PAGE.get(loop).AddItem(item);
			}
		}
	}

	public double GetValue(Material material, double[] durability, double stat)
	{
		double value = 0;
		double sum = 0;
		for (int loop = 0; loop != durability.length; loop++)
		{
			value += durability[loop] * Minekov.RAIDCONFIG.V_ITEM_RANK[loop];
			if (durability[loop] > 0)
				sum += Minekov.RAIDCONFIG.V_ITEM_RANK[loop];
		}
		int index = Util.IsContain(material.name(), MATERIAL_BONUS);
		if (index != -1) value = value / sum * material.getMaxDurability() * stat * V_MATERIAL_BONUS[index];
		else value = value / sum * material.getMaxDurability() * stat;
		return value;
	}

	public Inventory getShop()
	{
		return SHOP;
	}

	public ArrayList<InventoryPage> getShopPage()
	{
		return SHOP_PAGE;
	}

	@Override
	public void Save()
	{
		CONFIG.set(SELL_RATE, V_SELL_RATE);
		for (int loop = 0; loop != MATERIAL_BONUS.length; loop++)
			CONFIG.set(PRICE_BONUS + DOT + MATERIAL_BONUS[loop], V_MATERIAL_BONUS[loop]);

		super.Save();
	}
}