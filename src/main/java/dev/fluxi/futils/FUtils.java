package dev.fluxi.futils;

import dev.fluxi.futils.commands.ChallengeCommand;
import dev.fluxi.futils.commands.HealCommand;
import dev.fluxi.futils.commands.TimerCommand;
import dev.fluxi.futils.commands.VanishCommand;
import dev.fluxi.futils.listeners.JoinListener;
import dev.fluxi.futils.listeners.PlayerInteractEntityListener;
import dev.fluxi.futils.utils.Timer;
import dev.fluxi.futils.utils.ChallengeManager;
import dev.fluxi.futils.utils.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class FUtils extends JavaPlugin {
    private static FUtils instance;
    private VanishManager vanishManager;
    private ChallengeManager challengeManager;
    private Timer timer;

    @Override
    public void onEnable() {
        instance = this;
        vanishManager = new VanishManager(this);
        challengeManager = new ChallengeManager();
        timer = new Timer(false, 0);

        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
    }

    private void registerCommands() {
        registerCommand("vanish", new VanishCommand(), new VanishCommand());
        registerCommand("heal", new HealCommand(), new HealCommand());
        registerCommand("challenge", new ChallengeCommand(), new ChallengeCommand());
        registerCommand("timer", new TimerCommand(), new TimerCommand());
    }

    private void registerCommand(String name, CommandExecutor commandExecutor, TabCompleter tabCompleter) {
        Objects.requireNonNull(getCommand(name)).setExecutor(commandExecutor);
        Objects.requireNonNull(getCommand(name)).setTabCompleter(tabCompleter);
    }

    private void registerEvents() {
        registerEvent(new JoinListener());
        registerEvent(new PlayerInteractEntityListener());
    }

    public void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, instance);
    }

    public void unregisterEvent(Listener listener) {
        HandlerList.unregisterAll(listener);
    }

    public static FUtils getInstance() {
        return instance;
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
}
