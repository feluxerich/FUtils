package dev.fluxi.futils.utils;

import dev.fluxi.futils.FUtils;
import org.bukkit.configuration.ConfigurationSection;

public class ConfigUtils {
    public static ConfigurationSection getConfigSection(String path) {
        if (!FUtils.getInstance().getConfig().isConfigurationSection(path)) {
            return FUtils.getInstance().getConfig().createSection(path);
        }
        return FUtils.getInstance().getConfig().getConfigurationSection(path);
    }

    public static void resetConfigurationPath(String path) {
        FUtils.getInstance().getConfig().set(path, null);
    }
}
