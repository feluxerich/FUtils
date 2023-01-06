package dev.fluxi.futils.worlds;

import org.bukkit.Material;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class PillarWorld extends ChunkGenerator {
    @Override
    public void generateNoise(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                if (x == 0 && z == 0) {
                    continue;
                }
                chunkData.setRegion(x, chunkData.getMinHeight(), z, x + 1, chunkData.getMaxHeight(), z + 1, Material.AIR);
            }
        }
    }
}
