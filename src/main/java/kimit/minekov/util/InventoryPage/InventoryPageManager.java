package kimit.minekov.util.InventoryPage;

import kimit.minekov.util.ConfigFile.ConfigFile;

import java.util.HashMap;

public class InventoryPageManager
{
	private HashMap<String, InventoryPage> INVENTORYPAGES = new HashMap<String, InventoryPage>();
	//private HashMap<String, ConfigFile> INVENTORYPAGESCONFIG = new HashMap<String, ConfigFile>();

	public void Register(String key, String name)
	{
		INVENTORYPAGES.put(key, new InventoryPage(name));
	}

	public HashMap<String, InventoryPage> getInventoryPages()
	{
		return INVENTORYPAGES;
	}
}
