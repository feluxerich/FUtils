package dev.fluxi.futils.challenges;

import dev.fluxi.futils.utils.challenge.Challenge;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import java.util.Objects;

public class LevelBorder extends Challenge {
    Location spawn;
    WorldBorder worldBorder;
    public LevelBorder() {
        challengeName = "LevelBorder";
        challengeItemMaterial = Material.EXPERIENCE_BOTTLE;
        World world = Objects.requireNonNull(Bukkit.getWorld("world"));
        this.spawn = world.getSpawnLocation();
        this.worldBorder = world.getWorldBorder();

    }

    @EventHandler
    public void onPlayerLevelChange(PlayerLevelChangeEvent event) {
        if (event.getOldLevel() > event.getNewLevel()) {
            return;
        }
        worldBorder.setSize(worldBorder.getSize() + event.getNewLevel() - event.getOldLevel() + 1, 2);
    }

    @Override
    public void enable() {
        super.enable();
        worldBorder.setSize(1);
        worldBorder.setCenter(spawn.toCenterLocation());
    }

    @Override
    public void disable() {
        super.disable();
        worldBorder.reset();
    }
}
