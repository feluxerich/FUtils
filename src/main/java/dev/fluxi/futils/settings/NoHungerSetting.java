package dev.fluxi.futils.settings;

import dev.fluxi.futils.settings.utils.ToggleableSetting;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class NoHungerSetting extends ToggleableSetting {
    public NoHungerSetting() {
        super(Material.COOKED_BEEF, coloredComponent("No Hunger"), "no-hunger");
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
