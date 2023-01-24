package dev.fluxi.futils.settings.utils;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.inventory.items.Item;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.List;

public class Setting extends Item implements Listener {
    public Setting(Material material, Component name) {
        super(material, name);
        FUtils.getInstance().registerEvent(this);
    }

    public Setting(Material material, Component name, List<Component> description) {
        super(material, name, description);
        FUtils.getInstance().registerEvent(this);
    }

    @Override
    public void onClick(InventoryClickEvent event) {

    }
}
