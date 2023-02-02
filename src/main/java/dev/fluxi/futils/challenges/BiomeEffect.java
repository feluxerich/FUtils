package dev.fluxi.futils.challenges;

import dev.fluxi.futils.challenges.utils.Challenge;
import dev.fluxi.futils.utils.ChallengeUtils;
import dev.fluxi.futils.utils.RandomUtils;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class BiomeEffect extends Challenge {
    private final Map<Biome, PotionEffectType> effectMap = new HashMap<>();

    public BiomeEffect() {
        super(Material.LINGERING_POTION, coloredComponent("Biome Effect"), "biome-effect");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        handleMovement(event.getFrom().getBlock().getBiome(), event.getTo().getBlock().getBiome(), event.getPlayer());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        addEffect(event.getPlayer(), event.getPlayer().getLocation().getBlock().getBiome());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        removeEffect(event.getPlayer(), event.getPlayer().getLocation().getBlock().getBiome());
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        handleMovement(event.getFrom().getBlock().getBiome(), event.getTo().getBlock().getBiome(), event.getPlayer());
    }

    private void removeEffect(Player player, Biome biome) {
        player.removePotionEffect(getEffect(biome));
    }

    private void addEffect(Player player, Biome biome) {
        PotionEffectType effect = getEffect(biome);
        player.addPotionEffect(effect.createEffect(20 * 10000, RandomUtils.random(5) + 1));
    }

    private PotionEffectType getEffect(Biome biome) {
        if (effectMap.containsKey(biome)) return effectMap.get(biome);
        PotionEffectType effectType = RandomUtils.randomEffectType();
        if (effectType.isInstant()) return getEffect(biome);
        effectMap.put(biome, effectType);
        return effectType;
    }

    private void handleMovement(Biome from, Biome to, Player player) {
        if (!ChallengeUtils.shouldExecute(player) || from == to) return;
        removeEffect(player, from);
        addEffect(player, to);
    }
}
