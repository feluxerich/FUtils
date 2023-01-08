package dev.fluxi.futils.gui;

import dev.fluxi.futils.FUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainGui extends Gui {
    public MainGui() {
        super(Component.text("FUtils"), 1, MainGui.initializeItems());
    }

    public static List<ItemStack> initializeItems() {
        ItemStack placeholder = Gui.createItem(Material.GRAY_STAINED_GLASS_PANE, Component.text(""), null);
        List<ItemStack> items = new ArrayList<>(Collections.nCopies(3, placeholder));
        items.add(Gui.createItem(Material.COMPASS, Component.text("Challenges"), null));
        items.add(placeholder);
        items.add(Gui.createItem(Material.COMMAND_BLOCK, Component.text("Settings"), null));
        items.addAll(Collections.nCopies(3, placeholder));
        return items;
    }

    @Override
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        super.onInventoryClick(event);
        if (event.getSlot() == 3) {
            event.getWhoClicked().closeInventory();
            event.getWhoClicked().openInventory(FUtils.getInstance().getChallengeManager().getInventory());
        }
        if (event.getSlot() == 5) {
            event.getWhoClicked().closeInventory();
            event.getWhoClicked().openInventory(FUtils.getInstance().getSettingsManager().getInventory());
        }
    }
}
