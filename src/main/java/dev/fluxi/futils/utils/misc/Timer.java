package dev.fluxi.futils.utils.misc;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.events.TimerSettingsChangeEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {
    private int time = 0;
    private boolean running = false;
    private boolean ascending = true;

    public Timer() {
        run();
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
        triggerEvent();
    }

    public boolean running() {
        return running;
    }

    public void running(boolean running) {
        this.running = running;
        overridePlayerInventories();
        triggerEvent();
    }

    public int time() {
        return time;
    }

    public void time(int time) {
        if (time < 0) {
            running(false);
            return;
        }
        this.time = time;
    }

    public void time(int time, boolean triggerEvent) {
        time(time);
        if (triggerEvent) triggerEvent();
    }

    private void sendActionBar() {
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
                if (Bukkit.getOnlinePlayers().size() == 0) running(false);
                sendActionBar();
                if (!running()) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.playEffect(player.getLocation(), Effect.ENDER_SIGNAL, null);
                    }
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

    private void overridePlayerInventories() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (running) {
                FUtils.getInstance().getInventoryManager().setNormalInventory(player);
                continue;
            }
            FUtils.getInstance().getInventoryManager().setManagementInventory(player);
        }
    }

    private void triggerEvent() {
        TimerSettingsChangeEvent timerSettingsChangeEvent = new TimerSettingsChangeEvent(running(), ascending(), time());
        Bukkit.getPluginManager().callEvent(timerSettingsChangeEvent);
    }
}
