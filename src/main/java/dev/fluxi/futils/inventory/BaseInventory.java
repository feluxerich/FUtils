package dev.fluxi.futils.inventory;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.inventory.guis.MainMenu;
import dev.fluxi.futils.inventory.items.Item;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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

public class BaseInventory implements Listener {
    private Inventory inventory;
    private List<Item> items;

    public BaseInventory(Component title, int rows, List<Item> items) {
        FUtils.getInstance().registerEvent(this);
        items(items);
        inventory(Bukkit.createInventory(null, rows * 9, title));
    }

    /**
     * Rearranges and renders all items that should be displayed.
     */
    protected void renderItems() {
        int itemIndex = 0;
        for (Item item : items) {
            inventory.setItem(itemIndex, item.item());
            itemIndex++;
        }
    }

    /**
     * Opens the {@link MainMenu Main Menu}.
     *
     * @param player The player to open the previous inventory.
     */
    public void backToMain(Player player) {
        player.closeInventory();
        player.openInventory(new MainMenu().inventory());
    }

    /**
     * Sets all the necessary placeholders and the controls
     */
    protected void setInventoryItems() {
        renderItems();
    }

    /**
     * Creates an {@link ItemStack} only by material. The name of the {@link ItemStack} is
     * an empty {@link String}.
     *
     * @param material The material of the {@link ItemStack}.
     * @return Returns the created {@link ItemStack}.
     */
    public static ItemStack createItem(Material material) {
        return createItem(material, 1);
    }

    /**
     * Creates an {@link ItemStack} only by material and amount. The name of the {@link ItemStack} is
     * an empty {@link String}.
     *
     * @param material The material of the {@link ItemStack}.
     * @return Returns the created {@link ItemStack}.
     */
    public static ItemStack createItem(Material material, int amount) {
        return createItem(material, Component.text(""), amount);
    }

    /**
     * Creates an {@link ItemStack} by material and name.
     *
     * @param material The material of the {@link ItemStack}.
     * @param name     A {@link Component} which sets the name of the {@link ItemStack}.
     * @return Returns the created {@link ItemStack}.
     */
    public static ItemStack createItem(Material material, Component name) {
        return createItem(material, name, 1);
    }

    /**
     * Creates an {@link ItemStack} by material, name and amount.
     *
     * @param material The material of the {@link ItemStack}.
     * @param name     A {@link Component} which sets the name of the {@link ItemStack}.
     * @param amount   The amount of the {@link ItemStack}.
     * @return Returns the created {@link ItemStack}.
     */
    public static ItemStack createItem(Material material, Component name, int amount) {
        return createItem(material, name, null, amount);
    }

    /**
     * Creates an {@link ItemStack} by material, name and lore.
     *
     * @param material The material of the {@link ItemStack}.
     * @param name     A {@link Component} which sets the name of the {@link ItemStack}.
     * @param lore     List of {@link Component} representing the lore of the {@link ItemStack}.
     * @return Returns the created {@link ItemStack}.
     */
    public static ItemStack createItem(Material material, Component name, List<Component> lore) {
        return createItem(material, name, lore, 1);
    }

    /**
     * Creates an {@link ItemStack} by material, name, lore and amount.
     *
     * @param material The material of the {@link ItemStack}.
     * @param name     A {@link Component} which sets the name of the {@link ItemStack}.
     * @param lore     List of {@link Component} representing the lore of the {@link ItemStack}.
     * @param amount   The amount of the {@link ItemStack}.
     * @return Returns the created {@link ItemStack}.
     */
    public static ItemStack createItem(Material material, Component name, List<Component> lore, int amount) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(name);
        if (lore != null) {
            itemMeta.lore(lore);
        }
        item.setItemMeta(itemMeta);
        item.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        return item;
    }

    /**
     * Disables every click action in the opened inventory
     *
     * @param event The click event
     * @see InventoryClickEvent
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getInventory() != inventory) {
            return;
        }
        event.setCancelled(true);
    }

    /**
     * Disables every interaction in the opened inventory
     *
     * @param event The interaction event
     * @see InventoryInteractEvent
     */
    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent event) {
        if (event.getInventory() != inventory) {
            return;
        }
        event.setCancelled(true);
    }

    /**
     * Disables every drag action in the opened inventory
     *
     * @param event The drag event
     * @see InventoryDragEvent
     */
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getInventory() != inventory) {
            return;
        }
        event.setCancelled(true);
    }

    /**
     * Removes this listener class from the plugin if the inventory got closed.
     *
     * @param event The close event
     * @see InventoryCloseEvent
     */
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getInventory() != inventory) {
            return;
        }
        FUtils.getInstance().unregisterEvent(this);
    }

    public Inventory inventory() {
        setInventoryItems();
        return inventory;
    }

    public void inventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<Item> items() {
        return items;
    }

    public void items(List<Item> items) {
        this.items = items;
    }
}
