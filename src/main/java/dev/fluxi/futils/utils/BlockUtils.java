package dev.fluxi.futils.utils;

import org.bukkit.Chunk;
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

    public static Block getBlockInChunk(Chunk chunk, Location location) {
        return chunk.getBlock(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public static Location getInChunkCoordinates(Location location) {
        int x = location.getBlockX() % 16;
        int z = location.getBlockZ() % 16;
        if (x < 0) {
            x = Math.abs(Math.abs(x) - 16);
        }
        if (z < 0) {
            z = Math.abs(Math.abs(z) - 16);
        }
        return new Location(location.getWorld(), x, location.getBlockY(), z);
//        return new Location(
//                location.getWorld(),
//                Math.abs(Math.abs(location.getBlockX() % 16) - 16),
//                location.getBlockY(),
//                Math.abs(Math.abs(location.getBlockZ() % 16) - 16)
//        );
//        return new Location(
//                location.getWorld(),
//                Math.abs(location.getBlockX() % 16),
//                location.getBlockY(),
//                Math.abs(location.getBlockZ() % 16)
//        );
    }
}
