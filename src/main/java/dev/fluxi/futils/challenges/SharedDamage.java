package dev.fluxi.futils.challenges;

import dev.fluxi.futils.utils.challenge.Challenge;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class SharedDamage extends Challenge {
    public SharedDamage() {
        challengeName = "SharedDamage";
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player) || event.getCause().equals(EntityDamageEvent.DamageCause.CUSTOM)) {
            return;
        }
        Player player = (Player) event.getEntity();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer == player) {
                continue;
            }
            onlinePlayer.damage(event.getDamage());
        }
    }
}