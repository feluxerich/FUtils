package dev.fluxi.futils.settings;

import dev.fluxi.futils.events.PlayerDamageEvent;
import dev.fluxi.futils.settings.utils.ToggleableSetting;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class NoFallDamageSetting extends ToggleableSetting {
    public NoFallDamageSetting() {
        super(Material.DIAMOND_BOOTS, coloredComponent("No Fall Damage"), "no-fall-damage");
    }

    @EventHandler
    public void onFall(PlayerDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        event.setCancelled(true);
    }
}
