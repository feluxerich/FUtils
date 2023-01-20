package dev.fluxi.futils.settings;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.inventory.items.Toggleable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoHitDelay extends Toggleable {
    public NoHitDelay() {
        super(Material.FEATHER, coloredComponent("No Hit Delay"));
    }

    @EventHandler()
    public void onDamageByEntity(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof LivingEntity) || event.getCause() != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK || event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
            return;
        }
        Bukkit.getScheduler().runTaskLater(FUtils.getInstance(), () -> ((LivingEntity) event.getEntity()).setNoDamageTicks(0), 1);
    }
}
