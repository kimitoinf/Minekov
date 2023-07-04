package kimit.minekov.PlayerInfo;

import kimit.minekov.Minekov;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerInfoEventHandler implements Listener
{
	@EventHandler
	public void OnJoin(PlayerJoinEvent e)
	{
		Player player = e.getPlayer();
		Minekov.PLAYERS.put(player.getUniqueId(), new PlayerInfo(player.getUniqueId()));
	}

	@EventHandler
	public void OnQuit(PlayerQuitEvent e)
	{
		Minekov.PLAYERS.get(e.getPlayer().getUniqueId()).Save();
		Minekov.PLAYERS.remove(e.getPlayer().getUniqueId());
	}

	@EventHandler
	public void ClickInventory(InventoryClickEvent e)
	{
		if (e.getView().getTitle().equals(PlayerInfo.RECEIVE) && e.getClickedInventory().equals(e.getView().getBottomInventory()))
			e.setCancelled(true);
	}
}
