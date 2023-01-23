package dev.fluxi.futils.challenges;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.inventory.items.Toggleable;
import dev.fluxi.futils.utils.ChallengeUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class FallingBlocks extends Toggleable {
    public FallingBlocks() {
        super(Material.SAND, coloredComponent("Falling Blocks"));
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!ChallengeUtils.shouldExecute(event.getPlayer())) {
            return;
        }
        Block block = event.getTo().getBlock();
        Location blockLocation = block.getLocation();
        blockLocation.setY(blockLocation.getBlockY() - 1);

        Block blockBelow = block.getWorld().getBlockAt(blockLocation);
        Bukkit.getScheduler().runTaskLater(FUtils.getInstance(), () -> boostBlockInAir(blockBelow), 20);

    }

    private void boostBlockInAir(Block block) {
        if (!block.getType().isSolid()) return;

        FallingBlock fallingBlock = block.getWorld()
                .spawnFallingBlock(block.getLocation().add(0.5, 0, 0.5), block.getBlockData());
        fallingBlock.setInvulnerable(true);
        fallingBlock.setVelocity(new Vector(0, 1, 0));

        block.setType(Material.AIR);
    }
}
