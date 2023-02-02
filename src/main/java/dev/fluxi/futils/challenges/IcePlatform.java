package dev.fluxi.futils.challenges;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.challenges.utils.Challenge;
import dev.fluxi.futils.utils.ChallengeUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.ArrayList;
import java.util.List;

public class IcePlatform extends Challenge {
    private final List<Player> activeList = new ArrayList<>();

    public IcePlatform() {
        super(Material.BLUE_ICE, coloredComponent("Ice Platform"), "ice-platform");
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        if (!ChallengeUtils.shouldExecute(event.getPlayer())) {
            return;
        }
        if (!event.isSneaking()) {
            return;
        }
        togglePlayerActive(event.getPlayer());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!FUtils.getInstance().getTimer().running() || !active(event.getPlayer())) {
            return;
        }
        Block block = event.getTo().getBlock();
        Location blockLocation = block.getLocation();
        blockLocation.setY(blockLocation.getBlockY() - 1);
        setIce(blockLocation);
    }

    private void setIce(Location location) {
        int width = 3;
        int radius = (width - 1) / 2;
        for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                location.getWorld().getBlockAt(x, location.getBlockY(), z).setType(Material.BLUE_ICE);
            }
        }
    }

    private void togglePlayerActive(Player player) {
        if (activeList.contains(player)) {
            activeList.remove(player);
            return;
        }
        activeList.add(player);
    }

    private boolean active(Player player) {
        return activeList.contains(player);
    }
}
