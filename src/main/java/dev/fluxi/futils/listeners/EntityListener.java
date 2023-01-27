package dev.fluxi.futils.listeners;

import dev.fluxi.futils.events.PlayerKillEntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityListener implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof LivingEntity) || !(event.getDamager() instanceof Player)) return;
        LivingEntity entity = (LivingEntity) event.getEntity();
        if (entity.getHealth() - event.getDamage() > 0) return;
        PlayerKillEntityEvent playerDamageEvent = new PlayerKillEntityEvent((Player) event.getDamager(), entity);
        Bukkit.getPluginManager().callEvent(playerDamageEvent);
    }
}
