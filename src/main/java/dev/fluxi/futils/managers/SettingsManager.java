package dev.fluxi.futils.managers;

import dev.fluxi.futils.inventory.items.Item;
import dev.fluxi.futils.inventory.items.Toggleable;
import dev.fluxi.futils.settings.AdvancementDamage;
import dev.fluxi.futils.settings.NoHitDelay;
import dev.fluxi.futils.settings.OldPvP;
import dev.fluxi.futils.settings.SharedDamage;

import java.util.ArrayList;
import java.util.List;

public class SettingsManager {
    private final List<Toggleable> settings = new ArrayList<>();

    public SettingsManager() {
        settings.add(new AdvancementDamage());
        settings.add(new NoHitDelay());
        settings.add(new OldPvP());
        settings.add(new SharedDamage());
    }

    public List<Item> getSettings() {
        return new ArrayList<>(settings);
    }
}
