package dev.fluxi.futils.settings.goals;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.events.PlayerKillEntityEvent;
import dev.fluxi.futils.settings.utils.ToggleableSetting;
import org.bukkit.Material;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;

public class KillEnderDragonGoal extends ToggleableSetting {
    public KillEnderDragonGoal() {
        super(Material.DRAGON_HEAD, coloredComponent("Kill Ender Dragon"), "kill-ender-dragon-goal");
    }

    @EventHandler
    public void onPlayerKillEntity(PlayerKillEntityEvent event) {
        if (!(event.getMob() instanceof EnderDragon)) return;
        FUtils.getInstance().getTimer().running(false);
    }
}
