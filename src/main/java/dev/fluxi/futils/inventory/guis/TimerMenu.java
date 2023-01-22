package dev.fluxi.futils.inventory.guis;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.inventory.BaseInventory;
import dev.fluxi.futils.inventory.items.Item;
import dev.fluxi.futils.utils.Timer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TimerMenu extends BaseInventory {
    static Timer timer = FUtils.getInstance().getTimer();

    public TimerMenu(Component title) {
        super(title, 5, TimerMenu.initializeItems());
    }

    public static List<Item> initializeItems() {
        Item increaseButton = new Item(Material.LIME_DYE, Component.text("Add", Style.style(TextColor.fromHexString("#5b45ff"), TextDecoration.ITALIC.withState(false))));
        Item decreaseButton = new Item(Material.RED_DYE, Component.text("Subtract", Style.style(TextColor.fromHexString("#5b45ff"), TextDecoration.ITALIC.withState(false))));
        List<Item> items = new ArrayList<>();
        items.add(new Item(Material.CLOCK, Component.text("Timer", Style.style(TextColor.fromHexString("#5b45ff"), TextDecoration.ITALIC.withState(false))), Arrays.asList(
                Component.text(timer.prettifyTime(), Style.style(TextColor.fromHexString("#7866ff"), TextDecoration.ITALIC.withState(false))),
                Component.text("Click to toggle Timer", Style.style(TextColor.fromHexString("#7866ff"), TextDecoration.ITALIC.withState(false))),
                Component.text(timer.running() ? "Running" : "Paused", Style.style(TextColor.fromHexString("#7866ff"), TextDecoration.ITALIC.withState(false)))
        )));
        items.addAll(Collections.nCopies(3, increaseButton));
        items.add(new Item(
                Material.COMMAND_BLOCK,
                null,
                Arrays.asList(
                        Component.text("Click: 1s", Style.style(TextColor.fromHexString("#7866ff"), TextDecoration.ITALIC.withState(false))),
                        Component.text("Shift-Click: 10s", Style.style(TextColor.fromHexString("#7866ff"), TextDecoration.ITALIC.withState(false)))
                )
        ));
        items.add(new Item(
                Material.COMMAND_BLOCK,
                null,
                Arrays.asList(
                        Component.text("Click: 1m", Style.style(TextColor.fromHexString("#7866ff"), TextDecoration.ITALIC.withState(false))),
                        Component.text("Shift-Click: 10m", Style.style(TextColor.fromHexString("#7866ff"), TextDecoration.ITALIC.withState(false)))
                )
        ));
        items.add(new Item(
                Material.COMMAND_BLOCK,
                null,
                Arrays.asList(
                        Component.text("Click: 30m", Style.style(TextColor.fromHexString("#7866ff"), TextDecoration.ITALIC.withState(false))),
                        Component.text("Shift-Click: 1h", Style.style(TextColor.fromHexString("#7866ff"), TextDecoration.ITALIC.withState(false)))
                )
        ));
        items.add(new Item(Material.ARROW, Component.text("Invert Timer", Style.style(TextColor.fromHexString("#5b45ff"), TextDecoration.ITALIC.withState(false))), Collections.singletonList(Component.text(timer.countsUp() ? "Counts up" : "Counts down",
                Style.style(TextColor.fromHexString("#7866ff"), TextDecoration.ITALIC.withState(false))))));
        items.addAll(Collections.nCopies(3, decreaseButton));

        List<Item> formattedItems = new ArrayList<>();
        Item black = new Item(Material.BLACK_STAINED_GLASS_PANE);
        int itemIndex = 0;
        for (int row = 0; row < 5; row++) {
            for (int slot = 0; slot < 9; slot++) {
                if (row == 0 || row == 4 || (row == 2) && slot < 3 || slot % 2 == 0) {
                    formattedItems.add(black);
                    continue;
                }
                formattedItems.add(items.get(itemIndex));
                itemIndex++;
            }
        }

        formattedItems.set(36, new Item(Material.DARK_OAK_DOOR, Component.text(
                "Back to Main",
                Style.style(TextColor.fromHexString("#7866ff"),
                        TextDecoration.ITALIC.withState(false))
        )));
        return formattedItems;
    }

    @Override
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        super.onInventoryClick(event);
        switch (event.getSlot()) {
            case 10:
                timer.toggle();
                break;
            case 28:
                timer.invert();
                break;
            case 12:
                if (!event.isShiftClick()) {
                    timer.time(timer.time() + 1);
                } else {
                    timer.time(timer.time() + 10);
                }
                break;
            case 14:
                if (!event.isShiftClick()) {
                    timer.time(timer.time() + 60);
                } else {
                    timer.time(timer.time() + 600);
                }
                break;
            case 16:
                if (!event.isShiftClick()) {
                    timer.time(timer.time() + 1800);
                } else {
                    timer.time(timer.time() + 3600);
                }
                break;
            case 30:
                if (!event.isShiftClick()) {
                    timer.time(timer.time() - 1);
                } else {
                    timer.time(timer.time() - 10);
                }
                break;
            case 32:
                if (!event.isShiftClick()) {
                    timer.time(timer.time() - 60);
                } else {
                    timer.time(timer.time() - 600);
                }
                break;
            case 34:
                if (!event.isShiftClick()) {
                    timer.time(timer.time() - 1800);
                } else {
                    timer.time(timer.time() - 3600);
                }
                break;
            case 36:
                backToMain((Player) event.getWhoClicked());
                break;
        }
        renderItems();
    }
}
