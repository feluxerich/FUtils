package dev.fluxi.futils.challenges;

import dev.fluxi.futils.inventory.items.Toggleable;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageClearInventory extends Toggleable {
    public DamageClearInventory() {
        super(Material.ENDER_CHEST, coloredComponent("Damage Clear Inventory"));
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        player.getInventory().clear();
    }
}
