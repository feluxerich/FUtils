package dev.fluxi.futils.settings;

import dev.fluxi.futils.gui.GuiAccessible;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class SharedDamage extends GuiAccessible {
    public SharedDamage() {
        name("Shared Damage");
        itemMaterial(Material.CHAIN);
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
