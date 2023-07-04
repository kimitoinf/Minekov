package kimit.minekov.util.ConfigFile;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigFileProvider
{
	protected final String FILENAME;
	protected final ConfigFile CONFIGFILE;
	protected final FileConfiguration CONFIG;

	public ConfigFileProvider(String filename)
	{
		FILENAME = filename;
		CONFIGFILE = new ConfigFile(FILENAME);
		CONFIGFILE.Open();
		CONFIG = CONFIGFILE.getConfig();
	}

	public void Save()
	{
		CONFIGFILE.Close();
	}
}
