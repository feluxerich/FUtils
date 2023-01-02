package dev.fluxi.futils.challenges;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityRegainHealthEvent;


public class UltraUltraHardcore extends UltraHardcore {
    public UltraUltraHardcore() {
        challengeName = "UltraUltraHardcore";
    }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        event.setCancelled(true);
    }
}
