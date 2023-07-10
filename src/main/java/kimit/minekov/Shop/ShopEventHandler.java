package kimit.minekov.Shop;

import kimit.minekov.Minekov;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ShopEventHandler implements Listener
{
	@EventHandler
	public void ClickInventory(InventoryClickEvent e)
	{
		if (e.getClickedInventory() != null && e.getView().getTitle().equals(Shop.NAME) && e.getClickedInventory().equals(e.getView().getBottomInventory()))
			e.setCancelled(true);
		else if (e.getClickedInventory() != null && e.getView().getTitle().equals(Shop.NAME) && e.getClickedInventory().equals(e.getView().getTopInventory()) && e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR)
		{
			e.setCancelled(true);
			for (int loop = 0; loop != Shop.SHOP_POS.length; loop++)
				if (e.getSlot() == Shop.SHOP_POS[loop])
					Minekov.Shop.getShopPage().get(loop).OpenInventory((Player)e.getWhoClicked());
			return;
		}
		if (e.getClickedInventory() == null) return;
		for (int loop = 0; loop != Shop.SHOP_NAME.length; loop++)
		{
			if (e.getView().getTitle().equals(Shop.SHOP_NAME[loop]) && e.getClickedInventory().equals(e.getView().getTopInventory()) && e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR)
			{

			}
		}
	}
}