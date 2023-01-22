package dev.fluxi.futils.listeners;

import dev.fluxi.futils.FUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!FUtils.getInstance().getInventoryManager().containsPlayer(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockDestroy(BlockBreakEvent event) {
        if (!FUtils.getInstance().getInventoryManager().containsPlayer(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPhysics(BlockPhysicsEvent event) {
        if (event.getBlock().getChunk().getInhabitedTime() > 0) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        if (event.getBlock().getChunk().getInhabitedTime() > 0) {
            return;
        }
        event.setCancelled(true);
    }
}
