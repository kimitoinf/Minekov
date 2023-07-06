package kimit.minekov.Raid;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

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
					Material.BOW, Material.CROSSBOW, Material.TRIDENT, Material.SHIELD,
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
					Material.COD, Material.MUTTON, Material.SALMON,
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
					Material.MILK_BUCKET, Material.POTION,
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
	public static final PotionEffectType[][] POTION_LOOT = {
			{
					PotionEffectType.HEAL
			},
			{
					PotionEffectType.INCREASE_DAMAGE
			},
			{
					PotionEffectType.REGENERATION, PotionEffectType.NIGHT_VISION,
					PotionEffectType.INVISIBILITY, PotionEffectType.SPEED
			}
	};
	public static final PotionEffectType[][] SPLASH_POTION_LOOT = {
			{
					PotionEffectType.WEAKNESS, PotionEffectType.HARM, PotionEffectType.HEAL
			},
			{
					PotionEffectType.POISON, PotionEffectType.SLOW
			}
	};

	public ItemStack GetLootItem()
	{
		return null;
	}
}