package dev.fluxi.futils.settings.goals;

import dev.fluxi.futils.settings.goals.utils.KillMobGoal;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class KillBobGoal extends KillMobGoal {
    public KillBobGoal() {
        super(Material.PRISMARINE, coloredComponent("Kill Bob"), "kill-bob-goal", EntityType.ELDER_GUARDIAN);
    }
}
