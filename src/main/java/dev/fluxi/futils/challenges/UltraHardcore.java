package dev.fluxi.futils.challenges;

import dev.fluxi.futils.utils.Challenge;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.inventory.ItemStack;

public class UltraHardcore extends Challenge {
    public UltraHardcore() {
        challengeItem = new ItemStack(Material.GOLDEN_APPLE);
        challengeName = "UltraHardcore";
    }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        if (event.getRegainReason() == EntityRegainHealthEvent.RegainReason.MAGIC_REGEN || event.getRegainReason() == EntityRegainHealthEvent.RegainReason.MAGIC) {
            return;
        }
        event.setCancelled(true);
    }
}
