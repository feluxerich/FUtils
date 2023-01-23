package dev.fluxi.futils.managers;

import dev.fluxi.futils.inventory.items.Item;
import dev.fluxi.futils.settings.*;
import dev.fluxi.futils.settings.utils.Setting;

import java.util.ArrayList;
import java.util.List;

public class SettingsManager {
    private final List<Setting> settings = new ArrayList<>();

    public SettingsManager() {
        settings.add(new AdvancementDamage());
        settings.add(new NoHitDelay());
        settings.add(new OldPvP());
        settings.add(new SharedDamage());
        settings.add(new MaxHealth());
    }

    public List<Item> getSettings() {
        return new ArrayList<>(settings);
    }
}
