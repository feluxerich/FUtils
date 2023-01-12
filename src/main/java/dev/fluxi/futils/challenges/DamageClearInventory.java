package dev.fluxi.futils.challenges;

import dev.fluxi.futils.gui.GuiAccessible;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageClearInventory extends GuiAccessible {
    public DamageClearInventory() {
        name("Damage Clear Inventory");
        itemMaterial(Material.ENDER_CHEST);
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
