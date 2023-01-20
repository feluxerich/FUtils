package dev.fluxi.futils.challenges;

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
