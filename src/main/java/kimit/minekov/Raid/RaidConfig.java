package kimit.minekov.Raid;

import kimit.minekov.Util.ConfigFile.ConfigFileProvider;

public class RaidConfig extends ConfigFileProvider
{
	public static final String MAX = "Max";
	public static final String MIN = "Min";
	public static final String DOT = ".";
	public static final String RAID_TIME = "RaidTime";
	public final int V_RAID_TIME;
	public static final String[] RANK = { "Normal", "Rare", "Epic", "Legendary", "Unique" };
	public static final String ITEM = "Item";
	public final double[] V_ITEM_RANK = new double[RANK.length];
	public static final String ENCHANTMENT = "Enchantment";
	public final double[] V_ENCHANTMENT_RANK = new double[RANK.length];
	public static final String ENCHANTMENTS_OF_ITEM = "EnchantmentsOfItem";
	public final int MAX_ENCHANTMENTS_OF_ITEM = 5;
	public static final int MIN_ENCHANTMENT_RANK = 2;
	public final double[][] V_ENCHANTMENTS_OF_ITEM = new double[RANK.length][MAX_ENCHANTMENTS_OF_ITEM + 1];

	public RaidConfig(String filename)
	{
		super(filename);

		V_RAID_TIME = CONFIG.getInt(RAID_TIME);
		for (int loop = 0; loop != RANK.length; loop++)
		{
			V_ITEM_RANK[loop] = CONFIG.getDouble(ITEM + DOT + RANK[loop]);
			V_ENCHANTMENT_RANK[loop] = CONFIG.getDouble(ENCHANTMENT + DOT + RANK[loop]);
		}
		for (int loop = MIN_ENCHANTMENT_RANK; loop != RANK.length; loop++)
			for (int loop2 = 0; loop2 <= MAX_ENCHANTMENTS_OF_ITEM; loop2++)
				V_ENCHANTMENTS_OF_ITEM[loop][loop2] = CONFIG.getDouble(ENCHANTMENTS_OF_ITEM + DOT + RANK[loop] + DOT + loop2);
	}

	@Override
	public void Save()
	{
		CONFIG.set(RAID_TIME, V_RAID_TIME);
		for (int loop = 0; loop != RANK.length; loop++)
		{
			CONFIG.set(ITEM + DOT + RANK[loop], V_ITEM_RANK[loop]);
			CONFIG.set(ENCHANTMENT + DOT + RANK[loop], V_ENCHANTMENT_RANK[loop]);
		}
		for (int loop = MIN_ENCHANTMENT_RANK; loop != RANK.length; loop++)
			for (int loop2 = 0; loop2 <= MAX_ENCHANTMENTS_OF_ITEM; loop2++)
				CONFIG.set(ENCHANTMENTS_OF_ITEM + DOT + RANK[loop] + DOT + loop2, V_ENCHANTMENTS_OF_ITEM[loop][loop2]);

		super.Save();
	}
}
