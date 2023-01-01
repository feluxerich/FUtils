package dev.fluxi.futils.utils;

import dev.fluxi.futils.FUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {
    private boolean isRunning;
    private int time;
    private boolean isHidden = true;

    public Timer(boolean isRunning, int time) {
        this.isRunning = isRunning;
        this.time = time;

        run();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public Timer setRunning(boolean running) {
        isRunning = running;
        return this;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public void sendActionBar() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!isRunning()) {
                player.sendActionBar(Component.text()
                        .append(Component.text(prettifyTime()))
                        .color(TextColor.fromHexString("#7866ff"))
                        .decorate(TextDecoration.BOLD).build());
                continue;
            }
            player.sendActionBar(Component.text()
                    .append(Component.text(prettifyTime()))
                    .color(TextColor.fromHexString("#5b45ff"))
                    .decorate(TextDecoration.BOLD).build());
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
