package dev.fluxi.futils.listeners;

import dev.fluxi.futils.FUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!FUtils.getInstance().getInventoryManager().containsPlayer(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }
}
