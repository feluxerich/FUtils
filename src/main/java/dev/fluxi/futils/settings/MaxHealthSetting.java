package dev.fluxi.futils.settings;

import dev.fluxi.futils.settings.utils.Setting;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Arrays;

public class MaxHealthSetting extends Setting {
    private double hearts = 20;

    public MaxHealthSetting() {
        super(Material.HEART_OF_THE_SEA, coloredComponent("Max Health"), Arrays.asList(
                coloredComponent("Left click >> +1 ♥"),
                coloredComponent("Right click >> -1 ♥"),
                coloredComponent("Shift click >> reset")
        ));
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        AttributeInstance maxHealth = event.getWhoClicked().getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (maxHealth == null) {
            return;
        }
        hearts = maxHealth.getValue();
        if (event.isShiftClick()) {
            hearts = maxHealth.getDefaultValue();
        } else if (event.isLeftClick()) {
            if (hearts < 512) hearts++;
        } else if (event.isRightClick()) {
            if (hearts > 1) hearts--;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            setMaxHealth(player, hearts);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        setMaxHealth(event.getPlayer(), hearts);
    }

    private void setMaxHealth(Player player, double hearts) {
        AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (maxHealth == null) {
            return;
        }
        maxHealth.setBaseValue(hearts);
    }
}
