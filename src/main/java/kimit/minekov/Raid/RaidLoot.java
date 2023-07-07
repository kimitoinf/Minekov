package kimit.minekov.Raid;

import de.tr7zw.nbtapi.NBTItem;
import kimit.minekov.Minekov;
import kimit.minekov.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.List;
import java.util.Random;

public class RaidLoot
{
	public static final Material[][] ITEM_LOOT = {
			{
					Material.WOODEN_SWORD, Material.WOODEN_AXE, Material.WOODEN_SHOVEL, Material.WOODEN_PICKAXE,
					Material.GOLDEN_SWORD, Material.GOLDEN_AXE, Material.GOLDEN_SHOVEL, Material.GOLDEN_PICKAXE,
					Material.STONE_SWORD, Material.STONE_AXE, Material.STONE_SHOVEL, Material.STONE_PICKAXE,
					Material.IRON_SWORD, Material.IRON_AXE, Material.IRON_SHOVEL, Material.IRON_PICKAXE,
					Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS,
					Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS,
					Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS,
					Material.BOW, Material.CROSSBOW, Material.TRIDENT,
					Material.PUFFERFISH, Material.DRIED_KELP, Material.BEETROOT, Material.SPIDER_EYE, Material.TROPICAL_FISH,
					Material.POISONOUS_POTATO, Material.COOKIE, Material.GLOW_BERRIES, Material.MELON_SLICE, Material.SWEET_BERRIES,
					Material.STICK, Material.INK_SAC, Material.FEATHER, Material.FLINT, Material.STRING,
					Material.BONE, Material.SNOWBALL, Material.TORCH, Material.GUNPOWDER,
					Material.COBBLESTONE, Material.GLASS
			},
			{
					Material.WOODEN_SWORD, Material.WOODEN_AXE, Material.WOODEN_SHOVEL, Material.WOODEN_PICKAXE,
					Material.GOLDEN_SWORD, Material.GOLDEN_AXE, Material.GOLDEN_SHOVEL, Material.GOLDEN_PICKAXE,
					Material.STONE_SWORD, Material.STONE_AXE, Material.STONE_SHOVEL, Material.STONE_PICKAXE,
					Material.IRON_SWORD, Material.IRON_AXE, Material.IRON_SHOVEL, Material.IRON_PICKAXE,
					Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS,
					Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS,
					Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS,
					Material.BOW, Material.CROSSBOW, Material.TRIDENT, Material.SHIELD,
					Material.ROTTEN_FLESH, Material.POTATO, Material.CHICKEN,
					Material.COD, Material.MUTTON, Material.SALMON, Material.POTION,
					Material.COPPER_INGOT, Material.GOLD_NUGGET, Material.COAL, Material.LEATHER,
					Material.ARROW, Material.PAPER, Material.EXPERIENCE_BOTTLE
			},
			{
					Material.IRON_SWORD, Material.IRON_AXE, Material.IRON_SHOVEL, Material.IRON_PICKAXE,
					Material.DIAMOND_SWORD, Material.DIAMOND_AXE, Material.DIAMOND_SHOVEL, Material.DIAMOND_PICKAXE,
					Material.NETHERITE_SWORD, Material.NETHERITE_AXE, Material.NETHERITE_SHOVEL, Material.NETHERITE_PICKAXE,
					Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS,
					Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS,
					Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS,
					Material.BOW, Material.CROSSBOW, Material.TRIDENT, Material.SHIELD,
					Material.COOKED_CHICKEN, Material.COOKED_SALMON, Material.COOKED_COD,
					Material.COOKED_MUTTON, Material.BAKED_POTATO,
					Material.MILK_BUCKET, Material.SPLASH_POTION,
					Material.GOLD_INGOT, Material.IRON_INGOT, Material.EMERALD, Material.ENDER_PEARL,
					Material.FIRE_CHARGE, Material.LAPIS_LAZULI, Material.ZOMBIE_HEAD
			},
			{
					Material.IRON_SWORD, Material.IRON_AXE, Material.IRON_SHOVEL, Material.IRON_PICKAXE,
					Material.DIAMOND_SWORD, Material.DIAMOND_AXE, Material.DIAMOND_SHOVEL, Material.DIAMOND_PICKAXE,
					Material.NETHERITE_SWORD, Material.NETHERITE_AXE, Material.NETHERITE_SHOVEL, Material.NETHERITE_PICKAXE,
					Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS,
					Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS,
					Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS,
					Material.BOW, Material.CROSSBOW, Material.TRIDENT, Material.SHIELD,
					Material.GOLDEN_APPLE, Material.POTION, Material.SPLASH_POTION,
					Material.DIAMOND, Material.OBSIDIAN
			},
			{
					Material.DIAMOND_SWORD, Material.DIAMOND_AXE, Material.DIAMOND_SHOVEL, Material.DIAMOND_PICKAXE,
					Material.NETHERITE_SWORD, Material.NETHERITE_AXE, Material.NETHERITE_SHOVEL, Material.NETHERITE_PICKAXE,
					Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS,
					Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS,
					Material.BOW, Material.CROSSBOW, Material.TRIDENT, Material.SHIELD,
					Material.ENCHANTED_GOLDEN_APPLE, Material.POTION, Material.SPLASH_POTION,
					Material.BLAZE_POWDER
			}
	};
	public static final PotionType[][] POTION_LOOT = {
			{},
			{
					PotionType.INSTANT_HEAL
			},
			{},
			{
					PotionType.STRENGTH
			},
			{
					PotionType.REGEN, PotionType.NIGHT_VISION,
					PotionType.INVISIBILITY, PotionType.SPEED
			}
	};
	public static final PotionType[][] SPLASH_POTION_LOOT = {
			{}, {},
			{
					PotionType.INSTANT_HEAL
			},
			{
					PotionType.WEAKNESS, PotionType.INSTANT_DAMAGE
			},
			{
					PotionType.POISON, PotionType.SLOWNESS
			}
	};
	public static final Enchantment[][][] ENCHANTMENTS = {
			{
					{ Enchantment.DAMAGE_ARTHROPODS, Enchantment.KNOCKBACK, Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_UNDEAD },
					{ Enchantment.DAMAGE_ARTHROPODS, Enchantment.KNOCKBACK, Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_UNDEAD },
					{ Enchantment.DURABILITY, Enchantment.DAMAGE_ARTHROPODS, Enchantment.FIRE_ASPECT, Enchantment.LOOT_BONUS_MOBS, Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_UNDEAD, Enchantment.SWEEPING_EDGE },
					{ Enchantment.DURABILITY, Enchantment.DAMAGE_ARTHROPODS, Enchantment.FIRE_ASPECT, Enchantment.LOOT_BONUS_MOBS, Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_UNDEAD, Enchantment.SWEEPING_EDGE },
					{ Enchantment.MENDING, Enchantment.DURABILITY, Enchantment.DAMAGE_ARTHROPODS, Enchantment.LOOT_BONUS_MOBS, Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_UNDEAD, Enchantment.SWEEPING_EDGE }
			},
			{
					{ Enchantment.DAMAGE_ARTHROPODS, Enchantment.DIG_SPEED, Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_UNDEAD },
					{ Enchantment.DAMAGE_ARTHROPODS, Enchantment.DIG_SPEED, Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_UNDEAD },
					{ Enchantment.DURABILITY, Enchantment.DAMAGE_ARTHROPODS, Enchantment.DIG_SPEED, Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_UNDEAD },
					{ Enchantment.DURABILITY, Enchantment.DAMAGE_ARTHROPODS, Enchantment.DIG_SPEED, Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_UNDEAD },
					{ Enchantment.MENDING, Enchantment.DURABILITY, Enchantment.DAMAGE_ARTHROPODS, Enchantment.DIG_SPEED, Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_UNDEAD }
			},
			{
					{ Enchantment.DIG_SPEED },
					{ Enchantment.DIG_SPEED },
					{ Enchantment.DURABILITY, Enchantment.DIG_SPEED },
					{ Enchantment.DURABILITY, Enchantment.DIG_SPEED },
					{ Enchantment.MENDING, Enchantment.DURABILITY, Enchantment.DIG_SPEED }
			},
			{
					{ Enchantment.DIG_SPEED },
					{ Enchantment.DIG_SPEED },
					{ Enchantment.DURABILITY, Enchantment.DIG_SPEED },
					{ Enchantment.DURABILITY, Enchantment.DIG_SPEED },
					{ Enchantment.MENDING, Enchantment.DURABILITY, Enchantment.DIG_SPEED }
			},
			{
					{ Enchantment.ARROW_DAMAGE },
					{ Enchantment.ARROW_DAMAGE },
					{ Enchantment.DURABILITY, Enchantment.ARROW_DAMAGE },
					{ Enchantment.DURABILITY, Enchantment.ARROW_FIRE, Enchantment.ARROW_DAMAGE, Enchantment.ARROW_KNOCKBACK },
					{ Enchantment.MENDING, Enchantment.DURABILITY, Enchantment.ARROW_INFINITE, Enchantment.ARROW_DAMAGE, Enchantment.ARROW_KNOCKBACK }
			},
			{
					{},
					{},
					{ Enchantment.DURABILITY, Enchantment.LOYALTY },
					{ Enchantment.DURABILITY, Enchantment.LOYALTY },
					{ Enchantment.MENDING, Enchantment.DURABILITY, Enchantment.LOYALTY }
			},
			{
					{},
					{ Enchantment.PIERCING },
					{ Enchantment.DURABILITY, Enchantment.PIERCING, Enchantment.QUICK_CHARGE },
					{ Enchantment.DURABILITY, Enchantment.PIERCING, Enchantment.QUICK_CHARGE },
					{ Enchantment.MENDING, Enchantment.DURABILITY, Enchantment.PIERCING, Enchantment.QUICK_CHARGE, Enchantment.MULTISHOT }
			},
			{
					{},
					{ Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.PROTECTION_ENVIRONMENTAL },
					{ Enchantment.DURABILITY, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.THORNS },
					{ Enchantment.DURABILITY, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.THORNS },
					{ Enchantment.MENDING, Enchantment.DURABILITY, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.THORNS },
			}
	};
	public static final int[][][] ENCHANTMENT_LEVELS = {
			{
					{1, 2, 1, 1},
					{2, 1, 2, 2},
					{1, 3, 1, 1, 3, 3, 1},
					{2, 4, 2, 2, 4, 4, 2},
					{1, 3, 5, 3, 5, 5, 3}
			},
			{
					{1, 1, 1, 1},
					{2, 2, 2, 2},
					{1, 3, 3, 3, 3},
					{2, 4, 4, 4, 4},
					{1, 3, 5, 5, 5, 5}
			},
			{
					{1},
					{2},
					{1, 3},
					{2, 4},
					{1, 3, 5}
			},
			{
					{1},
					{2},
					{1, 3},
					{2, 4},
					{1, 3, 5}
			},
			{
					{1},
					{2},
					{1, 3},
					{2, 1, 4, 1},
					{1, 3, 1, 5, 2}
			},
			{
					{},
					{},
					{1, 1},
					{2, 2},
					{1, 3, 3}
			},
			{
					{},
					{1},
					{1, 2, 1},
					{2, 3, 2},
					{1, 3, 4, 3, 1}
			},
			{
					{},
					{1, 1, 1, 1},
					{1, 2, 2, 2, 2, 1},
					{2, 3, 3, 3, 3, 2},
					{1, 3, 4, 4, 4, 4, 3}
			}
	};
	public static final String[] WEAPONS = { "SWORD", "AXE", "SHOVEL", "PICKAXE" };
	public static final String[] ARMORS = { "HELMET", "CHESTPLATE", "LEGGINGS", "BOOTS" };
	public static final String[] EQUIPMENTS = { "SWORD", "AXE", "SHOVEL", "PICKAXE", "BOW", "TRIDENT", "CROSSBOW", "HELMET", "CHESTPLATE", "LEGGINGS", "BOOTS" };
	public static final String RANK = "Rank";

	public ItemStack GetLootItem()
	{
		int rank = Util.GetRandom(Minekov.RAIDCONFIG.V_ITEM_RANK);
		Material material = ITEM_LOOT[rank][new Random().nextInt(ITEM_LOOT[rank].length)];
		ItemStack item = new ItemStack(material);

		for (int loop = 0; loop != WEAPONS.length; loop++)
		{
			if (material.name().contains(WEAPONS[loop]))
			{
				for (int loop2 = 0; loop2 != RaidConfig.WEAPON_MATERIAL.length; loop2++)
				{
					if (material.name().contains(RaidConfig.WEAPON_MATERIAL[loop2]))
					{
						item = SetEnchantment(item, rank);
						Damageable meta = (Damageable)item.getItemMeta();
						meta.setDamage((int)(material.getMaxDurability() * (1 - Minekov.RAIDCONFIG.V_DURABILITY_WEAPON_MATERIAL[loop2][rank])));
						item.setItemMeta(meta);
						return SetRank(item, rank);
					}
				}
			}
		}

		for (int loop = 0; loop != ARMORS.length; loop++)
		{
			if (material.name().contains(ARMORS[loop]))
			{
				for (int loop2 = 0; loop2 != RaidConfig.ARMOR_MATERIAL.length; loop2++)
				{
					if (material.name().contains(RaidConfig.ARMOR_MATERIAL[loop2]))
					{
						item = SetEnchantment(item, rank);
						Damageable meta = (Damageable)item.getItemMeta();
						meta.setDamage((int)(material.getMaxDurability() * (1 - Minekov.RAIDCONFIG.V_DURABILITY_ARMOR_MATERIAL[loop2][rank])));
						item.setItemMeta(meta);
						return SetRank(item, rank);
					}
				}
			}
		}

		for (int loop = 0; loop != RaidConfig.PROJECTILE_MATERIAL.length; loop++)
		{
			if (material.name().contains(RaidConfig.PROJECTILE_MATERIAL[loop]))
			{
				item = SetEnchantment(item, rank);
				Damageable meta = (Damageable)item.getItemMeta();
				meta.setDamage((int)(material.getMaxDurability() * (1 - Minekov.RAIDCONFIG.V_DURABILITY_PROJECTILE_MATERIAL[loop][rank])));
				item.setItemMeta(meta);
				return SetRank(item, rank);
			}
		}

		if (material == Material.POTION)
		{
			PotionMeta meta = (PotionMeta)item.getItemMeta();
			meta.setBasePotionData(new PotionData(POTION_LOOT[rank][new Random().nextInt(POTION_LOOT[rank].length)]));
			item.setItemMeta(meta);
		}
		else if (material == Material.SPLASH_POTION)
		{
			PotionMeta meta = (PotionMeta)item.getItemMeta();
			meta.setBasePotionData(new PotionData(SPLASH_POTION_LOOT[rank][new Random().nextInt(SPLASH_POTION_LOOT[rank].length)]));
			item.setItemMeta(meta);
		}

		return SetRank(item, rank);
	}

	private ItemStack SetRank(ItemStack item, int rank)
	{
		ItemMeta meta = item.getItemMeta();
		meta.setLore(List.of(RaidConfig.RANK[rank]));
		item.setItemMeta(meta);
		NBTItem nbt = new NBTItem(item);
		nbt.setString(RANK, RaidConfig.RANK[rank]);
		nbt.applyNBT(item);
		return item;
	}

	private ItemStack SetEnchantment(ItemStack item, int rank)
	{
		if (rank < RaidConfig.MIN_ENCHANTMENT_RANK)
			return item;
		int equipment = -1;
		for (int loop = 0; loop != EQUIPMENTS.length; loop++)
			if (item.getType().name().contains(EQUIPMENTS[loop]))
				equipment = loop;
		if (equipment == -1)
			return item;
		equipment = Math.min(equipment, ENCHANTMENTS.length - 1);
		int enchantments = Util.GetRandom(Minekov.RAIDCONFIG.V_ENCHANTMENTS_OF_ITEM[rank]);
		NBTItem nbt = new NBTItem(item);
		nbt.setInteger(RaidConfig.ENCHANTMENTS_OF_ITEM, enchantments);
		nbt.applyNBT(item);
		for (int loop = 0; loop != enchantments; loop++)
		{
			int enchantmentRank;
			do
			{
				enchantmentRank = Util.GetRandom(Minekov.RAIDCONFIG.V_ENCHANTMENT[rank]);
			}
			while (ENCHANTMENTS[equipment][enchantmentRank].length == 0);

			int enchantment = new Random().nextInt(ENCHANTMENTS[equipment][enchantmentRank].length);
			item.addEnchantment(ENCHANTMENTS[equipment][enchantmentRank][enchantment], ENCHANTMENT_LEVELS[equipment][enchantmentRank][enchantment]);
			nbt = new NBTItem(item);
			nbt.setString(RaidConfig.ENCHANTMENTS_OF_ITEM + loop, RaidConfig.RANK[enchantmentRank]);
			nbt.applyNBT(item);
		}
		return item;
	}
}