package kimit.minekov.Util;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Predicate;

public class Timer extends BukkitRunnable
{
	private int Second = 0;
	private Predicate<Integer> EverySecond;
	private Runnable EndTimer;

	public Timer(int second, Predicate<Integer> everySecond, Runnable endTimer)
	{
		Second = second;
		EverySecond = everySecond;
		EndTimer = endTimer;
	}

	public void Start(Plugin plugin)
	{
		this.runTaskTimer(plugin, 0, 20);
	}

	@Override
	public void run()
	{
		EverySecond.test(Second);
		Second--;
		if (Second < 0)
		{
			EndTimer.run();
			this.cancel();
		}
	}

	public int getSecond()
	{
		return Second;
	}
}
