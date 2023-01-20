package dev.fluxi.futils.inventory.guis.utils;

import dev.fluxi.futils.inventory.items.Item;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Page {
    private List<Item> items;

    public Page(List<Item> items) {
        items(createPageItems(items));
    }

    public List<Item> createPageItems(List<Item> items) {
        List<Item> pageItems = placeItems(items);
        pageItems.set(36, new Item(Material.DARK_OAK_DOOR, Component.text(
                "Back to Main",
                Style.style(TextColor.fromHexString("#7866ff"),
                        TextDecoration.ITALIC.withState(false))
        )));
        pageItems.set(44, new Item(
                Material.ARROW,
                Component.text("Scroll", Style.style(TextColor.fromHexString("#7866ff"),
                        TextDecoration.ITALIC.withState(false))),
                Arrays.asList(
                        Component.text("Left click >> Scroll forwards", Style.style(TextColor.fromHexString("#22c55e"), TextDecoration.ITALIC.withState(false))),
                        Component.text("Right click >> Scroll backwards", Style.style(TextColor.fromHexString("#ef4444"), TextDecoration.ITALIC.withState(false)))
                )
        ));
        return pageItems;
    }

    public List<Item> placeItems(List<Item> items) {
        List<Item> pageItems = new ArrayList<>();
        Item black = new Item(Material.BLACK_STAINED_GLASS_PANE, Component.text(""));
        int itemIndex = 0;
        for (int row = 0; row < 5; row++) {
            for (int slot = 0; slot < 9; slot++) {
                if (row % 2 == 0 || slot % 2 == 0) {
                    pageItems.add(black);
                    continue;
                }
                pageItems.add(items.get(itemIndex));
                itemIndex++;
            }
        }
        return pageItems;
    }

    public List<Item> items() {
        return items;
    }

    public void items(List<Item> pageItems) {
        this.items = pageItems;
    }
}
