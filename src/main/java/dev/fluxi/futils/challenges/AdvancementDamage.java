package dev.fluxi.futils.challenges;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class AdvancementDamage extends Challenge {
    public AdvancementDamage() {
        name("AdvancementDamage");
        itemMaterial(Material.KNOWLEDGE_BOOK);
    }

    @EventHandler
    public void onEntityDamage(PlayerAdvancementDoneEvent event) {
        if (event.getAdvancement().getKey().toString().contains(":recipes/")) {
            return;
        }
        event.getPlayer().damage(2);
    }
}
