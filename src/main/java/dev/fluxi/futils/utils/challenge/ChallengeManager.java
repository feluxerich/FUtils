package dev.fluxi.futils.utils.challenge;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.challenges.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

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
        challenges.add(new LevelBorder());
    }

    public List<Challenge> getActiveChallenges() {
        List<Challenge> activeChallenges = new ArrayList<>();
        for (Challenge challenge : challenges) {
            if (challenge == null || !challenge.isActive()) {
                continue;
            }
            activeChallenges.add(challenge);
        }
        return activeChallenges;
    }

    public void start() {
        List<Challenge> activeChallenges = getActiveChallenges();
        if (activeChallenges.isEmpty() || isRunning()) {
            return;
        }
        for (Challenge activeChallenge : activeChallenges) {
            activeChallenge.enable();
        }
        FUtils.getInstance().getTimer().setTime(0);
        FUtils.getInstance().getTimer().setRunning(true);
        FUtils.getInstance().getTimer().setHidden(false);
        isRunning = true;
    }

    public void stop () {
        List<Challenge> activeChallenges = getActiveChallenges();
        if (activeChallenges.isEmpty() || !isRunning()) {
            return;
        }
        for (Challenge activeChallenge : activeChallenges) {
            activeChallenge.disable();
            activeChallenge.setActive(false);
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

    public void gameMode(GameMode gameMode) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setGameMode(gameMode);
        }
    }

    public Boolean isRunning() {
        return isRunning;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }
}
