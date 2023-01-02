package dev.fluxi.futils.utils.challenge;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.challenges.AdvancementDamage;
import dev.fluxi.futils.challenges.SharedDamage;
import dev.fluxi.futils.challenges.UltraHardcore;
import dev.fluxi.futils.challenges.UltraUltraHardcore;

import java.util.ArrayList;
import java.util.List;

public class ChallengeManager {
    List<Challenge> challenges = new ArrayList<>();
    Boolean isRunning = false;

    public ChallengeManager() {
        registerChallenges();
    }

    private void registerChallenges() {
        challenges.add(new UltraHardcore());
        challenges.add(new UltraUltraHardcore());
        challenges.add(new AdvancementDamage());
        challenges.add(new SharedDamage());
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

    public List<Challenge> getActiveChallenges() {
        List<Challenge> activeChallenges = new ArrayList<>();
        for (Challenge challenge : challenges) {
            if (!challenge.isActive()) {
                continue;
            }
            activeChallenges.add(challenge);
        }
        return activeChallenges;
    }

    public void start() {
        List<Challenge> activeChallenges = getActiveChallenges();
        if (activeChallenges.isEmpty()) {
            return;
        }
        for (Challenge activeChallenge : activeChallenges) {
            activeChallenge.enable();
        }
        FUtils.getInstance().getTimer().setRunning(true);
        FUtils.getInstance().getTimer().setHidden(false);
        isRunning = true;
    }

    public void stop () {
        List<Challenge> activeChallenges = getActiveChallenges();
        if (activeChallenges.isEmpty() || !FUtils.getInstance().getTimer().isRunning()) {
            return;
        }
        for (Challenge activeChallenge : activeChallenges) {
            activeChallenge.disable();
        }
        FUtils.getInstance().getTimer().setRunning(false);
        FUtils.getInstance().getTimer().setHidden(true);
        FUtils.getInstance().getTimer().setTime(0);
        isRunning = false;
    }

    public void pause() {
        if (!FUtils.getInstance().getTimer().isRunning()) {
            return;
        }
        FUtils.getInstance().getTimer().setRunning(false);
        isRunning = false;
    }

    public void resume() {
        if (FUtils.getInstance().getTimer().isRunning()) {
            return;
        }
        FUtils.getInstance().getTimer().setRunning(true);
        isRunning = true;
    }

    public Boolean getRunning() {
        return isRunning;
    }
}
