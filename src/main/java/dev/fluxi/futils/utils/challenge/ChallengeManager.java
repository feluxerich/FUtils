package dev.fluxi.futils.utils.challenge;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.challenges.*;
import dev.fluxi.futils.utils.Timer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChallengeManager {
    List<Challenge> challenges = new ArrayList<>();
    FileConfiguration configuration = FUtils.getInstance().getConfig();
    Timer timer = FUtils.getInstance().getTimer();
    boolean isRunning = false;

    public ChallengeManager() {
        registerChallenges();
        getConfig();
    }

    private Challenge getChallenge(String name) {
        for (Challenge challenge : challenges) {
            if (!challenge.challengeName.equals(name)) {
                continue;
            }
            return challenge;
        }
        return null;
    }

    private void registerChallenges() {
        challenges.add(new UltraHardcore());
        challenges.add(new UltraUltraHardcore());
        challenges.add(new AdvancementDamage());
        challenges.add(new SharedDamage());
        challenges.add(new LevelBorder());
        challenges.add(new OldPvP());
    }

    private ConfigurationSection getConfigSection() {
        if (!configuration.isConfigurationSection("challenge")) {
            return configuration.createSection("challenge");
        }
        return configuration.getConfigurationSection("challenge");
    }

    private void getConfig() {
        ConfigurationSection section = getConfigSection();
        List<?> active = section.getList("active");

        if (active == null) {
            return;
        }
        for (Object activeChallenge : active) {
            Challenge challenge = getChallenge(((Challenge) activeChallenge).challengeName);
            if (challenge == null) {
                continue;
            }
            challenge.setActive(true);
        }
        if (!section.isSet("running")) {
            isRunning = false;
        }
        isRunning = section.getBoolean("running");
    }

    public void setConfig() {
        ConfigurationSection section = getConfigSection();
        List<String> activeChallengeNames = new ArrayList<>();
        for (Challenge challenge : getActiveChallenges()) {
            if (activeChallengeNames.contains(challenge.challengeName)) {
                continue;
            }
            activeChallengeNames.add(challenge.challengeName);
        }
        section.set("active", activeChallengeNames);
        FUtils.getInstance().saveConfig();
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
        timer.setTime(0);
        timer.setRunning(true);
        timer.setHidden(false);
    }

    public void stop() {
        List<Challenge> activeChallenges = getActiveChallenges();
        if (activeChallenges.isEmpty() || !isRunning()) {
            return;
        }
        for (Challenge activeChallenge : activeChallenges) {
            activeChallenge.disable();
            activeChallenge.setActive(false);
        }
        timer.setRunning(false);
        timer.setHidden(true);
        timer.setTime(0);
    }

    public void pause() {
        if (!isRunning()) {
            return;
        }
        timer.setRunning(false);
    }

    public void resume() {
        if (isRunning()) {
            return;
        }
        timer.setRunning(true);
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
