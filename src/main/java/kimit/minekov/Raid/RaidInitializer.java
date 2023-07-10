package kimit.minekov.Raid;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class RaidInitializer
{
	private final Plugin PLUGIN;

	public RaidInitializer(String plugin)
	{
		PLUGIN = Bukkit.getPluginManager().getPlugin(plugin);
	}

	public void Start(int raidTime, RaidPoint loots)
	{
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				for (Location location : loots.RaidPointList)
				{
					Block block = location.getBlock();
					block.setType(Material.CHEST);
					Chest chest = (Chest)block.getState();
					chest.getBlockInventory().clear();
					chest.getBlockInventory().addItem(new RaidLoot().GetLootChest());
				}
			}
		}.runTaskTimer(PLUGIN, 0, raidTime * 20L);
	}
}
