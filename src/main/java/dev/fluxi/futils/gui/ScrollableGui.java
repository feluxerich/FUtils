package dev.fluxi.futils.gui;

import dev.fluxi.futils.managers.Manager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ScrollableGui extends Gui {
    private int scrollStatus = 0;
    private final List<GuiAccessible> guiAccessibleList;
    private final Manager manager;

    public ScrollableGui(Component title, Manager manager) {
        super(title, 3, new ArrayList<>());
        this.manager = manager;
        this.guiAccessibleList = manager.items();
        createItemTable();
    }

    private void createItemTable() {
        if (guiAccessibleList.size() < 8) {
            guiAccessibleList.addAll(guiAccessibleList.size(), Collections.nCopies(8 - guiAccessibleList.size(), null));
        }
        List<GuiAccessible> renderedItems = guiAccessibleList.subList(scrollStatus, scrollStatus + 8);
        List<ItemStack> items = new ArrayList<>(Collections.nCopies(18, null));

        int itemIndex = 0;
        for (GuiAccessible challenge : renderedItems) {
            items.set(itemIndex, guiAccessibleStatusIndicator(challenge));
            if (challenge == null) {
                itemIndex++;
                continue;
            }
            items.set(itemIndex + 9, createItem(challenge.itemMaterial(),
                    Component.text(challenge.name()).color(TextColor.fromHexString("#5b45ff")),
                    null));
            itemIndex++;
        }
        items.set(8, placeholder);
        items.set(17, createItem(Material.ARROW, Component.text("Scroll"), Arrays.asList(
                Component.text("Left click >> Scroll right").color(TextColor.fromHexString("#22c55e")),
                Component.text("Right click >> Scroll left").color(TextColor.fromHexString("#ef4444"))
        )));
        items.addAll(18, Collections.nCopies(9, placeholder));

        this.items = items;
        renderItems();
    }

    private ItemStack guiAccessibleStatusIndicator(GuiAccessible guiAccessible) {
        if (guiAccessible != null && guiAccessible.active()) {
            return createItem(Material.LIME_STAINED_GLASS_PANE, Component.text(""), null);
        }
        return placeholder;
    }

    @Override
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        super.onInventoryClick(event);
        // Null check
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.LIME_STAINED_GLASS_PANE || event.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE) {
            return;
        }

        // If scroll Button
        if (event.getSlot() == 17) {
            if (event.isLeftClick() && scrollStatus + 8 < guiAccessibleList.size()) {
                scrollStatus++;
            } else if (event.isRightClick() && scrollStatus > 0) {
                scrollStatus--;
            }
            createItemTable();
        }
        // Change active state of Challenge
        if (event.getSlot() > 8 && event.getSlot() < 17) {
            GuiAccessible item = guiAccessibleList.get(event.getSlot() - 9 + scrollStatus);
            if (item.active()) {
                manager.disable(item);
            } else {
                manager.enable(item);
            }
            items.set(event.getSlot() - 9, guiAccessibleStatusIndicator(item));
        }
        renderItems();
    }
}
