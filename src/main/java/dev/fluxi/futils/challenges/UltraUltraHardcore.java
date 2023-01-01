package dev.fluxi.futils.challenges;

import dev.fluxi.futils.utils.Challenge;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.inventory.ItemStack;

public class UltraUltraHardcore extends Challenge {
    public UltraUltraHardcore() {
        challengeItem = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
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
