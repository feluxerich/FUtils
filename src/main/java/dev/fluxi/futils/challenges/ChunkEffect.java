package dev.fluxi.futils.challenges;

import dev.fluxi.futils.challenges.utils.Challenge;
import dev.fluxi.futils.utils.ChallengeUtils;
import dev.fluxi.futils.utils.RandomUtils;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class ChunkEffect extends Challenge {
    private final Map<Chunk, PotionEffectType> effectMap = new HashMap<>();

    public ChunkEffect() {
        super(Material.SPLASH_POTION, coloredComponent("Chunk Effect"), "chunk-effect");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        handleMovement(event.getFrom().getChunk(), event.getTo().getChunk(), event.getPlayer());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        addEffect(event.getPlayer(), event.getPlayer().getChunk());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        removeEffect(event.getPlayer(), event.getPlayer().getChunk());
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        handleMovement(event.getFrom().getChunk(), event.getTo().getChunk(), event.getPlayer());
    }

    private void removeEffect(Player player, Chunk chunk) {
        player.removePotionEffect(getEffect(chunk));
    }

    private void addEffect(Player player, Chunk chunk) {
        PotionEffectType effect = getEffect(chunk);
        player.addPotionEffect(effect.createEffect(20 * 10000, RandomUtils.random(5) + 1));
    }

    private PotionEffectType getEffect(Chunk chunk) {
        if (effectMap.containsKey(chunk)) return effectMap.get(chunk);
        PotionEffectType effectType = RandomUtils.randomEffectType();
        if (effectType.isInstant()) return getEffect(chunk);
        effectMap.put(chunk, effectType);
        return effectType;
    }

    private void handleMovement(Chunk from, Chunk to, Player player) {
        if (!ChallengeUtils.shouldExecute(player) || from == to) return;
        removeEffect(player, from);
        addEffect(player, to);
    }
}
