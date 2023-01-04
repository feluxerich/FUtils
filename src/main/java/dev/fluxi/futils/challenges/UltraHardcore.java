package dev.fluxi.futils.challenges;

import dev.fluxi.futils.utils.challenge.Challenge;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Objects;

public class UltraHardcore extends Challenge {
    World world;
    public UltraHardcore() {
        challengeName = "UltraHardcore";
        challengeItemMaterial = Material.GOLDEN_APPLE;
        this.world = Objects.requireNonNull(Bukkit.getWorld("world"));
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        event.getPlayer().setGameMode(GameMode.SPECTATOR);
    }

    @Override
    public void enable() {
        super.enable();
        world.setGameRule(GameRule.NATURAL_REGENERATION, false);
    }

    @Override
    public void disable() {
        super.disable();
        world.setGameRule(GameRule.NATURAL_REGENERATION, true);
    }
}
