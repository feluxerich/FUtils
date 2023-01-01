package dev.fluxi.futils.utils.challenge;

import dev.fluxi.futils.FUtils;
import org.bukkit.event.Listener;

public class Challenge implements Listener {
    public String challengeName = "";
    private boolean isActive = false;

    public String getChallengeName() {
        return challengeName;
    }

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
}
