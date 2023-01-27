package dev.fluxi.futils.settings;

import dev.fluxi.futils.inventory.guis.PaginatedMenu;
import dev.fluxi.futils.inventory.items.Item;
import dev.fluxi.futils.settings.goals.KillBobGoal;
import dev.fluxi.futils.settings.goals.KillEnderDragonGoal;
import dev.fluxi.futils.settings.goals.KillWardenGoal;
import dev.fluxi.futils.settings.goals.KillWitherGoal;
import dev.fluxi.futils.settings.utils.Setting;
import dev.fluxi.futils.settings.utils.ToggleableSetting;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GoalSetting extends Setting {
    private final List<ToggleableSetting> goals = new ArrayList<>();

    public GoalSetting() {
        super(Material.CAULDRON, coloredComponent("Goals"), Collections.singletonList(coloredComponent("Set an event when the challenge is finished", true)));
        initializeGoals();
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.getWhoClicked().openInventory(new PaginatedMenu(
                Component.text("Goals", Style.style(TextColor.fromHexString("#7866ff"))),
                getGoals()
        ).inventory());
    }

    private void initializeGoals() {
        goals.add(new KillEnderDragonGoal());
        goals.add(new KillWitherGoal());
        goals.add(new KillBobGoal());
        goals.add(new KillWardenGoal());
    }

    private List<Item> getGoals() {
        return new ArrayList<>(goals);
    }
}
