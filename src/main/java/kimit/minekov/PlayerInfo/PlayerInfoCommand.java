package kimit.minekov.PlayerInfo;

import kimit.minekov.Minekov;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerInfoCommand implements CommandExecutor
{
	public static final String RECEIVE = "receive";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player)sender;

			switch (label)
			{
				case RECEIVE:
					Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(player.getUniqueId().toString()).OpenInventory(player);
					break;
			}
		}
		return true;
	}
}
