package dev.fluxi.futils.challenges;

import dev.fluxi.futils.utils.challenge.Challenge;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OldPvP extends Challenge {
    public static final double oldAttackSpeed = 32;
    public static final double newAttackSpeed = 4;
    public OldPvP() {
        challengeName = "OldPvP";
        challengeItemMaterial = Material.DIAMOND_SWORD;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        setAttackSpeed(event.getPlayer(), oldAttackSpeed);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        setAttackSpeed(event.getPlayer(), newAttackSpeed);
    }

    @EventHandler
    public void onSweepDamage(EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK) {
            return;
        }
        event.setCancelled(true);
    }

    @Override
    public void enable() {
        super.enable();
        for (Player player : Bukkit.getOnlinePlayers()) {
            setAttackSpeed(player, oldAttackSpeed);
        }
    }

    @Override
    public void disable() {
        super.disable();
        for (Player player : Bukkit.getOnlinePlayers()) {
            setAttackSpeed(player, newAttackSpeed);
        }
    }

    protected void setAttackSpeed(Player player, double value) {
        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        if (attribute == null) {
            return;
        }
        attribute.setBaseValue(value);
    }
}
