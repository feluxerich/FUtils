package dev.fluxi.futils.utils;

import dev.fluxi.futils.FUtils;
import net.kyori.adventure.text.Component;
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

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
        setConfig();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        setConfig();
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
        setConfig();
    }

    public void sendActionBar() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!isRunning()) {
                player.sendActionBar(Component.text()
                        .append(Component.text(prettifyTime()))
                        .color(TextColor.fromHexString("#7866ff"))
                        .decorate(TextDecoration.BOLD));
                continue;
            }
            player.sendActionBar(Component.text()
                    .append(Component.text(prettifyTime()))
                    .color(TextColor.fromHexString("#5b45ff"))
                    .decorate(TextDecoration.BOLD));
        }
    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (isHidden()) {
                    return;
                }
                sendActionBar();
                if (!isRunning()) {
                    return;
                }
                setTime(getTime() + 1);
            }
        }.runTaskTimer(FUtils.getInstance(), 20, 20);
    }

    private String prettifyTime() {
        int time = getTime();

        int seconds = time % 60;
        int minutes = (time % 3600) / 60;
        int hours = (time % 86400) / 3600;
        int days = time / 86400;

        return String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds);
    }
}
