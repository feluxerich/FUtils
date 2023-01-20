package dev.fluxi.futils.inventory.guis;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.inventory.BaseInventory;
import dev.fluxi.futils.inventory.items.Item;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Collections;

public class MainMenu extends BaseInventory {
    public MainMenu() {
        super(Component.text("FUtils", Style.style(TextColor.fromHexString("#5b45ff"))), 3, new ArrayList<>());
    }

    @Override
    public void setInventoryItems() {
        Item black = new Item(Material.BLACK_STAINED_GLASS_PANE);
        items().addAll(Collections.nCopies(11, black));
        items().add(new Item(
                Material.CLOCK,
                Component.text("Timer",
                        Style.style(TextColor.fromHexString("#5b45ff"),
                                TextDecoration.ITALIC.withState(false))),
                Collections.singletonList(
                        Component.text(
                                "Settings for the timer",
                                Style.style(TextColor.fromHexString("#7866ff"),
                                        TextDecoration.ITALIC.withState(false))
                        )
                )
                ));
        items().add(black);
        items().add(new Item(
                Material.DRAGON_EGG,
                Component.text("Challenges",
                        Style.style(TextColor.fromHexString("#5b45ff"),
                                TextDecoration.ITALIC.withState(false))),
                Collections.singletonList(
                        Component.text(
                                "Play Challenges",
                                Style.style(TextColor.fromHexString("#7866ff"),
                                        TextDecoration.ITALIC.withState(false))
                        )
                )
        ));
        items().add(black);
        items().add(new Item(
                Material.COMPARATOR,
                Component.text("Settings",
                        Style.style(TextColor.fromHexString("#5b45ff"),
                                TextDecoration.ITALIC.withState(false))),
                Collections.singletonList(
                        Component.text(
                                "Customize some world settings and game rules",
                                Style.style(TextColor.fromHexString("#7866ff"),
                                        TextDecoration.ITALIC.withState(false))
                        )
                )
        ));
        items().addAll(Collections.nCopies(11, black));
        renderItems();
    }

    @Override
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        super.onInventoryClick(event);
        switch (event.getSlot()) {
            case 11:
                break;
            case 13:
                event.getWhoClicked().openInventory(new PaginatedMenu(
                        Component.text("Challenges", Style.style(TextColor.fromHexString("#7866ff"))),
                        FUtils.getInstance().getChallengeManager().getChallenges()
                ).inventory());
                break;
            case 15:
                event.getWhoClicked().openInventory(new PaginatedMenu(
                        Component.text("Settings", Style.style(TextColor.fromHexString("#7866ff"))),
                        FUtils.getInstance().getSettingsManager().getSettings()
                ).inventory());
                break;
        }
    }
}
