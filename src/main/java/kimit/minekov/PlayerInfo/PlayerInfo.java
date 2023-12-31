package kimit.minekov.PlayerInfo;

import de.tr7zw.nbtapi.NBTItem;
import kimit.minekov.Minekov;
import kimit.minekov.Raid.Raid;
import kimit.minekov.Util.ConfigFile.ConfigFileProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerInfo extends ConfigFileProvider
{
	private final UUID PLAYERUUID;
	public static final String RECEIVE = "Receive";
	public static final String RECEIVECOUNT = RECEIVE + ".Count";

	public static final String GOLDCONFIG = "Gold";
	private long Gold = 0;
	public boolean OnMarketSell = false;
	public ArrayList<ItemStack> MarketSell = new ArrayList<ItemStack>();
	private Scoreboard Board;
	private static final String BOARDNAME = "board";
	public static final Material GOLDMATERIAL = Material.PAPER;
	public static final String INRAIDCONFIG = "Raid.InRaid";
	public static final String RAIDTIMECONFIG = "Raid.RaidTime";
	private boolean InRaid = false;
	private int RaidTime = 0;
	public final Raid RAID;

	public PlayerInfo(UUID uuid)
	{
		super(Minekov.PLAYERSFOLDER + File.separator + uuid.toString() + ".yml");
		PLAYERUUID = uuid;
		Minekov.INVENTORYPAGEMANAGER.Register(PLAYERUUID.toString(), RECEIVE);

		for (int loop = 0; loop != CONFIG.getInt(RECEIVECOUNT); loop++)
			Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(PLAYERUUID.toString()).AddItem(CONFIG.getItemStack(RECEIVE + "." + loop));

		Gold = CONFIG.getLong(GOLDCONFIG);

		InRaid = CONFIG.getBoolean(INRAIDCONFIG);
		RaidTime = CONFIG.getInt(RAIDTIMECONFIG);

		Board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective objective = Board.registerNewObjective(BOARDNAME, Criteria.DUMMY, Minekov.PLUGINNAME);
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		UpdateBoard();
		RAID = new Raid(Bukkit.getPlayer(PLAYERUUID));
	}

	@Override
	public void Save()
	{
		CONFIG.set(GOLDCONFIG, Gold);

		ArrayList<ItemStack> items = Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(PLAYERUUID.toString()).getItems();
		CONFIG.set(RECEIVECOUNT, items.size());
		for (int loop = 0; loop != items.size(); loop++)
			CONFIG.set(RECEIVE + "." + loop, items.get(loop));
		CONFIG.set(INRAIDCONFIG, InRaid);
		CONFIG.set(RAIDTIMECONFIG, RaidTime);
		super.Save();
	}

	public void UpdateBoard()
	{
		Objective objective = Board.getObjective(BOARDNAME);
		objective.unregister();
		objective = Board.registerNewObjective(BOARDNAME, Criteria.DUMMY, Minekov.PLUGINNAME);
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.getScore(ChatColor.GOLD + "Gold: " + Gold).setScore(3);
		objective.getScore(ChatColor.GRAY + "Receives: " + Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(PLAYERUUID.toString()).getItems().size()).setScore(2);
		if (InRaid) objective.getScore(ChatColor.BLUE + "RAID: " + RaidTime / 60 + "m " + RaidTime % 60 + "s").setScore(1);
		else objective.getScore(ChatColor.BLUE + "RAID: Not in raid.").setScore(1);
		Bukkit.getPlayer(PLAYERUUID).setScoreboard(Board);
	}

	public static ItemStack GoldItem(long gold)
	{
		ItemStack item = new ItemStack(GOLDMATERIAL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + Long.toString(gold) + " Gold");
		meta.setLore(List.of("우클릭하여 사용"));
		item.setItemMeta(meta);
		NBTItem nbt = new NBTItem(item);
		nbt.setLong(GOLDCONFIG, gold);
		nbt.applyNBT(item);
		return item;
	}

	public UUID getUUID()
	{
		return PLAYERUUID;
	}

	public long getGold()
	{
		return Gold;
	}

	public void setGold(long gold)
	{
		Gold = gold;
	}

	public void addGold(long gold)
	{
		Gold += gold;
	}

	public Scoreboard getBoard()
	{
		return Board;
	}

	public boolean isInRaid()
	{
		return InRaid;
	}

	public void setInRaid(boolean inRaid)
	{
		InRaid = inRaid;
		if (!inRaid) RaidTime = 0;
	}

	public int getRaidTime()
	{
		return RaidTime;
	}

	public void setRaidTime(int raidTime)
	{
		RaidTime = raidTime;
	}
}
