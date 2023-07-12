package kimit.minekov.Command;

import kimit.minekov.Market.Market;
import kimit.minekov.Menu.Menu;
import kimit.minekov.Minekov;
import kimit.minekov.PlayerInfo.PlayerInfo;
import kimit.minekov.Raid.RaidInitializer;
import kimit.minekov.Raid.RaidLoot;
import kimit.minekov.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Commands implements CommandExecutor
{
	public static final String[] COMMANDS = {"spawn", "escape", "loot", "raid", "getgold", "setgold", "gold", "lootitem", "lootchest", "receive", "market", "sell", "shop", "mob", "initializer", "menu"};
	public static final Executor[] EXECUTORS = {new RaidPointExecutor(Minekov.RAIDSPAWN), new RaidPointExecutor(Minekov.RAIDESCAPE), new RaidPointExecutor(Minekov.RAIDLOOT),
			new Executor()
			{
				@Override
				public void Run(CommandSender sender, String[] args)
				{
					if (sender instanceof Player)
					{
						if (Minekov.PLAYERS.get(((Player)sender).getUniqueId()).isInRaid())
						{
							sender.sendMessage(INRAID_ERROR);
							return;
						}
						Player player = (Player)sender;
						if (Minekov.RAIDSPAWN.RaidPointList.size() == 0)
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
			},
			new Executor()
			{
				@Override
				public void Run(CommandSender sender, String[] args)
				{
					if (args.length != 1)
					{
						sender.sendMessage(ARGUMENTS_ERROR);
						return;
					}
					Player player = Bukkit.getPlayer(args[0]);
					if (player == null)
						sender.sendMessage(ARGUMENTS_ERROR);
					else
						sender.sendMessage(Long.toString(Minekov.PLAYERS.get(player.getUniqueId()).getGold()));
				}
			},
			new Executor()
			{
				@Override
				public void Run(CommandSender sender, String[] args)
				{
					if (args.length != 2)
					{
						sender.sendMessage(ARGUMENTS_ERROR);
						return;
					}
					Player player = Bukkit.getPlayer(args[0]);
					if (player == null || !Util.IsNumberic(args[1]))
						sender.sendMessage(ARGUMENTS_ERROR);
					else
					{
						Minekov.PLAYERS.get(player.getUniqueId()).setGold(Long.parseLong(args[1]));
						sender.sendMessage("Player " + player.getName() + "'s gold is now " + Long.parseLong(args[1]));
						Minekov.PLAYERS.get(player.getUniqueId()).UpdateBoard();
					}
				}
			},
			new Executor()
			{
				@Override
				public void Run(CommandSender sender, String[] args)
				{
					if (args.length != 1 || !Util.IsNumberic(args[0]))
						sender.sendMessage(ARGUMENTS_ERROR);
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
						sender.sendMessage(NOT_PLAYER_ERROR);
				}
			},
			new Executor()
			{
				@Override
				public void Run(CommandSender sender, String[] args)
				{
					if (sender instanceof Player)
					{
						for (int loop = 0; loop != 9; loop++)
							Util.GiveItem((Player)sender, new RaidLoot().GetLootItem());
					}
				}
			},
			new Executor()
			{
				@Override
				public void Run(CommandSender sender, String[] args)
				{
					if (Minekov.RAIDLOOT.RaidPointList.size() == 0)
					{
						sender.sendMessage("Error: No raid loot point.");
						return;
					}

					for (Location loop : Minekov.RAIDLOOT.RaidPointList)
					{
						Block block = loop.getBlock();
						block.setType(Material.CHEST);
						((Chest)block.getState()).getBlockInventory().addItem(new RaidLoot().GetLootChest());
					}
				}
			},
			new Executor()
			{
				@Override
				public void Run(CommandSender sender, String[] args)
				{
					if (sender instanceof Player)
					{
						if (Minekov.PLAYERS.get(((Player)sender).getUniqueId()).isInRaid())
						{
							sender.sendMessage(INRAID_ERROR);
							return;
						}
						Player player = (Player)sender;
						Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(player.getUniqueId().toString()).OpenInventory(player);
					}
				}
			},
			new Executor()
			{
				@Override
				public void Run(CommandSender sender, String[] args)
				{
					if (sender instanceof Player)
					{
						if (Minekov.PLAYERS.get(((Player)sender).getUniqueId()).isInRaid())
						{
							sender.sendMessage(INRAID_ERROR);
							return;
						}
						Minekov.MARKET.Open((Player) sender);
					}
				}
			},
			new Executor()
			{
				@Override
				public void Run(CommandSender sender, String[] args)
				{
					if (sender instanceof Player)
					{
						if (Minekov.PLAYERS.get(((Player)sender).getUniqueId()).isInRaid())
						{
							sender.sendMessage(INRAID_ERROR);
							return;
						}
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
			},
			new Executor()
			{
				@Override
				public void Run(CommandSender sender, String[] args)
				{
					if (sender instanceof Player)
					{
						if (Minekov.PLAYERS.get(((Player)sender).getUniqueId()).isInRaid())
						{
							sender.sendMessage(INRAID_ERROR);
							return;
						}
						((Player)sender).openInventory(Minekov.Shop.getShop());
					}
					else sender.sendMessage(NOT_PLAYER_ERROR);
				}
			},
			new Executor()
			{
				@Override
				public void Run(CommandSender sender, String[] args)
				{
					if (args.length != 4 || !Util.IsNumberic(args[1]) || !Util.IsNumberic(args[2]) || !Util.IsNumberic(args[3]) || !args[0].equals("first") && !args[0].equals("last"))
					{
						sender.sendMessage(ARGUMENTS_ERROR);
						return;
					}
					if (args[0].equals("first")) Minekov.RAIDCONFIG.V_FIRST_SPAWN_POINT = new Location(Bukkit.getWorlds().get(0), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
					else Minekov.RAIDCONFIG.V_LAST_SPAWN_POINT = new Location(Bukkit.getWorlds().get(0), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
					sender.sendMessage("Spawn area point was saved.");
				}
			},
			new Executor()
			{
				@Override
				public void Run(CommandSender sender, String[] args)
				{
					if (args.length != 1 || !args[0].equals("false") && !args[0].equals("true"))
					{
						sender.sendMessage(ARGUMENTS_ERROR);
						return;
					}
					if (args[0].equals("false")) RaidInitializer.Initializing = false;
					else RaidInitializer.Initializing = true;
					sender.sendMessage("RaidInitializer.Initializing is now " + RaidInitializer.Initializing);
				}
			},
			new Executor()
			{
				@Override
				public void Run(CommandSender sender, String[] args)
				{
					if (sender instanceof Player)
					{
						if (Minekov.PLAYERS.get(((Player)sender).getUniqueId()).isInRaid())
						{
							sender.sendMessage(INRAID_ERROR);
							return;
						}
						Menu menu = new Menu();
						((Player)sender).openInventory(menu.getMenu());
					}
				}
			}
	};

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		for (int loop = 0; loop != COMMANDS.length; loop++)
			if (label.equals(COMMANDS[loop]))
				EXECUTORS[loop].Run(sender, args);

		return true;
	}
}