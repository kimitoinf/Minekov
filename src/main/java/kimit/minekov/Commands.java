package kimit.minekov;

import kimit.minekov.Market.Market;
import kimit.minekov.PlayerInfo.PlayerInfo;
import kimit.minekov.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Commands implements CommandExecutor
{
	public static final String[] COMMANDS = {"raidspawn", "raid", "receive", "market", "sell", "getgold", "setgold", "gold"};
	private static final String ARGUMENTSERROR = "Invalid arguments.";

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if (label.equals(COMMANDS[0]))
			RaidSpawnCommand(sender, args);
		else if (label.equals(COMMANDS[1]))
			RaidCommand(sender);
		else if (label.equals(COMMANDS[2]))
			ReceiveCommand(sender);
		else if (label.equals(COMMANDS[3]))
			MarketCommand(sender);
		else if (label.equals(COMMANDS[4]))
			SellCommand(sender);
		else if (label.equals(COMMANDS[5]))
			GetGoldCommand(sender, args);
		else if (label.equals(COMMANDS[6]))
			SetGoldCommand(sender, args);
		else if (label.equals(COMMANDS[7]))
			GoldCommand(sender, args);

		return true;
	}

	private void RaidSpawnCommand(CommandSender sender, String[] args)
	{
		if (args.length != 3 || !Util.IsNumberic(args[0]) || !Util.IsNumberic(args[1]) || !Util.IsNumberic(args[2]))
		{
			sender.sendMessage(ARGUMENTSERROR);
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
			if (Minekov.RAIDSPAWN.RaidSpawnList.size() == 0)
				player.sendMessage("Error: No raid spawn point. contact admin.");
			else
			{
				PlayerInfo playerInfo = Minekov.PLAYERS.get(player.getUniqueId());
				if (playerInfo.isInRaid()) player.sendMessage(ChatColor.RED + "You are already in raid.");
				else
				{
					player.sendMessage(ChatColor.BOLD + "Start raid in 5 seconds.");
					playerInfo.RAID.Start();
				}
			}
		}
	}

	private void ReceiveCommand(CommandSender sender)
	{
		if (sender instanceof Player)
		{
			Player player = (Player)sender;
			Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(player.getUniqueId().toString()).OpenInventory(player);
		}
	}

	private void MarketCommand(CommandSender sender)
	{
		if (sender instanceof Player)
			Minekov.MARKET.Open((Player)sender);
	}

	private void SellCommand(CommandSender sender)
	{
		if (sender instanceof Player)
		{
			Player player = (Player)sender;
			if (Minekov.PLAYERS.get(player.getUniqueId()).OnMarketSell)
			{
				player.sendMessage("이미 판매 등록이 진행 중입니다.");
				return;
			}
			Inventory inventory = Bukkit.createInventory(null, 9, Market.SELLINVENTORY);
			player.openInventory(inventory);
		}
	}

	private void GetGoldCommand(CommandSender sender, String[] args)
	{
		if (args.length != 1)
		{
			sender.sendMessage(ARGUMENTSERROR);
			return;
		}
		Player player = Bukkit.getPlayer(args[0]);
		if (player == null)
			sender.sendMessage(ARGUMENTSERROR);
		else
			sender.sendMessage(Long.toString(Minekov.PLAYERS.get(player.getUniqueId()).getGold()));
	}

	private void SetGoldCommand(CommandSender sender, String[] args)
	{
		if (args.length != 2)
		{
			sender.sendMessage(ARGUMENTSERROR);
			return;
		}
		Player player = Bukkit.getPlayer(args[0]);
		if (player == null || !Util.IsNumberic(args[1]))
			sender.sendMessage(ARGUMENTSERROR);
		else
		{
			Minekov.PLAYERS.get(player.getUniqueId()).setGold(Long.parseLong(args[1]));
			sender.sendMessage("Player " + player.getName() + "'s gold is now " + Long.parseLong(args[1]));
			Minekov.PLAYERS.get(player.getUniqueId()).UpdateBoard();
		}
	}

	private void GoldCommand(CommandSender sender, String[] args)
	{
		if (args.length != 1 || !Util.IsNumberic(args[0]))
			sender.sendMessage(ARGUMENTSERROR);
		else if (sender instanceof Player)
		{
			if (Minekov.PLAYERS.get(((Player)sender).getUniqueId()).getGold() < Integer.parseInt(args[0]))
			{
				sender.sendMessage("골드가 부족합니다.");
				return;
			}
			else
			{
				sender.sendMessage(ChatColor.YELLOW + args[0] + " 골드를 지급했습니다.");
				Util.GiveItem((Player) sender, PlayerInfo.GoldItem(Long.parseLong(args[0])));
				Minekov.PLAYERS.get(((Player)sender).getUniqueId()).addGold(-Integer.parseInt(args[0]));
				Minekov.PLAYERS.get(((Player)sender).getUniqueId()).UpdateBoard();
			}
		}
		else
			sender.sendMessage("Only player can execute this command.");
	}
}
