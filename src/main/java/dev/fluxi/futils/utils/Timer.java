package dev.fluxi.futils.utils;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.components.LinearGradientComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {
    private int time = 0;
    private boolean isRunning = false;
    private boolean isHidden = true;

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
            time = section.getInt("time");
        }
        if (section.isSet("running")) {
            isRunning = section.getBoolean("running");
        }
        if (section.isSet("hidden")) {
            isHidden = section.getBoolean("hidden");
        }
    }

    private void setConfig() {
        ConfigurationSection section = getConfigSection();
        section.set("time", time);
        section.set("running", isRunning);
        section.set("hidden", isHidden);
        FUtils.getInstance().saveConfig();
    }

    public boolean running() {
        return isRunning;
    }

    public void running(boolean running) {
        isRunning = running;
        setConfig();
    }

    public int time() {
        return time;
    }

    public void time(int time) {
        this.time = time;
        setConfig();
    }

    public boolean hidden() {
        return isHidden;
    }

    public void hidden(boolean hidden) {
        isHidden = hidden;
        setConfig();
    }

    public void sendActionBar() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!running()) {
                player.sendActionBar(
                        new LinearGradientComponent(prettifyTime(),
                        TextColor.fromHexString("#ef4444"),
                        TextColor.fromHexString("#5b45ff")
                ).getGradientComponent().decorate(TextDecoration.BOLD));
                continue;
            }
            player.sendActionBar(
                    new LinearGradientComponent(prettifyTime(),
                            TextColor.fromHexString("#5b45ff"),
                            TextColor.fromHexString("#ef4444")
                    ).getGradientComponent().decorate(TextDecoration.BOLD));
        }
    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (hidden()) {
                    return;
                }
                sendActionBar();
                if (!running()) {
                    return;
                }
                time(time() + 1);
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
}
