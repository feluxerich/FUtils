package dev.fluxi.futils.managers;

import dev.fluxi.futils.settings.*;

public class SettingsManager extends Manager {
    public SettingsManager() {
        super("settings", "Settings");
    }

    @Override
    protected void registerItems() {
        items().add(new AdvancementDamage());
        items().add(new OldPvP());
        items().add(new SharedDamage());
        items().add(new UltraHardcore());
        items().add(new UltraUltraHardcore());
        items().add(new Hardcore());
    }
}
