package kimit.minekov.Menu;

import kimit.minekov.Command.Commands;
import kimit.minekov.Minekov;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class MenuEventHandler implements Listener
{
	@EventHandler
	public void ClickInventory(InventoryClickEvent e)
	{
		if (e.getClickedInventory() != null && e.getView().getTitle().equals(Menu.NAME) && e.getClickedInventory().equals(e.getView().getBottomInventory()))
			e.setCancelled(true);
		else if (e.getClickedInventory() != null && e.getView().getTitle().equals(Menu.NAME) && e.getClickedInventory().equals(e.getView().getTopInventory()) && e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR)
		{
			e.setCancelled(true);
			switch (e.getSlot())
			{
				case 0:
					Commands.EXECUTORS[3].Run(e.getWhoClicked(), null);
					e.getWhoClicked().closeInventory();
					break;
				case 1:
					Commands.EXECUTORS[12].Run(e.getWhoClicked(), null);
					break;
				case 2:
					Commands.EXECUTORS[10].Run(e.getWhoClicked(), null);
					break;
				case 3:
					Commands.EXECUTORS[11].Run(e.getWhoClicked(), null);
					break;
				case 4:
					Commands.EXECUTORS[9].Run(e.getWhoClicked(), null);
					break;
			}
		}
	}

	@EventHandler
	public void OpenMenu(PlayerSwapHandItemsEvent e)
	{
		if (e.getPlayer().isSneaking() && !Minekov.PLAYERS.get(e.getPlayer().getUniqueId()).isInRaid())
		{
			Menu menu = new Menu();
			e.getPlayer().openInventory(menu.getMenu());
		}
	}
}
