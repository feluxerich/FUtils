package dev.fluxi.futils.worlds;

import org.bukkit.World;
import org.bukkit.WorldCreator;

public class WorldGenerator {
    private final WorldCreator worldCreator;
    private WorldCreator netherWorldCreator;
    private WorldCreator theEndWorldCreator;
    private boolean nether = true;
    private boolean end = true;

    public WorldGenerator(String name, ChunkGenerator generator) {
        worldCreator = new WorldCreator(name).generator(generator);
        if (nether()) {
            netherWorldCreator = new WorldCreator(name + "_nether").generator(generator).environment(World.Environment.NETHER);
        }
        if (end()) {
            theEndWorldCreator = new WorldCreator(name + "_the_end").generator(generator).environment(World.Environment.THE_END);
        }
    }

    public World createWorld() {
        World world = worldCreator.createWorld();
        if (nether()) {
            netherWorldCreator.createWorld();
        }
        if (end()) {
            theEndWorldCreator.createWorld();
        }
        return world;
    }

    public boolean nether() {
        return nether;
    }

    public void nether(boolean nether) {
        this.nether = nether;
    }

    public boolean end() {
        return end;
    }

    public void end(boolean end) {
        this.end = end;
    }
}
