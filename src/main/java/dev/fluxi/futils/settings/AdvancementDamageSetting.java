package dev.fluxi.futils.settings;

import dev.fluxi.futils.settings.utils.ToggleableSetting;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class AdvancementDamageSetting extends ToggleableSetting {
    public AdvancementDamageSetting() {
        super(Material.KNOWLEDGE_BOOK, coloredComponent("Advancement Damage"));
    }

    @EventHandler
    public void onEntityDamage(PlayerAdvancementDoneEvent event) {
        if (event.getAdvancement().getKey().toString().contains(":recipes/")) {
            return;
        }
        event.getPlayer().damage(1);
    }
}
