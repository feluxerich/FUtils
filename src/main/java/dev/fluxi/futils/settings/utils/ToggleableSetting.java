package dev.fluxi.futils.settings.utils;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.utils.ConfigUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Collections;


public class ToggleableSetting extends Setting {
    private boolean active = false;
    protected final String config;

    public ToggleableSetting(Material material, Component name, String config) {
        super(material, name);
        this.config = config;
        readConfig();
        updateDescription();
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
        FUtils.getInstance().registerEvent(this);
        active(true);
        writeConfig();
        updateDescription();
    }

    public void disable() {
        FUtils.getInstance().unregisterEvent(this);
        active(false);
        writeConfig();
        updateDescription();
    }

    public boolean active() {
        return active;
    }

    public void active(boolean active) {
        this.active = active;
    }

    public void writeConfig() {
        ConfigurationSection section = ConfigUtils.getConfigSection("settings");
        section.set(config, active);
        FUtils.getInstance().saveConfig();
    }

    public void readConfig() {
        ConfigurationSection section = ConfigUtils.getConfigSection("settings");
        if (!section.isSet(config)) {
            return;
        }
        active = section.getBoolean(config);
    }

    private void updateDescription() {
        description(Collections.singletonList(coloredComponent(active() ? "Enabled" : "Disabled")));
    }
}
