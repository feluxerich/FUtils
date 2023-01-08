package dev.fluxi.futils.managers;

import dev.fluxi.futils.challenges.LevelBorder;

public class ChallengeManager extends Manager {
    public ChallengeManager() {
        super("challenges", "Challenges");
    }

    @Override
    protected void registerItems() {
        items().add(new LevelBorder());
    }
}
