package dev.fluxi.futils.utils;

import dev.fluxi.futils.FUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class ChallengeUtils {
    public static boolean shouldExecute(Player player) {
        return FUtils.getInstance().getTimer().running() && player.getGameMode() == GameMode.SURVIVAL;
    }
}
