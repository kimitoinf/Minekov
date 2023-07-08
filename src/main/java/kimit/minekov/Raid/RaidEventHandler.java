package kimit.minekov.Raid;

import kimit.minekov.Minekov;
import kimit.minekov.PlayerInfo.PlayerInfo;
import kimit.minekov.Util.ProgressBar;
import kimit.minekov.Util.Timer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class RaidEventHandler implements Listener
{
	@EventHandler
	public void OnDeath(PlayerDeathEvent e)
	{
		Minekov.PLAYERS.get(e.getEntity().getPlayer().getUniqueId()).RAID.Cancel();
	}

	@EventHandler
	public void OnLooting(InventoryOpenEvent e)
	{
		PlayerInfo playerInfo = Minekov.PLAYERS.get(e.getPlayer().getUniqueId());
		if (e.getInventory().getHolder() instanceof Chest && playerInfo.isInRaid() && !playerInfo.RAID.Looted.contains(e.getInventory().getLocation()))
		{
			e.setCancelled(true);
			Player player = (Player)e.getPlayer();
			final int time = Minekov.RAIDCONFIG.V_LOOTING_TIME;
			player.sendMessage(ChatColor.BOLD + "DON'T MOVE! Opening chest...");
			playerInfo.RAID.Looting = true;
			if (playerInfo.RAID.LootingTimer != null && !playerInfo.RAID.LootingTimer.isCancelled())
				playerInfo.RAID.LootingTimer.cancel();
			playerInfo.RAID.LootingTimer = new Timer(time, second -> {
				TextComponent progress = new TextComponent(new ProgressBar('=', '-').GetString((time - second) / (double)time));
				progress.setColor(ChatColor.DARK_RED);
				player.spigot().sendMessage(ChatMessageType.ACTION_BAR, progress);
				return true;
			}, () -> {
				Minekov.PLAYERS.get(player.getUniqueId()).RAID.Looted.add(e.getInventory().getLocation());
				playerInfo.RAID.Looting = false;
				player.openInventory(e.getInventory());
			});
			playerInfo.RAID.LootingTimer.Start(Bukkit.getPluginManager().getPlugin(Minekov.PLUGINNAME));
		}
	}

	@EventHandler
	public void OnMoving(PlayerMoveEvent e)
	{
		Location from = e.getFrom();
		Location to = e.getTo();
		if (from.getX() != to.getX() || from.getY() != to.getY() || from.getZ() != to.getZ())
		{
			PlayerInfo playerInfo = Minekov.PLAYERS.get(e.getPlayer().getUniqueId());
			if (playerInfo.RAID.Looting)
			{
				playerInfo.RAID.LootingTimer.cancel();
				playerInfo.RAID.Looting = false;
				e.getPlayer().sendMessage(ChatColor.BOLD + "LOOTING WAS CANCELLED!");
			}
			else if (playerInfo.RAID.Escaping)
			{
				playerInfo.RAID.EscapingTimer.cancel();
				playerInfo.RAID.Escaping = false;
				e.getPlayer().sendMessage(ChatColor.BOLD + "ESCAPING WAS CANCELLED!");
			}
		}
	}

	@EventHandler
	public void OnEscape(PlayerInteractEvent e)
	{
		PlayerInfo playerInfo = Minekov.PLAYERS.get(e.getPlayer().getUniqueId());
		if (e.getClickedBlock() != null && e.getClickedBlock().getType() == RaidConfig.V_ESCAPE_MATERIAL && playerInfo.isInRaid())
		{
			e.getPlayer().sendMessage(ChatColor.BOLD + "DON'T MOVE! Escaping...");
			playerInfo.RAID.Escaping = true;
			if (playerInfo.RAID.EscapingTimer != null && !playerInfo.RAID.EscapingTimer.isCancelled())
				playerInfo.RAID.EscapingTimer.cancel();
			final int time = Minekov.RAIDCONFIG.V_ESCAPE_TIME;
			playerInfo.RAID.EscapingTimer = new Timer(time, second -> {
				TextComponent progress = new TextComponent(new ProgressBar('=', '-').GetString((time - second) / (double)time));
				progress.setColor(ChatColor.BLUE);
				e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, progress);
				return true;
			}, () -> {
				playerInfo.RAID.Escaping = false;
				playerInfo.RAID.Cancel();
				e.getPlayer().teleport(new Location(Bukkit.getWorld(e.getPlayer().getUniqueId().toString()), 0.5, 100, 0.5));
			});
			playerInfo.RAID.EscapingTimer.Start(Bukkit.getPluginManager().getPlugin(Minekov.PLUGINNAME));
		}
	}
}
