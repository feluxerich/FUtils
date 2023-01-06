package dev.fluxi.futils.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Arrays;

public class PlayerPortalListener implements Listener {
    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        if (Arrays.asList("world", "world_nether", "world_the_end").contains(event.getFrom().getWorld().getName())) {
            return;
        }
        Player player = event.getPlayer();

        if (event.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
            event.setCanCreatePortal(true);
            Location location;
            if (!player.getWorld().getName().endsWith("_nether")) {
                location = new Location(Bukkit.getWorld(player.getWorld().getName() + "_nether"),
                        event.getFrom().getBlockX() / 8F,
                        event.getFrom().getBlockY(),
                        event.getFrom().getBlockZ() / 8F);
            } else {
                location = new Location(Bukkit.getWorld(player.getWorld().getName().split("_")[0]),
                        event.getFrom().getBlockX() * 8,
                        event.getFrom().getBlockY(),
                        event.getFrom().getBlockZ() * 8);
            }
            event.setTo(location);
            return;
        }

        if (event.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL) {
            if (player.getWorld().getName().endsWith("_the_end")) {
                return;
            }
            Location location = new Location(Bukkit.getWorld(player.getWorld().getName() + "_the_end"), 100, 50, 0);
            event.setTo(location);
        }
    }
}
