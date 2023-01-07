package dev.fluxi.futils.worlds;

import dev.fluxi.futils.FUtils;
import org.bukkit.World;

import java.util.logging.Level;

public class WorldManager {
    public World createWorld(String name) {
        WorldGenerator worldGenerator = new WorldGenerator("world_" + name, new PillarWorld());
        World world = worldGenerator.createWorld();
        if (world == null) {
            FUtils.getInstance().getLogger().log(Level.INFO, "Could not load World: world_" + name);
            return null;
        }
        return world;
    }
}
