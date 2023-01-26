package dev.fluxi.futils.utils;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BlockUtils {
    public static boolean isAir(Block block) {
        return block.getType() == Material.AIR || block.getType() == Material.VOID_AIR || block.getType() == Material.CAVE_AIR;
    }

    public static Location getBelow(Location location) {
        return new Location(location.getWorld(), location.getBlockX(), location.getBlockY() - 1, location.getBlockZ());
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
    }

    public static boolean isSameBlock(Location location1, Location location2) {
        boolean world = location1.getWorld() == location2.getWorld();
        boolean x = location1.getBlockX() == location2.getBlockX();
        boolean y = location1.getBlockY() == location2.getBlockY();
        boolean z = location1.getBlockZ() == location2.getBlockZ();
        return world && x && y && z;
    }

    public static boolean isInAir(Player player) {
        return BlockUtils.isAir(getBelow(player.getLocation()).getBlock());
    }
}
