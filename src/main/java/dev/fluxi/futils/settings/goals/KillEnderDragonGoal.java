package dev.fluxi.futils.settings.goals;

import dev.fluxi.futils.settings.goals.utils.KillMobGoal;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class KillEnderDragonGoal extends KillMobGoal {
    public KillEnderDragonGoal() {
        super(Material.DRAGON_HEAD, coloredComponent("Kill Ender Dragon"), "kill-ender-dragon-goal", EntityType.ENDER_DRAGON);
    }
}
