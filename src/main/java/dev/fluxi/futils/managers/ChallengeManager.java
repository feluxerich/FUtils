package dev.fluxi.futils.managers;

import dev.fluxi.futils.challenges.BlockBreakVoid;
import dev.fluxi.futils.challenges.DamageClearInventory;
import dev.fluxi.futils.challenges.FallingBlocks;
import dev.fluxi.futils.challenges.IcePlatform;
import dev.fluxi.futils.inventory.items.Item;
import dev.fluxi.futils.inventory.items.Toggleable;

import java.util.ArrayList;
import java.util.List;

public class ChallengeManager {
    private final List<Toggleable> challenges = new ArrayList<>();

    public ChallengeManager() {
        challenges.add(new BlockBreakVoid());
        challenges.add(new DamageClearInventory());
        challenges.add(new FallingBlocks());
        challenges.add(new IcePlatform());
    }

    public List<Item> getChallenges() {
        return new ArrayList<>(challenges);
    }
}
