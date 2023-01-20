package dev.fluxi.futils.inventory.guis.timer;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.inventory.guis.PaginatedMenu;
import dev.fluxi.futils.inventory.items.Item;
import dev.fluxi.futils.utils.Timer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TimerMenu extends PaginatedMenu {
    static Timer timer = FUtils.getInstance().getTimer();

    public TimerMenu(Component title) {
        super(title, TimerMenu.initializeItems());
    }

    @Override
    public void createPages(List<Item> items) {
        for (int i = 0; i + 10 <= items.size(); i += 10) {
            pages.add(new TimerPage(items.subList(i, i + 10)));
        }
    }

    public static List<Item> initializeItems() {
        Item increaseButton = new Item(Material.LIME_DYE, Component.text("Add", Style.style(TextColor.fromHexString("#5b45ff"), TextDecoration.ITALIC.withState(false))));
        Item decreaseButton = new Item(Material.RED_DYE, Component.text("Subtract", Style.style(TextColor.fromHexString("#5b45ff"), TextDecoration.ITALIC.withState(false))));
        List<Item> items = new ArrayList<>(Collections.nCopies(3, increaseButton));
        items.add(new Item(Material.CLOCK, Component.text("Timer", Style.style(TextColor.fromHexString("#5b45ff"), TextDecoration.ITALIC.withState(false))), Arrays.asList(
                Component.text(timer.prettifyTime(), Style.style(TextColor.fromHexString("#7866ff"), TextDecoration.ITALIC.withState(false))),
                Component.text("Click to toggle Timer", Style.style(TextColor.fromHexString("#7866ff"), TextDecoration.ITALIC.withState(false)))
        )));
        items.addAll(Collections.nCopies(3, new Item(Material.COMMAND_BLOCK)));
        items.addAll(Collections.nCopies(3, decreaseButton));

        // Page 2
        items.addAll(Collections.nCopies(3, increaseButton));
        items.add(new Item(Material.ARROW, Component.text("Invert Timer", Style.style(TextColor.fromHexString("#5b45ff"), TextDecoration.ITALIC.withState(false))), Collections.singletonList(Component.text(timer.countsUp() ? "Counts up" : "Counts down",
                Style.style(TextColor.fromHexString("#7866ff"), TextDecoration.ITALIC.withState(false))))));
        items.addAll(Collections.nCopies(3, new Item(Material.COMMAND_BLOCK)));
        items.addAll(Collections.nCopies(3, decreaseButton));
        return items;
    }

    @Override
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        super.onInventoryClick(event);
        switch (event.getSlot()) {
            case 19:
                if (page == 0) {
                    timer.toggle();
                } else if (page == 1) {
                    timer.invert();
                }
                break;
            case 12:
                if (page == 0) {
                    timer.time(timer.time() + 1);
                } else if (page == 1) {
                    timer.time(timer.time() + 600);
                }
                break;
            case 14:
                if (page == 0) {
                    timer.time(timer.time() + 10);
                } else if (page == 1) {
                    timer.time(timer.time() + 1800);
                }
                break;
            case 16:
                if (page == 0) {
                    timer.time(timer.time() + 60);
                } else if (page == 1) {
                    timer.time(timer.time() + 3600);
                }
                break;
            case 30:
                if (page == 0) {
                    timer.time(timer.time() - 1);
                } else if (page == 1) {
                    timer.time(timer.time() - 600);
                }
                break;
            case 32:
                if (page == 0) {
                    timer.time(timer.time() - 10);
                } else if (page == 1) {
                    timer.time(timer.time() - 1200);
                }
                break;
            case 34:
                if (page == 0) {
                    timer.time(timer.time() - 60);
                } else if (page == 1) {
                    timer.time(timer.time() - 3600);
                }
                break;
        }
        recreatePages();
    }

    private void recreatePages() {
        pages.clear();
        createPages(initializeItems());
        items(pages().get(page).items());
        renderItems();
    } // TODO: make this method prettier
}
