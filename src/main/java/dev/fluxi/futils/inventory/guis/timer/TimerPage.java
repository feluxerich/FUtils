package dev.fluxi.futils.inventory.guis.timer;

import dev.fluxi.futils.inventory.guis.utils.Page;
import dev.fluxi.futils.inventory.items.Item;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class TimerPage extends Page {
    public TimerPage(List<Item> items) {
        super(items);
    }

    @Override
    public List<Item> placeItems(List<Item> items) {
        List<Item> pageItems = new ArrayList<>();
        Item black = new Item(Material.BLACK_STAINED_GLASS_PANE, Component.text(""));
        int itemIndex = 0;
        for (int row = 0; row < 5; row++) {
            for (int slot = 0; slot < 9; slot++) {
                if (row == 0 || row == 4 || (row == 1 || row == 3) && slot < 3 || slot % 2 == 0) {
                    pageItems.add(black);
                    continue;
                }
                pageItems.add(items.get(itemIndex));
                itemIndex++;
            }
        }
        return pageItems;
    }
}
