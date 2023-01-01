package dev.fluxi.futils.challenges;

import dev.fluxi.futils.utils.Challenge;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.inventory.ItemStack;

public class AdvancementDamage extends Challenge {
    public AdvancementDamage() {
        challengeItem = new ItemStack(Material.KNOWLEDGE_BOOK);
        challengeName = "AdvancementDamage";
    }

    @EventHandler
    public void onEntityDamage(PlayerAdvancementDoneEvent event) {
        if (event.getAdvancement().getKey().toString().contains(":recipes/")) {
            return;
        }
        event.getPlayer().damage(2);
    }
}
