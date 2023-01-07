package dev.fluxi.futils.utils.gui;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.challenges.Challenge;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ChallengeGui extends Gui {
    private int scrollStatus = 0;
    private final List<Challenge> challenges;

    public ChallengeGui(Component title, List<Challenge> challenges) {
        super(title, 3, new ArrayList<>());
        this.challenges = challenges;
        createItemTable();
        FUtils.getInstance().registerEvent(this);
    }

    private void createItemTable() {
        if (challenges.size() < 8) {
            challenges.addAll(challenges.size(), Collections.nCopies(8 - challenges.size(), null));
        }
        List<Challenge> renderedChallenges = challenges.subList(scrollStatus, scrollStatus + 8);
        List<ItemStack> items = new ArrayList<>(Collections.nCopies(18, null));

        int itemIndex = 0;
        for (Challenge challenge : renderedChallenges) {
            items.set(itemIndex, challengeStatusIndicator(challenge));
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
        items.addAll(18, Collections.nCopies(3, placeholder));
        items.add(21, createItem(Material.LIME_DYE, Component.text("Start Challenge").color(TextColor.fromHexString("#22c55e")), null));
        items.add(22, challengeRunningIndicator());
        items.add(23, createItem(Material.RED_DYE, Component.text("Stop Challenge").color(TextColor.fromHexString("#ef4444")), null));
        items.addAll(24, Collections.nCopies(3, placeholder));

        this.items = items;
        renderItems();
    }

    private ItemStack challengeStatusIndicator(Challenge challenge) {
        if (challenge != null && challenge.isActive()) {
            return createItem(Material.LIME_STAINED_GLASS_PANE, Component.text(""), null);
        }
        return placeholder;
    }

    private ItemStack challengeRunningIndicator() {
        if (FUtils.getInstance().getChallengeManager().isRunning()) {
            return createItem(Material.AXOLOTL_BUCKET,
                    Component.text("Challenge running").color(TextColor.fromHexString("#22c55e")),
                    Collections.singletonList(Component.text("Click to pause").color(TextColor.fromHexString("#22c55e"))));
        }
        return createItem(Material.BUCKET,
                Component.text("Challenge paused").color(TextColor.fromHexString("#22c55e")),
                Collections.singletonList(Component.text("Click to resume").color(TextColor.fromHexString("#ef4444"))));

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
            if (event.isLeftClick() && scrollStatus + 8 < challenges.size()) {
                scrollStatus++;
            } else if (event.isRightClick() && scrollStatus > 0) {
                scrollStatus--;
            }
            createItemTable();
            return;
        }
        // Change active state of Challenge
        if (event.getSlot() > 8 && event.getSlot() < 17) {
            Challenge challenge = challenges.get(event.getSlot() - 9 + scrollStatus);
            challenge.setActive(!challenge.isActive());
            items.set(event.getSlot() - 9, challengeStatusIndicator(challenge));
            renderItems();
            return;
        }
        FUtils fUtils = FUtils.getInstance();
        switch (event.getSlot()) {
            case 21:
                fUtils.getChallengeManager().start();
                event.getWhoClicked().sendTitlePart(TitlePart.TITLE, Component.text("Started").color(TextColor.fromHexString("#5b45ff")));
                getInventory().close();
                //event.getWhoClicked().playSound(Sound.);
                // TODO: add levelup sound
                break;
            case 22:
                if (fUtils.getChallengeManager().isRunning()) {
                    fUtils.getChallengeManager().pause();
                    event.getWhoClicked().sendTitlePart(TitlePart.TITLE, Component.text("Paused").color(TextColor.fromHexString("#5b45ff")));
                    break;
                }
                fUtils.getChallengeManager().resume();
                event.getWhoClicked().sendTitlePart(TitlePart.TITLE, Component.text("Resumed").color(TextColor.fromHexString("#5b45ff")));
                break;
            case 23:
                fUtils.getChallengeManager().stop();
                event.getWhoClicked().sendTitlePart(TitlePart.TITLE, Component.text("Stopped").color(TextColor.fromHexString("#5b45ff")));
                getInventory().close();
                break;
        }
        // FIXME: why have i done this?
        items.set(22, challengeRunningIndicator());
        renderItems();
    }
}
