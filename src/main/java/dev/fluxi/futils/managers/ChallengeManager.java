package dev.fluxi.futils.managers;

import dev.fluxi.futils.challenges.*;
import dev.fluxi.futils.challenges.utils.Challenge;
import dev.fluxi.futils.inventory.items.Item;

import java.util.ArrayList;
import java.util.List;

public class ChallengeManager {
    private final List<Challenge> challenges = new ArrayList<>();

    public ChallengeManager() {
        challenges.add(new BlockBreakVoid());
        challenges.add(new DamageClearInventory());
        challenges.add(new FallingBlocks());
        challenges.add(new IcePlatform());
        challenges.add(new FloorIsLava());
        challenges.add(new BedrockWall());
        challenges.add(new OnlyDown());
        challenges.add(new NoExp());
        challenges.add(new FoodAirLaunch());
        challenges.add(new PickupItemAirLaunch());
        challenges.add(new MirrorChunk());
        challenges.add(new ChunkEffect());
        challenges.add(new BiomeEffect());
    }

    public List<Item> getChallenges() {
        return new ArrayList<>(challenges);
    }
}
