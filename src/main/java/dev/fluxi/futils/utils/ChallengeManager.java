package dev.fluxi.futils.utils;

import dev.fluxi.futils.challenges.AdvancementDamage;
import dev.fluxi.futils.challenges.SharedHearts;
import dev.fluxi.futils.challenges.UltraHardcore;
import dev.fluxi.futils.challenges.UltraUltraHardcore;

import java.util.ArrayList;
import java.util.List;

public class ChallengeManager {

    List<Challenge> challenges = new ArrayList<>();
    Challenge activeChallenge;

    public ChallengeManager() {
        registerChallenges();
    }

    private void registerChallenges() {
        challenges.add(new UltraHardcore());
        challenges.add(new UltraUltraHardcore());
        challenges.add(new SharedHearts());
        challenges.add(new AdvancementDamage());
    }

    public List<String> getChallengeNames() {
        List<String> challengeNames = new ArrayList<>();

        for (Challenge challenge : challenges) {
            challengeNames.add(challenge.getChallengeName());
        }

        return challengeNames;
    }

    public Challenge getChallenge(String name) {
        for (Challenge challenge : challenges) {
            if (!name.equalsIgnoreCase(challenge.challengeName)) {
                continue;
            }
            return challenge;
        }
        return null;
    }

    public Challenge getActiveChallenge() {
        return activeChallenge;
    }

    public void setActiveChallenge(Challenge activeChallenge) {
        this.activeChallenge = activeChallenge;
    }
}
