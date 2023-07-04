package kimit.minekov.util.InventoryPage;

import kimit.minekov.Minekov;
import kimit.minekov.PlayerInfo.PlayerInfo;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryPageEventHandler implements Listener
{
	@EventHandler
	public void ClickInventory(InventoryClickEvent e)
	{
		if ((e.getView().getTitle().equals(PlayerInfo.RECEIVE) || Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(e.getView().getTitle()) != null) && e.getClickedInventory() != null && e.getClickedInventory().equals(e.getView().getTopInventory()))
		{
			InventoryPage ip = null;
			Player player = (Player)e.getWhoClicked();
			if (Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(e.getView().getTitle()) != null)
				ip = Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(e.getView().getTitle());
			else if (Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(player.getUniqueId().toString()) != null)
				ip = Minekov.INVENTORYPAGEMANAGER.getInventoryPages().get(player.getUniqueId().toString());

			int page = Integer.parseInt(e.getClickedInventory().getItem(InventoryPage.CURRENTPOS).getItemMeta().getLore().get(0).split(" ")[0]) - 1;
			final int index = e.getSlot();

			int count = 0;
			for (ItemStack loop : ip.getInventories().get(page).getContents())
			{
				if (loop != null && loop.getType() != Material.AIR)
					count++;
			}
			if (count == 2 && page != 0)
			{
				page--;
				player.openInventory(ip.getInventories().get(page));
				ip.RemovePage();
			}

			if (index == InventoryPage.PREVIOUSPOS && page != 0)
			{
				page--;
				player.openInventory(ip.getInventories().get(page));
			}
			else if (index == InventoryPage.NEXTPOS && page != ip.getInventories().size() - 1)
			{
				page++;
				player.openInventory(ip.getInventories().get(page));
			}

			if (index > InventoryPage.LIMITPOS)
				e.setCancelled(true);
		}
	}
}