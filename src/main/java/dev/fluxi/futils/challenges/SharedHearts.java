package dev.fluxi.futils.challenges;

import dev.fluxi.futils.utils.Challenge;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class SharedHearts extends Challenge {
    public SharedHearts() {
        challengeItem = new ItemStack(Material.SCULK_SENSOR);
        challengeName = "SharedHearts";
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
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
