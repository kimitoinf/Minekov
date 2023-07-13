package kimit.minekov.Raid;

import kimit.minekov.Minekov;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class RaidInitializer
{
	private final Plugin PLUGIN;
	private Location First;
	private Location Last;
	public static boolean Initializing = false;
	public static final EntityType[] MOBS = {
			EntityType.ZOMBIE, EntityType.CREEPER, EntityType.SKELETON/*, EntityType.WITHER_SKELETON*/, EntityType.STRAY, EntityType.HUSK, EntityType.CAVE_SPIDER, EntityType.VINDICATOR, EntityType.PILLAGER, EntityType.RAVAGER, EntityType.ILLUSIONER/*, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE*/
	};

	public RaidInitializer(String plugin)
	{
		PLUGIN = Bukkit.getPluginManager().getPlugin(plugin);
		First = Minekov.RAIDCONFIG.V_FIRST_SPAWN_POINT;
		Last = Minekov.RAIDCONFIG.V_LAST_SPAWN_POINT;
	}

	public void Start(int raidTime, RaidPoint loots)
	{
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				First = Minekov.RAIDCONFIG.V_FIRST_SPAWN_POINT;
				Last = Minekov.RAIDCONFIG.V_LAST_SPAWN_POINT;
				if (!Initializing)
					return;
				for (Location location : loots.RaidPointList)
				{
					Block block = location.getBlock();
					block.setType(Material.CHEST);
					Chest chest = (Chest)block.getState();
					chest.getBlockInventory().clear();
					chest.getBlockInventory().addItem(new RaidLoot().GetLootChest());
				}
				World world = Bukkit.getWorlds().get(0);
				for (LivingEntity loop : world.getLivingEntities())
					if (!(loop instanceof Player))
						loop.setHealth(0);
				for (Entity loop : world.getEntities())
					if (loop instanceof Item)
						loop.remove();
				if (First != null && Last != null)
				{
					double minX = Math.min(First.getX(), Last.getX());
					double maxX = Math.max(First.getX(), Last.getX());
					double minZ = Math.min(First.getZ(), Last.getZ());
					double maxZ = Math.max(First.getZ(), Last.getZ());
					for (int loop = 0; loop != (int)((maxX - minX) * (maxZ - minZ) * Minekov.RAIDCONFIG.V_MOBS_PER_BLOCK); loop++)
						world.spawnEntity(new Location(world, Math.random() * (maxX - minX + 1) + minX, First.getY(), Math.random() * (maxZ - minZ + 1) + minZ), MOBS[new Random().nextInt(MOBS.length)]);
				}
				Minekov.LOGGER.Log("Raid initialized.");
			}
		}.runTaskTimer(PLUGIN, 0, raidTime * 20L);
	}

	public Location getFirst()
	{
		return First;
	}

	public Location getLast()
	{
		return Last;
	}
}
