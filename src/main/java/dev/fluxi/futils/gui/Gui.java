package dev.fluxi.futils.gui;

import dev.fluxi.futils.FUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Gui implements Listener {
    protected int rows;
    protected Component title;
    private final Inventory inventory;
    protected List<ItemStack> items;
    protected final ItemStack placeholder = createItem(Material.GRAY_STAINED_GLASS_PANE, Component.text(""), null);

    public Gui(Component title, int rows, List<ItemStack> items) {
        this.title = title;
        this.rows = rows;
        this.items = items;

        FUtils.getInstance().registerEvent(this);

        this.inventory = Bukkit.createInventory(null, rows * 9, getTitle());
        renderItems();
    }

    public void renderItems() {
        int itemIndex = 0;
        for (ItemStack item : items) {
            inventory.setItem(itemIndex, item);
            itemIndex++;
        }
    }

    public static ItemStack createItem(Material material, Component name, List<Component> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(name);
        if (lore != null) {
            itemMeta.lore(lore);
        }
        item.setItemMeta(itemMeta);
        item.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        return item;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory() != getInventory()) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent event) {
        if (event.getInventory() != getInventory()) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getInventory() != getInventory()) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory() != getInventory()) {
            return;
        }
        FUtils.getInstance().unregisterEvent(this);
    }

    public Component getTitle() {
        return title;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
