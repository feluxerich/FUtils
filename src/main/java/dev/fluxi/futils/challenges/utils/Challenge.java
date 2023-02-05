package dev.fluxi.futils.challenges.utils;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.inventory.items.Item;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Challenge extends Item implements Listener {
    protected boolean active = false;
    protected final String configName;

    public Challenge(Material material, Component name, String configName) {
        super(material, name);
        this.configName = configName;
    }

    protected void toggle() {
        if (active) {
            disable();
        } else {
            enable();
        }
        active = !active;
    }

    public void enable() {
        FUtils.getInstance().registerEvent(this);
        description(Collections.singletonList(coloredComponent("Enabled", true)));
    }

    public void disable() {
        FUtils.getInstance().unregisterEvent(this);
        description(new ArrayList<>());
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        toggle();
    }
}
