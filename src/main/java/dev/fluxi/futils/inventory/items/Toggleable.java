package dev.fluxi.futils.inventory.items;

import dev.fluxi.futils.FUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Collections;

public class Toggleable extends Item implements Listener {
    private boolean active = false;

    public Toggleable(Material material, Component name) {
        super(material, name);
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
        description(Collections.singletonList(Component.text("Enabled")));
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
