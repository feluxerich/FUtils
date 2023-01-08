package dev.fluxi.futils.settings;

import dev.fluxi.futils.gui.GuiAccessible;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class AdvancementDamage extends GuiAccessible {
    public AdvancementDamage() {
        name("Advancement Damage");
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
