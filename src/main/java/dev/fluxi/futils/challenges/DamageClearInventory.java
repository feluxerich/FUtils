package dev.fluxi.futils.challenges;

import dev.fluxi.futils.challenges.utils.Challenge;
import dev.fluxi.futils.utils.ChallengeUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageClearInventory extends Challenge {
    public DamageClearInventory() {
        super(Material.ENDER_CHEST, coloredComponent("Damage Clear Inventory"), "damage-clear-inventory");
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!ChallengeUtils.shouldExecute((Player) event.getEntity())) {
            return;
        }
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        player.getInventory().clear();
    }
}
