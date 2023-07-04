package kimit.minekov.PlayerInfo;

import kimit.minekov.Minekov;
import kimit.minekov.util.ConfigFile.ConfigFileProvider;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class PlayerInfo extends ConfigFileProvider
{
	private final UUID PLAYERUUID;
	public static final String RECEIVE = "Receive";
	public static final String RECEIVECOUNT = RECEIVE + ".Count";

	public static final String CASHCONFIG = "Cash";
	private long CASH = 0;
	public boolean ONMARKETSELL = false;
	public ArrayList<ItemStack> MARKETSELL = new ArrayList<ItemStack>();

	public PlayerInfo(UUID uuid)
	{
		super(Minekov.PLAYERSFOLDER + File.separator + uuid.toString() + ".yml");
		PLAYERUUID = uuid;
		Minekov.INVENTORYPAGEMANAGER.Register(PLAYERUUID.toString(), RECEIVE);

		for (int loop = 0; loop != CONFIG.getInt(RECEIVECOUNT); loop++)
			Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(PLAYERUUID.toString()).AddItem(CONFIG.getItemStack(RECEIVE + "." + Integer.toString(loop)));

		CASH = CONFIG.getLong(CASHCONFIG);
	}

	@Override
	public void Save()
	{
		CONFIG.set(CASHCONFIG, CASH);

		ArrayList<ItemStack> items = Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(PLAYERUUID.toString()).getItems();
		CONFIG.set(RECEIVECOUNT, items.size());
		for (int loop = 0; loop != items.size(); loop++)
			CONFIG.set(RECEIVE + "." + loop, items.get(loop));
		super.Save();
	}

	public UUID getUUID()
	{
		return PLAYERUUID;
	}

	public long getCash()
	{
		return CASH;
	}

	public void setCash(long cash)
	{
		CASH = cash;
	}

	public void addCash(long cash)
	{
		CASH += cash;
	}
}
