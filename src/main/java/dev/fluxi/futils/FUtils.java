package dev.fluxi.futils;

import dev.fluxi.futils.commands.*;
import dev.fluxi.futils.listeners.BlockUpdateListener;
import dev.fluxi.futils.listeners.PlayerInteractEntityListener;
import dev.fluxi.futils.listeners.PlayerPortalListener;
import dev.fluxi.futils.listeners.WorldLoadListener;
import dev.fluxi.futils.utils.ExtendedPlugin;
import dev.fluxi.futils.utils.Timer;
import dev.fluxi.futils.challenges.ChallengeManager;
import dev.fluxi.futils.utils.VanishManager;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public final class FUtils extends ExtendedPlugin implements Listener {
    private static FUtils instance;

    private VanishManager vanishManager;
    private ChallengeManager challengeManager;
    private Timer timer;

    @Override
    public void onLoad() {
        super.onLoad();
        setInstance();

        if (!getConfig().getBoolean("reset")) return;
        resetWorld();
        getConfig().set("reset", false);
        saveConfig();
    }

    @Override
    public void onEnable() {
        vanishManager = new VanishManager();
        timer = new Timer();
        challengeManager = new ChallengeManager();

        registerCommands();
        registerEvents();
    }

    private void registerCommands() {
        registerCommand("vanish", new VanishCommand());
        registerCommand("heal", new HealCommand());
        registerCommand("challenge", new ChallengeCommand());
        registerCommand("timer", new TimerCommand());
        registerCommand("reset", new ResetCommand());
        registerCommand("world", new WorldCommand());
    }

    private void registerEvents() {
        registerEvent(new PlayerInteractEntityListener());
        registerEvent(new BlockUpdateListener());
        registerEvent(new WorldLoadListener());
        registerEvent(new PlayerPortalListener());
        registerEvent(this);
    }

    private void resetWorld() {
        File world = new File(Bukkit.getWorldContainer(), "world");
        File nether = new File(Bukkit.getWorldContainer(), "world_nether");
        File end = new File(Bukkit.getWorldContainer(), "world_the_end");

        try {
            FileUtils.deleteDirectory(world);
            FileUtils.deleteDirectory(nether);
            FileUtils.deleteDirectory(end);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getLogger().log(Level.INFO, world.mkdirs() ? "World folder created" : "No World folder created");
        getLogger().log(Level.INFO, new File(world, "playerdata").mkdirs() ? "Playerdata folder created" : "No Playerdata folder created");
    }

    public VanishManager getVanishManager() {
        return vanishManager;
    }

    public ChallengeManager getChallengeManager() {
        return challengeManager;
    }

    public Timer getTimer() {
        return timer;
    }

    public static FUtils getInstance() {
        return instance;
    }

    private void setInstance() {
        instance = this;
    }
}
