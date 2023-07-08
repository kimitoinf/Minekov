package kimit.minekov;

import kimit.minekov.Island.IslandEventHandler;
import kimit.minekov.Market.Market;
import kimit.minekov.Market.MarketEventHandler;
import kimit.minekov.PlayerInfo.PlayerInfo;
import kimit.minekov.PlayerInfo.PlayerInfoEventHandler;
import kimit.minekov.Raid.RaidConfig;
import kimit.minekov.Raid.RaidEventHandler;
import kimit.minekov.Raid.RaidPoint;
import kimit.minekov.Util.InventoryPage.InventoryPageEventHandler;
import kimit.minekov.Util.InventoryPage.InventoryPageManager;
import kimit.minekov.Util.PrefixLogger;
import kimit.minekov.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public final class Minekov extends JavaPlugin
{
	public static final String PLUGINNAME = "Minekov";
	public static final String PLAYERSFOLDER = File.separator + "Players";
	public static final PrefixLogger LOGGER = new PrefixLogger(Bukkit.getLogger());
	public static HashMap<UUID, PlayerInfo> PLAYERS = new HashMap<UUID, PlayerInfo>();
	public static InventoryPageManager INVENTORYPAGEMANAGER = new InventoryPageManager();
	public static Market MARKET;
	public static final String RAIDFOLDER = File.separator + "Raid";
	public static RaidPoint RAIDSPAWN, RAIDESCAPE, RAIDLOOT;
	public static RaidConfig RAIDCONFIG;
	private final Listener[] EVENTHANDLERS = {new PlayerInfoEventHandler(), new InventoryPageEventHandler(), new MarketEventHandler(), new IslandEventHandler(), new RaidEventHandler()};

	@Override
	public void onEnable()
	{
		super.onEnable();
		LOGGER.Log("Minekov plugin is enabled.");

		final String[] folders = {getDataFolder().toString(), getDataFolder().toString() + PLAYERSFOLDER, getDataFolder().toString() + RAIDFOLDER};
		for (String loop : folders) Util.MakeFolder(loop);

		MARKET = new Market("Market.yml");
		RAIDSPAWN = new RaidPoint(RAIDFOLDER + File.separator + "RaidSpawn.yml");
		RAIDESCAPE = new RaidPoint(RAIDFOLDER + File.separator + "RaidEscape.yml");
		RAIDLOOT = new RaidPoint(RAIDFOLDER + File.separator + "RaidLoot.yml");
		RAIDCONFIG = new RaidConfig(RAIDFOLDER + File.separator + "RaidConfig.yml");

		for (Player player : Bukkit.getServer().getOnlinePlayers())
		{
			UUID uuid = player.getUniqueId();
			PlayerInfo playerInfo = new PlayerInfo(uuid);
			PLAYERS.put(uuid, playerInfo);
		}

		for (Listener loop : EVENTHANDLERS)
			Bukkit.getPluginManager().registerEvents(loop, this);
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
		RAIDESCAPE.Save();
		RAIDLOOT.Save();
		RAIDCONFIG.Save();
	}
}