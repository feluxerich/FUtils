package dev.fluxi.futils.challenges;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class UltraUltraHardcore extends UltraHardcore {
    public UltraUltraHardcore() {
        name("UltraUltraHardcore");
        itemMaterial(Material.ENCHANTED_GOLDEN_APPLE);
    }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        event.setCancelled(true);
    }
}
