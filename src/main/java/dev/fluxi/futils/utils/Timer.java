package dev.fluxi.futils.utils;

import dev.fluxi.futils.FUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {
    private int time = 0;
    private boolean running = false;
    private boolean ascending = true;

    public Timer() {
        getConfig();
        run();
    }

    private ConfigurationSection getConfigSection() {
        if (!FUtils.getInstance().getConfig().isConfigurationSection("timer")) {
            return FUtils.getInstance().getConfig().createSection("timer");
        }
        return FUtils.getInstance().getConfig().getConfigurationSection("timer");
    }

    private void getConfig() {
        ConfigurationSection section = getConfigSection();
        if (section.isSet("time")) {
            time(section.getInt("time"));
        }
        if (section.isSet("running")) {
            running(section.getBoolean("running"));
        }
        if (section.isSet("ascending")) {
            ascending(section.getBoolean("ascending"));
        }
    }

    private void setConfig() {
        ConfigurationSection section = getConfigSection();
        section.set("time", time());
        section.set("running", running());
        section.set("ascending", ascending());
        FUtils.getInstance().saveConfig();
    }

    public void toggle() {
        running(!running());
    }

    public void invert() {
        ascending(!ascending());
    }

    public boolean ascending() {
        return ascending;
    }

    public void ascending(boolean ascending) {
        this.ascending = ascending;
        setConfig();
    }

    public boolean running() {
        return running;
    }

    public void running(boolean running) {
        overridePlayerInventories(running);
        this.running = running;
        setConfig();
    }

    public int time() {
        return time;
    }

    public void time(int time) {
        if (time < 0) {
            running(false);
            setConfig();
            return;
        }
        this.time = time;
        setConfig();
    }

    public void sendActionBar() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!running()) {
                player.sendActionBar(
                        Component.text("• " + prettifyTime() + " •", Style.style(
                                TextColor.fromHexString("#7866ff"),
                                TextDecoration.ITALIC.withState(false),
                                TextDecoration.BOLD
                        ))
                );
                continue;
            }
            player.sendActionBar(
                    Component.text(prettifyTime(), Style.style(
                            TextColor.fromHexString("#5b45ff"),
                            TextDecoration.ITALIC.withState(false),
                            TextDecoration.BOLD
                    ))
            );
        }
    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                sendActionBar();
                if (!running()) {
                    return;
                }
                if (ascending) {
                    time(time() + 1);
                } else {
                    time(time() - 1);
                }
            }
        }.runTaskTimer(FUtils.getInstance(), 20, 20);
    }

    public String prettifyTime() {
        int time = time();

        int seconds = time % 60;
        int minutes = (time % 3600) / 60;
        int hours = (time % 86400) / 3600;
        int days = time / 86400;

        return String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds);
    }

    private void overridePlayerInventories(boolean remove) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (remove) {
                FUtils.getInstance().getInventoryManager().removePlayer(player);
                continue;
            }
            if (FUtils.getInstance().getInventoryManager().containsPlayer(player)) {
                continue;
            }
            FUtils.getInstance().getInventoryManager().overridePlayerInventory(player);
        }
    }
}
