package dev.fluxi.futils.inventory;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.inventory.items.Item;
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

public class ScrollMenu extends BaseInventory {
    private int scrollIndex = 0;
    private final List<Item> menuItems;

    public ScrollMenu(Component title, List<Item> items) {
        super(title, 3, new ArrayList<>());
        this.menuItems = items;
        FUtils.getInstance().registerEvent(this);
    }

    @Override
    public void setInventoryItems() {
        if (menuItems.size() < 8) {
            menuItems.addAll(Collections.nCopies(8 - menuItems.size(), new Item(Material.GRAY_STAINED_GLASS_PANE)));
        }
        List<Item> renderedItems = menuItems.subList(scrollIndex, scrollIndex + 8);
        items().addAll(Collections.nCopies(9, new Item(Material.BLACK_STAINED_GLASS_PANE)));
        for (Item item : renderedItems) {
            items().add(item);
        }
        items().add(new Item(
                Material.ARROW,
                Component.text("Scroll", Style.style(TextDecoration.ITALIC.withState(false))),
                Arrays.asList(
                        Component.text("Left click >> Scroll right", Style.style(TextColor.fromHexString("#22c55e"), TextDecoration.ITALIC.withState(false))),
                        Component.text("Right click >> Scroll left", Style.style(TextColor.fromHexString("#ef4444"), TextDecoration.ITALIC.withState(false)))
                )
        ));
        items().addAll(Collections.nCopies(9, new Item(Material.BLACK_STAINED_GLASS_PANE)));
        renderItems();
    }

    @Override
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        super.onInventoryClick(event);
        if (event.getCurrentItem() == null ||
                event.getCurrentItem().getType() == Material.LIGHT_GRAY_STAINED_GLASS_PANE ||
                event.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE) {
            return;
        }
        if (event.getSlot() == 17) {
            if (event.isLeftClick() && scrollIndex + 8 < menuItems.size()) {
                scrollIndex++;
            } else if (event.isRightClick() && scrollIndex > 0) {
                scrollIndex--;
            }
            setInventoryItems();
        }
    }
}
