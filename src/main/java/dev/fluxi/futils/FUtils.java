package dev.fluxi.futils;

import dev.fluxi.futils.managers.ChallengeManager;
import dev.fluxi.futils.commands.*;
import dev.fluxi.futils.listeners.*;
import dev.fluxi.futils.managers.SettingsManager;
import dev.fluxi.futils.utils.ExtendedPlugin;
import dev.fluxi.futils.utils.Timer;
import dev.fluxi.futils.managers.VanishManager;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public final class FUtils extends ExtendedPlugin implements Listener {
    @Override
    public void onLoad() {
        setInstance();

        if (!getConfig().getBoolean("reset")) return;
        resetWorld();
        getConfig().set("reset", false);
        saveConfig();
    }

    @Override
    public void onEnable() {
        setVanishManager(new VanishManager());
        setChallengeManager(new ChallengeManager());
        setSettingsManager(new SettingsManager());
        setTimer(new Timer());

        registerCommands();
        registerEvents();
    }

    private void registerCommands() {
        registerCommand("vanish", new VanishCommand());
        registerCommand("heal", new HealCommand());
        registerCommand("timer", new TimerCommand());
        registerCommand("reset", new ResetCommand());
        registerCommand("gui", new GuiCommand());
    }

    private void registerEvents() {
        registerEvent(new PlayerInteractEntityListener());
        registerEvent(new BlockUpdateListener());
        registerEvent(new WorldLoadListener());
        registerEvent(new PlayerPortalListener());
        registerEvent(new PlayerJoinListener());
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
}
