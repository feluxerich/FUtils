package dev.fluxi.futils.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class BlockUtils {
    public static boolean isAir(Block block) {
        return block.getType() == Material.AIR || block.getType() == Material.VOID_AIR || block.getType() == Material.CAVE_AIR;
    }

    public static Block getBlockBelow(Location location) {
        return location.getWorld().getBlockAt(location.subtract(new Vector(0, 1, 0)));
    }
}
