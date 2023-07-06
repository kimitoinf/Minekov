package kimit.minekov.Raid;

import kimit.minekov.Minekov;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class RaidEventHandler implements Listener
{
	@EventHandler
	public void OnDeath(PlayerDeathEvent e)
	{
		Minekov.PLAYERS.get(e.getEntity().getPlayer().getUniqueId()).RAID.Cancel();
	}
}
