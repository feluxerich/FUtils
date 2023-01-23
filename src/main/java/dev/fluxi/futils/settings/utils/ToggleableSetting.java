package dev.fluxi.futils.settings.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ToggleableSetting extends Setting {
    private boolean active = false;

    public ToggleableSetting(Material material, Component name) {
        super(material, name);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        toggle();
    }

    public void toggle() {
        if (active()) {
            disable();
            return;
        }
        enable();
    }

    public void enable() {
        active(true);
    }

    public void disable() {
        active(false);
    }

    public boolean active() {
        return active;
    }

    public void active(boolean active) {
        this.active = active;
    }
}
