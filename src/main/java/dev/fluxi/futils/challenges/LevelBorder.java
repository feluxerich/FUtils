package dev.fluxi.futils.challenges;

import dev.fluxi.futils.gui.GuiAccessible;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import java.util.Objects;

public class LevelBorder extends GuiAccessible {
    World world;
    public LevelBorder() {
        name("Level Border");
        itemMaterial(Material.EXPERIENCE_BOTTLE);
    }

    @EventHandler
    public void onPlayerLevelChange(PlayerLevelChangeEvent event) {
        if (event.getOldLevel() > event.getNewLevel()) {
            return;
        }
        world.getWorldBorder().setSize(world.getWorldBorder().getSize() + event.getNewLevel() - event.getOldLevel(), 2);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setShouldDropExperience(false);
    }

    @Override
    public void enable() {
        super.enable();

        world = Objects.requireNonNull(Bukkit.getWorld("world"));
        world.getWorldBorder().setSize(1);
        world.getWorldBorder().setCenter(world.getSpawnLocation().toCenterLocation());
    }

    @Override
    public void disable() {
        super.disable();
        world.getWorldBorder().reset();
    }
}
