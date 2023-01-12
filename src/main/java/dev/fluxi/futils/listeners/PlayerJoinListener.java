package dev.fluxi.futils.listeners;

import dev.fluxi.futils.components.LinearGradientComponent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().getName().equals("Rumsbums_")) {
            return;
        }
        event.getPlayer().playerListName(
                new LinearGradientComponent("[DEV]", TextColor.fromHexString("#5b45ff"), TextColor.fromHexString("#8b5cf6")).getGradientComponent().append(Component.text(" ")).append(
                        new LinearGradientComponent(event.getPlayer().getName(), TextColor.fromHexString("#8b5cf6"), TextColor.fromHexString("#5b45ff")).getGradientComponent()
                ));
    }
}
