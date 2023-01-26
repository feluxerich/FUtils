package dev.fluxi.futils.utils;

import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Random;

public class RandomUtils {
    public static int random(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }
    
    public static PotionEffectType randomEffectType() {
        int randomEffectIndex = RandomUtils.random(PotionEffectType.values().length);
        return Arrays.asList(PotionEffectType.values()).get(randomEffectIndex);
    }
}
