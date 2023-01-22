package dev.fluxi.futils.listeners;

import dev.fluxi.futils.FUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;

public class InventoryListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!FUtils.getInstance().getInventoryManager().containsPlayer((Player) event.getWhoClicked())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent event) {
        if (!FUtils.getInstance().getInventoryManager().containsPlayer((Player) event.getWhoClicked())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (!FUtils.getInstance().getInventoryManager().containsPlayer((Player) event.getWhoClicked())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onPickUpArrow(PlayerPickupArrowEvent event) {
        if (!FUtils.getInstance().getInventoryManager().containsPlayer(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player) || !FUtils.getInstance().getInventoryManager().containsPlayer((Player) event.getEntity())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (!FUtils.getInstance().getInventoryManager().containsPlayer(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }
}
