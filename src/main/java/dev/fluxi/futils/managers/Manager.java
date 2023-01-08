package dev.fluxi.futils.managers;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.gui.GuiAccessible;
import dev.fluxi.futils.gui.ScrollableGui;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Manager {
    private final List<GuiAccessible> items = new ArrayList<>();
    private final String configSection;
    private final String guiName;

    public Manager(String configSection, String guiName) {
        this.configSection = configSection;
        this.guiName = guiName;
        registerItems();
        getConfig();
    }

    public Inventory getInventory() {
        return new ScrollableGui(Component.text(guiName()), this).getInventory();
    }

    protected void registerItems() {
    }

    protected ConfigurationSection getConfigSection() {
        if (!FUtils.getInstance().getConfig().isConfigurationSection(configSection())) {
            return FUtils.getInstance().getConfig().createSection(configSection());
        }
        return FUtils.getInstance().getConfig().getConfigurationSection(configSection());
    }

    protected void getConfig() {
        ConfigurationSection section = getConfigSection();
        List<?> active = section.getList("active");

        if (active == null) {
            return;
        }
        for (Object activeItem : active) {
            GuiAccessible item = getItem((String) activeItem);
            if (item == null) {
                continue;
            }
            enable(item);
            FUtils.getInstance().getLogger().log(Level.INFO, "Activated Item in " + configSection() + ": " + item.name());
        }
    }

    public void setConfig() {
        ConfigurationSection section = getConfigSection();
        List<String> activeItemNames = new ArrayList<>();
        for (GuiAccessible item : items()) {
            if (item == null || !item.active()) {
                continue;
            }
            if (activeItemNames.contains(item.name())) {
                continue;
            }
            activeItemNames.add(item.name());
        }
        section.set("active", activeItemNames);
        FUtils.getInstance().saveConfig();
    }

    public void enable(GuiAccessible item) {
        item.enable();
        FUtils.getInstance().registerEvent(item);
        setConfig();
    }

    public void disable(GuiAccessible item) {
        item.disable();
        FUtils.getInstance().unregisterEvent(item);
        setConfig();
    }

    protected GuiAccessible getItem(String name) {
        for (GuiAccessible item : items()) {
            if (!item.name().equals(name)) {
                continue;
            }
            return item;
        }
        return null;
    }

    public List<GuiAccessible> items() {
        return items;
    }

    protected String configSection() {
        return configSection;
    }

    protected String guiName() {
        return guiName;
    }
}
