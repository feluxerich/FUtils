package dev.fluxi.futils.utils.challenge;

import dev.fluxi.futils.FUtils;
import org.bukkit.Material;
import org.bukkit.event.Listener;

public class Challenge implements Listener {
    private String name = "";
    private Material itemMaterial = Material.BARRIER;
    private boolean isActive = false;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void enable() {
        FUtils.getInstance().registerEvent(this);
    }

    public void disable() {
        FUtils.getInstance().unregisterEvent(this);
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
