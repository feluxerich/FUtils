package dev.fluxi.futils.challenges;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.inventory.items.Toggleable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakVoid extends Toggleable {
    public BlockBreakVoid() {
        super(Material.NETHERITE_PICKAXE, coloredComponent("Block Break Void"));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!FUtils.getInstance().getTimer().running()) {
            return;
        }
        Block block = event.getBlock();
        World world = block.getWorld();
        for (ItemStack drop : block.getDrops()) {
            world.dropItemNaturally(block.getLocation(), drop);
        }
        for (int i = world.getMinHeight(); i < world.getMaxHeight(); i++) {
            Location location = block.getLocation();
            location.setY(i);
            world.setBlockData(location, Material.AIR.createBlockData());
        }
    }
}
