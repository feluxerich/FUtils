package dev.fluxi.futils.utils.challenge;

import dev.fluxi.futils.FUtils;
import org.bukkit.Material;
import org.bukkit.event.Listener;

public class Challenge implements Listener {
    public String challengeName = "";
    public Material challengeItemMaterial = Material.BARRIER;
    private boolean isActive = false;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
        FUtils.getInstance().getChallengeManager().setConfig();
    }

    public void enable() {
        FUtils.getInstance().registerEvent(this);
    }

    public void disable() {
        FUtils.getInstance().unregisterEvent(this);
    }
}
