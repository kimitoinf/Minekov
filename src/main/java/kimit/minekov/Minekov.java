package kimit.minekov;

import kimit.minekov.Island.IslandEvent;
import kimit.minekov.Market.Market;
import kimit.minekov.PlayerInfo.PlayerInfo;
import kimit.minekov.util.ConfigFile.ConfigFile;
import kimit.minekov.util.InventoryPage.InventoryPageManager;
import kimit.minekov.util.PrefixLogger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.C;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public final class Minekov extends JavaPlugin
{
	public static final String PLUGINNAME = "Minekov";
	public static final String PLAYERSFOLDER = File.separator + "players";
	public static final PrefixLogger LOGGER = new PrefixLogger(Bukkit.getLogger());
	public static HashMap<UUID, PlayerInfo> PLAYERS = new HashMap<UUID, PlayerInfo>();
	public static InventoryPageManager INVENTORYPAGEMANAGER = new InventoryPageManager();
	public static Market MARKET;
	public static RaidSpawn RAIDSPAWN;

	@Override
	public void onEnable()
	{
		super.onEnable();
		LOGGER.Log("Minekov plugin is enabled.");

		File dataFolder = new File(getDataFolder().toString());
		if (!dataFolder.exists())
			dataFolder.mkdir();
		File playersFolder = new File(getDataFolder().toString() + PLAYERSFOLDER);
		if (!playersFolder.exists())
			playersFolder.mkdir();

		MARKET = new Market("Market.yml");
		RAIDSPAWN = new RaidSpawn("RaidSpawn.yml");

		for (Player player : Bukkit.getServer().getOnlinePlayers())
		{
			UUID uuid = player.getUniqueId();
			PlayerInfo playerInfo = new PlayerInfo(uuid);
			PLAYERS.put(uuid, playerInfo);
		}

		Bukkit.getPluginManager().registerEvents(new IslandEvent(), this);
		for (String loop : Commands.COMMANDS)
			this.getCommand(loop).setExecutor(new Commands());
	}

	@Override
	public void onDisable()
	{
		super.onDisable();
		LOGGER.Log("Enocraft plugin is disabled.");

		for (Player player : Bukkit.getServer().getOnlinePlayers())
			PLAYERS.get(player.getUniqueId()).Save();

		MARKET.Save();
		RAIDSPAWN.Save();
	}
}