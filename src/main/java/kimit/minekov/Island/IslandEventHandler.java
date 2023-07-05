package kimit.minekov.Island;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class IslandEventHandler implements Listener
{
	@EventHandler
	public void OnJoin(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		if (Bukkit.getServer().getWorld(player.getUniqueId().toString()) == null)
		{
			WorldCreator creator = new WorldCreator(player.getUniqueId().toString());
			creator.generator(new EmptyChunk());
			World world = creator.createWorld();
			Location location = new Location(world, 0.5, 100, 0.5);
			world.getBlockAt(0, 100, 0).setType(Material.BEDROCK);
			world.setSpawnLocation(location);
			world.setDifficulty(Difficulty.PEACEFUL);
			world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
			world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
			world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
			world.setGameRule(GameRule.KEEP_INVENTORY, true);
			world.setTime(0);
			player.teleport(location);
			player.setBedSpawnLocation(location);
		}
	}

	@EventHandler
	public void OnRespawn(PlayerRespawnEvent e)
	{
		e.setRespawnLocation(new Location(Bukkit.getWorld(e.getPlayer().getUniqueId().toString()), 0.5, 100, 0.5));
	}
}
