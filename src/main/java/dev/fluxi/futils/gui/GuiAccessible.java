package dev.fluxi.futils.gui;

import org.bukkit.Material;
import org.bukkit.event.Listener;

public class GuiAccessible implements Listener {
    private String name = "";
    private Material itemMaterial = Material.BARRIER;
    private boolean isActive = false;

    public boolean active() {
        return isActive;
    }

    public void active(boolean active) {
        isActive = active;
    }

    public void enable() {
        active(true);
    }

    public void disable() {
        active(false);
    }

    public String name() {
        return name;
    }

    public void name(String name) {
        this.name = name;
    }

    public Material itemMaterial() {
        return itemMaterial;
    }

    public void itemMaterial(Material itemMaterial) {
        this.itemMaterial = itemMaterial;
    }
}
