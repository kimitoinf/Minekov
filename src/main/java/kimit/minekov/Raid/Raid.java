package kimit.minekov.Raid;

import kimit.minekov.Minekov;
import kimit.minekov.PlayerInfo.PlayerInfo;
import kimit.minekov.Util.Timer;
import kimit.minekov.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Random;

public class Raid
{
	private final Player PLAYER;
	private Timer RaidTimer;
	public Timer LootingTimer;
	public Timer EscapingTimer;
	public ArrayList<Location> Looted = new ArrayList<>();
	public boolean Looting = false;
	public boolean Escaping = false;
	private ArrayList<Location> Escapes = new ArrayList<>();

	public Raid(Player player)
	{
		PLAYER = player;
	}

	public void Start()
	{
		PlayerInfo playerInfo = Minekov.PLAYERS.get(PLAYER.getUniqueId());
		Plugin plugin = Bukkit.getPluginManager().getPlugin(Minekov.PLUGINNAME);

		Looted.clear();
		Looting = false;
		Escaping = false;

		Timer teleport = new Timer(5, second -> {
			PLAYER.resetTitle();
			PLAYER.sendTitle(Integer.toString(second), null, 10, 70, 20);
			return true;
		}, () -> {
			PLAYER.resetTitle();
			PLAYER.teleport(Minekov.RAIDSPAWN.RaidPointList.get(new Random().nextInt(Minekov.RAIDSPAWN.RaidPointList.size())));
			playerInfo.setInRaid(true);
		});
		teleport.Start(plugin);

		RaidTimer = new Timer(Minekov.RAIDCONFIG.V_RAID_TIME, second -> {
			playerInfo.setRaidTime(second);
			playerInfo.UpdateBoard();
			for (int loop = 0; loop != RaidConfig.MAX_ESCAPE_SPAWN; loop++)
			{
				if ((int) (Minekov.RAIDCONFIG.V_ESCAPE_SPAWN_TIME[loop] * Minekov.RAIDCONFIG.V_RAID_TIME) == second)
				{
					Location location = Minekov.RAIDESCAPE.RaidPointList.get(new Random().nextInt(Minekov.RAIDESCAPE.RaidPointList.size()));
					location.getBlock().setType(RaidConfig.V_ESCAPE_MATERIAL);
					Escapes.add(location);

					for (Player player : Bukkit.getOnlinePlayers())
						if (Minekov.PLAYERS.get(player.getUniqueId()).isInRaid())
							player.sendMessage(ChatColor.ITALIC + "탈출구가 스폰되었습니다.");
				}
			}
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

		for (Location loop : Escapes)
			loop.getBlock().setType(Material.AIR);
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
