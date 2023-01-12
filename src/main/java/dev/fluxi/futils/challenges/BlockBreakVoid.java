package dev.fluxi.futils.challenges;

import dev.fluxi.futils.gui.GuiAccessible;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakVoid extends GuiAccessible {
    public BlockBreakVoid() {
        name("Block Break Void");
        itemMaterial(Material.NETHERITE_PICKAXE);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
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
