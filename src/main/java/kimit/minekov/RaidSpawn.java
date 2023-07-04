package kimit.minekov;

import kimit.minekov.util.ConfigFile.ConfigFileProvider;
import org.bukkit.Location;

import java.util.ArrayList;

public class RaidSpawn extends ConfigFileProvider
{
	private static final String COUNT = "Count";
	public ArrayList<Location> RaidSpawnList = new ArrayList<>();

	public RaidSpawn(String filename)
	{
		super(filename);
		for (int loop = 0; loop != CONFIG.getInt(COUNT); loop++)
			RaidSpawnList.add(CONFIG.getLocation(Integer.toString(loop)));
	}

	@Override
	public void Save()
	{
		CONFIG.set(COUNT, RaidSpawnList.size());
		for (int loop = 0; loop != RaidSpawnList.size(); loop++)
			CONFIG.set(Integer.toString(loop), RaidSpawnList.get(loop));
		super.Save();
	}
}
