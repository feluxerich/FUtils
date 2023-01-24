package dev.fluxi.futils.utils;

import dev.fluxi.futils.managers.ChallengeManager;
import dev.fluxi.futils.managers.InventoryManager;
import dev.fluxi.futils.managers.VanishManager;
import dev.fluxi.futils.managers.SettingsManager;
import dev.fluxi.futils.utils.misc.Timer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class ExtendedPlugin extends JavaPlugin {
    private static ExtendedPlugin instance;

    private VanishManager vanishManager;
    private ChallengeManager challengeManager;
    private SettingsManager settingsManager;
    private InventoryManager inventoryManager;
    private Timer timer;

    public void registerCommand(String name, CommandExecutor commandExecutor) {
        Objects.requireNonNull(getCommand(name)).setExecutor(commandExecutor);
        Objects.requireNonNull(getCommand(name)).setTabCompleter((TabCompleter) commandExecutor);
    }

    public void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public void unregisterEvent(Listener listener) {
        HandlerList.unregisterAll(listener);
    }

    public VanishManager getVanishManager() {
        return vanishManager;
    }

    public void setVanishManager(VanishManager vanishManager) {
        this.vanishManager = vanishManager;
    }

    public ChallengeManager getChallengeManager() {
        return challengeManager;
    }

    public void setChallengeManager(ChallengeManager challengeManager) {
        this.challengeManager = challengeManager;
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }

    public void setSettingsManager(SettingsManager settingsManager) {
        this.settingsManager = settingsManager;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public void setInventoryManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public static ExtendedPlugin getInstance() {
        return instance;
    }

    protected void setInstance() {
        instance = this;
    }
}
