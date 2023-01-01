package dev.fluxi.futils.utils;

import dev.fluxi.futils.challenges.UltraHardcore;
import dev.fluxi.futils.challenges.UltraUltraHardcore;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChallengeManager {

    List<Challenge> challenges = new ArrayList<>();
    Challenge activeChallenge;

    public ChallengeManager() {
        registerChallenges();
    }

    private void registerChallenges() {
        challenges.add(new UltraHardcore());
        challenges.add(new UltraUltraHardcore());
    }

    public List<String> getChallengeNames() {
        List<String> challengeNames = new ArrayList<>();

        for (Challenge challenge : challenges) {
            challengeNames.add(challenge.getChallengeName());
        }

        return challengeNames;
    }

    public Challenge getChallenge(String name, Player player) {
        for (Challenge challenge : challenges) {
            player.sendMessage(challenge.challengeName);
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
