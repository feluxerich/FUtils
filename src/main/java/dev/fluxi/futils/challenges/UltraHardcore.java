package dev.fluxi.futils.challenges;

import dev.fluxi.futils.utils.challenge.Challenge;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import java.util.Objects;

public class UltraHardcore extends Challenge {
    public UltraHardcore() {
        challengeName = "UltraHardcore";
        Objects.requireNonNull(Bukkit.getWorld("world")).setGameRule(GameRule.NATURAL_REGENERATION, false);
    }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        if (event.getRegainReason() == EntityRegainHealthEvent.RegainReason.MAGIC_REGEN || event.getRegainReason() == EntityRegainHealthEvent.RegainReason.MAGIC) {
            return;
        }
        event.setCancelled(true);
    }
}
