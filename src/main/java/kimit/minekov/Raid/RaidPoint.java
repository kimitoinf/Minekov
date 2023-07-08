package kimit.minekov.Raid;

import kimit.minekov.Util.ConfigFile.ConfigFileProvider;
import org.bukkit.Location;

import java.util.ArrayList;

public class RaidPoint extends ConfigFileProvider
{
	private static final String COUNT = "Count";
	public final ArrayList<Location> RaidPointList = new ArrayList<>();

	public RaidPoint(String filename)
	{
		super(filename);
		for (int loop = 0; loop != CONFIG.getInt(COUNT); loop++)
			RaidPointList.add(CONFIG.getLocation(Integer.toString(loop)));
	}

	@Override
	public void Save()
	{
		CONFIG.set(COUNT, RaidPointList.size());
		for (int loop = 0; loop != RaidPointList.size(); loop++)
			CONFIG.set(Integer.toString(loop), RaidPointList.get(loop));
		super.Save();
	}
}
