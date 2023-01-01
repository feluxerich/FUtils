package dev.fluxi.futils.challenges;

import dev.fluxi.futils.utils.Challenge;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class UltraHardcore extends Challenge {
    public UltraHardcore() {
        challengeItem = new ItemStack(Material.GOLDEN_APPLE);
        challengeName = "Ultra-Hardcore";
    }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();
        if (player.getPotionEffect(PotionEffectType.REGENERATION) != null) {
            return;
        }
        event.setCancelled(true);
    }
}
