package dev.fluxi.futils.challenges;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.inventory.items.Toggleable;
import dev.fluxi.futils.utils.ChallengeUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class BedrockWall extends Toggleable {
    public BedrockWall() {
        super(Material.BEDROCK, coloredComponent("Bedrock Wall"));
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!ChallengeUtils.shouldExecute(event.getPlayer())) {
            return;
        }
        Bukkit.getScheduler().runTaskLater(FUtils.getInstance(), () -> createWall(event.getTo().getBlock().getLocation()), 40);
    }

    private void createWall(Location location) {
        for (int y = location.getWorld().getMinHeight(); y < location.getWorld().getMaxHeight(); y++) {
            location.setY(y);
            location.getBlock().setType(Material.BEDROCK);
        }
    }
}
