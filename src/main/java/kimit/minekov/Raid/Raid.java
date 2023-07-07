package kimit.minekov.Raid;

import kimit.minekov.Minekov;
import kimit.minekov.PlayerInfo.PlayerInfo;
import kimit.minekov.Util.Timer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Random;

public class Raid
{
	private final Player PLAYER;
	public static final int RAIDTIME = 30;
	private Timer Teleport;
	private Timer RaidTimer;

	public Raid(Player player)
	{
		PLAYER = player;
	}

	public void Start()
	{
		PlayerInfo playerInfo = Minekov.PLAYERS.get(PLAYER.getUniqueId());
		Plugin plugin = Bukkit.getPluginManager().getPlugin(Minekov.PLUGINNAME);

		Teleport = new Timer(5, second -> {
			PLAYER.resetTitle();
			PLAYER.sendTitle(Integer.toString(second), null, 10, 70, 20);
			return true;
		}, () -> {
			PLAYER.resetTitle();
			PLAYER.teleport(Minekov.RAIDSPAWN.RaidSpawnList.get(new Random().nextInt(Minekov.RAIDSPAWN.RaidSpawnList.size())));
			playerInfo.setInRaid(true);
		});
		Teleport.Start(plugin);

		RaidTimer = new Timer(RAIDTIME, second -> {
			playerInfo.setRaidTime(second);
			playerInfo.UpdateBoard();
			return true;
		}, () -> PLAYER.setHealth(0.0));
		RaidTimer.Start(plugin);
	}

	public void Cancel()
	{
		if (RaidTimer != null && !RaidTimer.isCancelled())
			RaidTimer.cancel();
		PlayerInfo playerInfo = Minekov.PLAYERS.get(PLAYER.getUniqueId());
		playerInfo.setInRaid(false);
		playerInfo.UpdateBoard();
	}

	public void Pause()
	{
		RaidTimer.cancel();
	}

	public void Resume()
	{
		PlayerInfo playerInfo = Minekov.PLAYERS.get(PLAYER.getUniqueId());
		RaidTimer = new Timer(playerInfo.getRaidTime(), second -> {
			playerInfo.setRaidTime(second);
			playerInfo.UpdateBoard();
			return true;
		}, () -> PLAYER.setHealth(0.0));
		RaidTimer.Start(Bukkit.getPluginManager().getPlugin(Minekov.PLUGINNAME));
	}
}
