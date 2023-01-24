package dev.fluxi.futils.managers;

import dev.fluxi.futils.inventory.items.Item;
import dev.fluxi.futils.settings.*;
import dev.fluxi.futils.settings.utils.Setting;

import java.util.ArrayList;
import java.util.List;

public class SettingsManager {
    private final List<Setting> settings = new ArrayList<>();

    public SettingsManager() {
        settings.add(new AdvancementDamageSetting());
        settings.add(new NoHitDelaySetting());
        settings.add(new OldPvPSetting());
        settings.add(new SharedDamageSetting());
        settings.add(new MaxHealthSetting());
    }

    public List<Item> getSettings() {
        return new ArrayList<>(settings);
    }
}
