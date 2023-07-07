package kimit.minekov.Raid;

import kimit.minekov.Util.ConfigFile.ConfigFileProvider;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class RaidConfig extends ConfigFileProvider
{
	public static final String MAX = "Max";
	public static final String MIN = "Min";
	public static final String DOT = ".";
	public static final String RAID_TIME = "RaidTime";
	public final int V_RAID_TIME;
	public static final String[] RANK = { "Normal", "Rare", "Epic", "Legendary", "Unique" };
	// public static final ChatColor[] RANK_COLOR = { ChatColor.GRAY, ChatColor.BLUE, ChatColor.LIGHT_PURPLE, ChatColor.GOLD, ChatColor.DARK_RED };
	public static final String ITEM = "Item";
	public final double[] V_ITEM_RANK = new double[RANK.length];
	public static final String ENCHANTMENT = "Enchantment";
	public final double[][] V_ENCHANTMENT = new double[RANK.length][RANK.length];
	public static final String ENCHANTMENTS_OF_ITEM = "EnchantmentsOfItem";
	public final int MAX_ENCHANTMENTS_OF_ITEM = 5;
	public static final int MIN_ENCHANTMENT_RANK = 2;
	public final double[][] V_ENCHANTMENTS_OF_ITEM = new double[RANK.length][MAX_ENCHANTMENTS_OF_ITEM + 1];
	public static final String WEAPON = "Weapon";
	public static final String ARMOR = "Armor";
	public static final String PROJECTILE = "Projectile";
	public static final String[] WEAPON_MATERIAL = { "WOODEN", "GOLDEN", "STONE", "IRON", "DIAMOND", "NETHERITE" };
	public static final String[] ARMOR_MATERIAL = { "LEATHER", "GOLDEN", "IRON", "DIAMOND", "NETHERITE" };
	public static final String[] PROJECTILE_MATERIAL = { Material.BOW.name(), Material.CROSSBOW.name(), Material.TRIDENT.name(), Material.SHIELD.name() };
	public final double[][] V_DURABILITY_WEAPON_MATERIAL = new double[WEAPON_MATERIAL.length][RANK.length];
	public final double[][] V_DURABILITY_ARMOR_MATERIAL = new double[ARMOR_MATERIAL.length][RANK.length];
	public final double[][] V_DURABILITY_PROJECTILE_MATERIAL = new double[PROJECTILE_MATERIAL.length][RANK.length];

	public RaidConfig(String filename)
	{
		super(filename);

		V_RAID_TIME = CONFIG.getInt(RAID_TIME);
		for (int loop = 0; loop != RANK.length; loop++)
			V_ITEM_RANK[loop] = CONFIG.getDouble(ITEM + DOT + RANK[loop]);

		for (int loop = MIN_ENCHANTMENT_RANK; loop != RANK.length; loop++)
			for (int loop2 = 0; loop2 != RANK.length; loop2++)
				V_ENCHANTMENT[loop][loop2] = CONFIG.getDouble(ENCHANTMENT + DOT + RANK[loop] + DOT + RANK[loop2]);

		for (int loop = MIN_ENCHANTMENT_RANK; loop != RANK.length; loop++)
			for (int loop2 = 0; loop2 <= MAX_ENCHANTMENTS_OF_ITEM; loop2++)
				V_ENCHANTMENTS_OF_ITEM[loop][loop2] = CONFIG.getDouble(ENCHANTMENTS_OF_ITEM + DOT + RANK[loop] + DOT + loop2);

		for (int loop = 0; loop != WEAPON_MATERIAL.length; loop++)
			for (int loop2 = 0; loop2 != RANK.length; loop2++)
				V_DURABILITY_WEAPON_MATERIAL[loop][loop2] = CONFIG.getDouble(WEAPON + DOT + WEAPON_MATERIAL[loop] + DOT + RANK[loop2]);

		for (int loop = 0; loop != ARMOR_MATERIAL.length; loop++)
			for (int loop2 = 0; loop2 != RANK.length; loop2++)
				V_DURABILITY_ARMOR_MATERIAL[loop][loop2] = CONFIG.getDouble(ARMOR + DOT + ARMOR_MATERIAL[loop] + DOT + RANK[loop2]);

		for (int loop = 0; loop != PROJECTILE_MATERIAL.length; loop++)
			for (int loop2 = 0; loop2 != RANK.length; loop2++)
				V_DURABILITY_PROJECTILE_MATERIAL[loop][loop2] = CONFIG.getDouble(PROJECTILE + DOT + PROJECTILE_MATERIAL[loop] + DOT + RANK[loop2]);
	}

	@Override
	public void Save()
	{
		CONFIG.set(RAID_TIME, V_RAID_TIME);
		for (int loop = 0; loop != RANK.length; loop++)
			CONFIG.set(ITEM + DOT + RANK[loop], V_ITEM_RANK[loop]);

		for (int loop = MIN_ENCHANTMENT_RANK; loop != RANK.length; loop++)
			for (int loop2 = 0; loop2 != RANK.length; loop2++)
				CONFIG.set(ENCHANTMENT + DOT + RANK[loop] + DOT + RANK[loop2], V_ENCHANTMENT[loop][loop2]);

		for (int loop = MIN_ENCHANTMENT_RANK; loop != RANK.length; loop++)
			for (int loop2 = 0; loop2 <= MAX_ENCHANTMENTS_OF_ITEM; loop2++)
				CONFIG.set(ENCHANTMENTS_OF_ITEM + DOT + RANK[loop] + DOT + loop2, V_ENCHANTMENTS_OF_ITEM[loop][loop2]);

		for (int loop = 0; loop != WEAPON_MATERIAL.length; loop++)
			for (int loop2 = 0; loop2 != RANK.length; loop2++)
				CONFIG.set(WEAPON + DOT + WEAPON_MATERIAL[loop] + DOT + RANK[loop2], V_DURABILITY_WEAPON_MATERIAL[loop][loop2]);

		for (int loop = 0; loop != ARMOR_MATERIAL.length; loop++)
			for (int loop2 = 0; loop2 != RANK.length; loop2++)
				CONFIG.set(ARMOR + DOT + ARMOR_MATERIAL[loop] + DOT + RANK[loop2], V_DURABILITY_ARMOR_MATERIAL[loop][loop2]);

		for (int loop = 0; loop != PROJECTILE_MATERIAL.length; loop++)
			for (int loop2 = 0; loop2 != RANK.length; loop2++)
				CONFIG.set(PROJECTILE + DOT + PROJECTILE_MATERIAL[loop] + DOT + RANK[loop2], V_DURABILITY_PROJECTILE_MATERIAL[loop][loop2]);

		super.Save();
	}
}
