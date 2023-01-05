package dev.fluxi.futils.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class ExtendedPlugin extends JavaPlugin {
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
}
