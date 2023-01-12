package dev.fluxi.futils.settings;

import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.world.WorldLoadEvent;

public class UltraHardcore extends Hardcore {
    public UltraHardcore() {
        name("Ultra Hardcore");
        itemMaterial(Material.GOLDEN_APPLE);
    }

    @Override
    public void enable() {
        super.enable();
        world = Bukkit.getWorld("world");
        if (world == null) {
            return;
        }
        world.setGameRule(GameRule.NATURAL_REGENERATION, false);

    }

    @Override
    public void disable() {
        super.disable();
        world = Bukkit.getWorld("world");
        if (world == null) {
            return;
        }
        world.setGameRule(GameRule.NATURAL_REGENERATION, true);
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        world = event.getWorld();
        world.setGameRule(GameRule.NATURAL_REGENERATION, false);
    }
}
