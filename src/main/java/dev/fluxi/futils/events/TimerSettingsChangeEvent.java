package dev.fluxi.futils.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class TimerSettingsChangeEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final boolean running;
    private final boolean ascending;
    private final int time;

    public TimerSettingsChangeEvent(boolean running, boolean ascending, int time) {
        this.running = running;
        this.ascending = ascending;
        this.time = time;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isAscending() {
        return ascending;
    }

    public int getTime() {
        return time;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
