package dev.fluxi.futils.settings.goals;

import dev.fluxi.futils.settings.goals.utils.KillMobGoal;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class KillWitherGoal extends KillMobGoal {
    public KillWitherGoal() {
        super(Material.WITHER_SKELETON_SKULL, coloredComponent("Kill Wither"), "kill-wither-goal", EntityType.WITHER);
    }
}