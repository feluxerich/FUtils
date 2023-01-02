package dev.fluxi.futils;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import dev.fluxi.futils.commands.*;
import dev.fluxi.futils.listeners.JoinListener;
import dev.fluxi.futils.listeners.PlayerInteractEntityListener;
import dev.fluxi.futils.utils.Timer;
import dev.fluxi.futils.utils.challenge.ChallengeManager;
import dev.fluxi.futils.utils.VanishManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class FUtils extends JavaPlugin implements Listener {
    private static FUtils instance;
    private VanishManager vanishManager;
    private ChallengeManager challengeManager;
    private Timer timer;
    FileConfiguration config = getConfig();

    @Override
    public void onLoad() {
        if (!config.getBoolean("reset")) {
            return;
        }
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

        world.mkdirs();
        new File(world, "playerdata").mkdirs();

        config.set("reset", false);
        saveConfig();
    }

    @Override
    public void onEnable() {
        instance = this;
        vanishManager = new VanishManager(this);
        timer = new Timer(false, 0);

        config.addDefault("reset", false);
        config.options().copyDefaults(true);
        saveConfig();

        registerCommands();
        registerEvents();
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        challengeManager = new ChallengeManager();
    }

    @EventHandler
    public void onServerListPing(PaperServerListPingEvent event) {
        if (challengeManager.getRunning()) {
            event.motd(Component.text("Challenge running with " + event.getNumPlayers() + " players")
                    .color(TextColor.fromHexString("#5b45ff")));
            return;
        }
        event.motd(Component.text("No Challenge running")
                .color(TextColor.fromHexString("#7866ff")));
    }

    private void registerCommands() {
        registerCommand("vanish", new VanishCommand(), new VanishCommand());
        registerCommand("heal", new HealCommand(), new HealCommand());
        registerCommand("challenge", new ChallengeCommand(), new ChallengeCommand());
        registerCommand("timer", new TimerCommand(), new TimerCommand());
        registerCommand("reset", new ResetCommand(), new ResetCommand());
    }

    private void registerCommand(String name, CommandExecutor commandExecutor, TabCompleter tabCompleter) {
        Objects.requireNonNull(getCommand(name)).setExecutor(commandExecutor);
        Objects.requireNonNull(getCommand(name)).setTabCompleter(tabCompleter);
    }

    private void registerEvents() {
        registerEvent(new JoinListener());
        registerEvent(new PlayerInteractEntityListener());
        registerEvent(this);
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
