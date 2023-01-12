package dev.fluxi.futils.managers;

import dev.fluxi.futils.challenges.*;

public class ChallengeManager extends Manager {
    public ChallengeManager() {
        super("challenges", "Challenges");
    }

    @Override
    protected void registerItems() {
        items().add(new LevelBorder());
        items().add(new FallingBlocks());
        items().add(new BlockBreakVoid());
        items().add(new DamageClearInventory());
        items().add(new IcePlatform());
    }
}
