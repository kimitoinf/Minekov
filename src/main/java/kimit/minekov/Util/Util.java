package kimit.minekov.Util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class Util
{
	public static boolean IsNumberic(String string)
	{
		try
		{
			Long.parseLong(string);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}

	public static void GiveItem(Player player, ItemStack item)
	{
		if (player.getInventory().firstEmpty() == -1)
		{
			player.getWorld().dropItem(player.getLocation(), item);
			player.sendMessage(ChatColor.YELLOW + "아이템 " + item.getType() + " " + item.getAmount() + "개를 바닥에 드랍했습니다.");
		}
		else
		{
			player.getInventory().addItem(item);
			player.sendMessage(ChatColor.YELLOW + "아이템 " + item.getType() + " " + item.getAmount() + "개를 인벤토리에 지급했습니다.");
		}
	}

	public static void MakeFolder(String path)
	{
		File folder = new File(path);
		if (!folder.exists()) folder.mkdir();
	}

	public static int GetRandom(double[] probabilities)
	{
		double random = Math.random();
		double sum = 0;
		for (int loop = 0; loop != probabilities.length; loop++)
		{
			sum += probabilities[loop];
			if (sum > random)
				return loop;
		}
		return -1;
	}

	public static int IsContain(String dest, String[] compare)
	{
		for (int loop = 0; loop != compare.length; loop++)
			if (dest.contains(compare[loop]))
				return loop;
		return -1;
	}
}
