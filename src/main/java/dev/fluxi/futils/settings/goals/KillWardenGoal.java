package dev.fluxi.futils.settings.goals;

import dev.fluxi.futils.settings.goals.utils.KillMobGoal;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class KillWardenGoal extends KillMobGoal {
    public KillWardenGoal() {
        super(Material.SCULK_SENSOR, coloredComponent("Kill Warden"), "kill-warden-goal", EntityType.WARDEN);
    }
}
