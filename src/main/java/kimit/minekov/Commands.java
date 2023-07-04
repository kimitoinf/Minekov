package kimit.minekov;

import kimit.minekov.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.Random;

public class Commands implements CommandExecutor
{
	public static final String[] COMMANDS = {"raidspawn", "raid"};

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (label.equals(COMMANDS[0]))
			RaidSpawnCommand(sender, args);
		else if (label.equals(COMMANDS[1]))
			RaidCommand(sender);

		return true;
	}

	private void RaidSpawnCommand(CommandSender sender, String[] args)
	{
		if (args.length != 3 || !Util.IsNumberic(args[0]) || !Util.IsNumberic(args[1]) || !Util.IsNumberic(args[2]))
		{
			sender.sendMessage("Invalid arguments.");
			return;
		}
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		int z = Integer.parseInt(args[2]);
		for (Location loop : Minekov.RAIDSPAWN.RaidSpawnList)
		{
			if (loop.getBlockX() == x && loop.getBlockY() == y && loop.getBlockZ() == z)
			{
				sender.sendMessage("Already registered location.");
				return;
			}
		}
		Location location = new Location(Bukkit.getWorlds().get(0), x, y, z);
		Minekov.RAIDSPAWN.RaidSpawnList.add(location);
		sender.sendMessage("Registered location " + location.toString());
	}

	private void RaidCommand(CommandSender sender)
	{
		if (sender instanceof Player)
		{
			Player player = (Player)sender;
			player.teleport(Minekov.RAIDSPAWN.RaidSpawnList.get(new Random().nextInt(Minekov.RAIDSPAWN.RaidSpawnList.size())));
		}
	}
}
