package dev.fluxi.futils.settings;

import dev.fluxi.futils.gui.GuiAccessible;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.world.WorldLoadEvent;

public class UltraHardcore extends GuiAccessible {
    World world;

    public UltraHardcore() {
        name("Ultra Hardcore");
        itemMaterial(Material.GOLDEN_APPLE);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        event.getPlayer().setGameMode(GameMode.SPECTATOR);
    }

    @Override
    public void enable() {
        world = Bukkit.getWorld("world");
        if (world == null) {
            return;
        }
        world.setGameRule(GameRule.NATURAL_REGENERATION, false);
        super.enable();
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
