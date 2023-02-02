package dev.fluxi.futils.challenges.utils;

import dev.fluxi.futils.FUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class TimedChallenge extends Challenge {
    public TimedChallenge(Material material, Component name, String configName, int delay, int period) {
        super(material, name, configName);
        run(delay, period);
    }

    public abstract void run();

    private void run(int delay, int period) {
        new BukkitRunnable() {
            @Override
            public void run() {
                TimedChallenge.this.run();
            }
        }.runTaskTimer(FUtils.getInstance(), delay, period);
    }
}
