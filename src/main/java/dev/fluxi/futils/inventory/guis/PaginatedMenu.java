package dev.fluxi.futils.inventory.guis;

import dev.fluxi.futils.inventory.BaseInventory;
import dev.fluxi.futils.inventory.guis.utils.Page;
import dev.fluxi.futils.inventory.items.Item;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaginatedMenu extends BaseInventory {
    public int page = 0;
    public final List<Page> pages = new ArrayList<>();

    public PaginatedMenu(Component title, List<Item> items) {
        super(title, 5, new ArrayList<>());
        createPages(items);
        items(pages().get(page).items());
    }

    public void createPages(List<Item> items) {
        List<Item> modifiableItems = new ArrayList<>(items);
        if (modifiableItems.size() % 8 != 0) {
            modifiableItems.addAll(Collections.nCopies(8 - (modifiableItems.size() % 8), new Item(Material.GRAY_STAINED_GLASS_PANE)));
        }
        for (int i = 0; i + 8 <= modifiableItems.size(); i += 8) {
            pages.add(new Page(modifiableItems.subList(i, i + 8)));
        }
    }

    @Override
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        super.onInventoryClick(event);
        switch (event.getSlot()) {
            case 36:
                backToMain((Player) event.getWhoClicked());
                break;
            case 44:
                if (page < pages.size() - 1 && event.isLeftClick()) {
                    page++;
                }
                if (page > 0 && event.isRightClick()) {
                    page--;
                }
                items(pages().get(page).items());
        }
        items().get(event.getSlot()).onClick();
        renderItems();
    }

    public List<Page> pages() {
        return pages;
    }
}
