package kimit.minekov.Command;

import kimit.minekov.Raid.RaidPoint;
import kimit.minekov.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;

public class RaidPointExecutor extends Executor
{
	private final RaidPoint POINTS;

	public RaidPointExecutor(RaidPoint points)
	{
		POINTS = points;
	}

	@Override
	public void Run(CommandSender sender, String[] args)
	{
		if (args.length != 3 || !Util.IsNumberic(args[0]) || !Util.IsNumberic(args[1]) || !Util.IsNumberic(args[2]))
		{
			sender.sendMessage(ARGUMENTS_ERROR);
			return;
		}
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		int z = Integer.parseInt(args[2]);
		for (Location loop : POINTS.RaidPointList)
		{
			if (loop.getBlockX() == x && loop.getBlockY() == y && loop.getBlockZ() == z)
			{
				sender.sendMessage("Already registered location.");
				return;
			}
		}
		Location location = new Location(Bukkit.getWorlds().get(0), x, y, z);
		POINTS.RaidPointList.add(location);
		sender.sendMessage("Registered location " + location);
	}
}