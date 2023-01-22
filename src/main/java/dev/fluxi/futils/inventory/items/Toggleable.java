package dev.fluxi.futils.inventory.items;

import dev.fluxi.futils.FUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Collections;

public class Toggleable extends Item implements Listener {
    private boolean active = false;

    public Toggleable(Material material, Component name) {
        super(material, name);
    }

    protected void toggle() {
        if (active) {
            FUtils.getInstance().unregisterEvent(this);
            description(new ArrayList<>());
        } else {
            FUtils.getInstance().registerEvent(this);
            description(Collections.singletonList(Component.text("Enabled")));
        }
        active = !active;
    }

    protected void enable() {

    }

    protected void disable() {
        
    }

    public static Component coloredComponent(String name) {
        return Component.text(name, Style.style(TextColor.fromHexString("#7866ff"), TextDecoration.ITALIC.withState(false)));
    }

    @Override
    public void onClick(Player clicker) {
        toggle();
    }
}
