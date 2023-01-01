package dev.fluxi.futils.utils;

import dev.fluxi.futils.FUtils;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class Challenge implements Listener {
    public ItemStack challengeItem = new ItemStack(Material.BARRIER);
    public String challengeName = "";

    public ItemStack getChallengeItem() {
        return challengeItem;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void startChallenge() {
        FUtils.getInstance().registerEvent(this);
        FUtils.getInstance().getChallengeManager().setActiveChallenge(this);
        FUtils.getInstance().getTimer().setHidden(false);
        FUtils.getInstance().getTimer().setRunning(true);
    }

    public Challenge resumeChallenge() {
        FUtils.getInstance().getTimer().setRunning(true);
        return this;
    }

    public Challenge pauseChallenge() {
        FUtils.getInstance().getTimer().setRunning(false);
        return this;
    }

    public Challenge stopChallenge() {
        FUtils.getInstance().unregisterEvent(this);
        FUtils.getInstance().getChallengeManager().setActiveChallenge(null);
        FUtils.getInstance().getTimer().setRunning(false);
        FUtils.getInstance().getTimer().setTime(0);
        FUtils.getInstance().getTimer().setHidden(true);
        return this;
    }
}
