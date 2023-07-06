package kimit.minekov.Util.ConfigFile;

import kimit.minekov.Minekov;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile
{
	private final String FILENAME;
	private final File CONFIGFILE;
	private final FileConfiguration CONFIG;

	public ConfigFile(String filename)
	{
		FILENAME = filename;
		CONFIGFILE = new File(Bukkit.getPluginManager().getPlugin(Minekov.PLUGINNAME).getDataFolder(), FILENAME);
		CONFIG = YamlConfiguration.loadConfiguration(CONFIGFILE);
	}

	public FileConfiguration getConfig()
	{
		return CONFIG;
	}

	public void Open()
	{
		if (!CONFIGFILE.exists())
			CreateFile();
	}

	public void Close()
	{
		try
		{
			CONFIG.save(CONFIGFILE);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void CreateFile()
	{
		try
		{
			CONFIGFILE.createNewFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
