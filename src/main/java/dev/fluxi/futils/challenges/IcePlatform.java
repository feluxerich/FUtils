package dev.fluxi.futils.challenges;

import dev.fluxi.futils.inventory.items.Toggleable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class IcePlatform extends Toggleable {
    private boolean active = false;

    public IcePlatform() {
        super(Material.BLUE_ICE, coloredComponent("Ice Platform"));
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        if (!event.isSneaking()) {
            return;
        }
        active = !active;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!active) {
            return;
        }
        Block block = event.getTo().getBlock();
        Location blockLocation = block.getLocation();
        blockLocation.setY(blockLocation.getBlockY() - 1);
        setCircle(blockLocation, 2);
    }

    private void setCircle(Location location, int radius) {
        for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                if ((location.getBlockX() - x) * (location.getBlockX() - x) + (location.getBlockZ() - z) * (location.getBlockZ() - z) <= radius * radius) {
                    location.getWorld().getBlockAt(x, location.getBlockY(), z).setType(Material.BLUE_ICE);
                }
            }
        }
    }
}
