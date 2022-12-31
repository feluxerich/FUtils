package dev.fluxi.futils;

import dev.fluxi.futils.commands.HealCommand;
import dev.fluxi.futils.commands.VanishCommand;
import dev.fluxi.futils.listeners.JoinListener;
import dev.fluxi.futils.listeners.PlayerInteractEntityListener;
import dev.fluxi.futils.utils.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public final class FUtils extends JavaPlugin {
    private static FUtils instance;
    private VanishManager vanishManager;

    @Override
    public void onEnable() {
        instance = this;
        this.vanishManager = new VanishManager(this);

        registerCommands();
        registerEvents();
    }

    @Override
    public void onDisable() {
    }

    private void registerCommands() {
        registerCommand("vanish", new VanishCommand(), new VanishCommand());
        registerCommand("heal", new HealCommand(), new HealCommand());
    }

    private void registerCommand(String name, CommandExecutor commandExecutor, TabCompleter tabCompleter) {
        getCommand(name).setExecutor(commandExecutor);
        getCommand(name).setTabCompleter(tabCompleter);
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractEntityListener(), this);
    }

    public static FUtils getInstance() {
        return instance;
    }

    public VanishManager getVanishManager() {
        return vanishManager;
    }
}
