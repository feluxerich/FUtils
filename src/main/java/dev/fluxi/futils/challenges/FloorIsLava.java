package dev.fluxi.futils.challenges;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.inventory.items.Toggleable;
import dev.fluxi.futils.utils.BlockUtils;
import dev.fluxi.futils.utils.ChallengeUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class FloorIsLava extends Toggleable {
    public FloorIsLava() {
        super(Material.MAGMA_BLOCK, coloredComponent("Floor is Lava"), "floor-is-lava");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!ChallengeUtils.shouldExecute(event.getPlayer())) {
            return;
        }
        Block block = BlockUtils.getBlockBelow(event.getTo().getBlock().getLocation());
        if (block.isLiquid() || BlockUtils.isAir(block) || !block.isSolid()) {
            return;
        }
        Bukkit.getScheduler().runTaskLater(FUtils.getInstance(), () -> setMagma(block), 20);
    }

    private void setMagma(Block block) {
        block.setType(Material.MAGMA_BLOCK);
        Bukkit.getScheduler().runTaskLater(FUtils.getInstance(), () -> setLava(block), 40);
    }

    private void setLava(Block block) {
        block.setType(Material.LAVA);
        Bukkit.getScheduler().runTaskLater(FUtils.getInstance(), () -> block.setType(Material.MAGMA_BLOCK), 40);
    }
}
