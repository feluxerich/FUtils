package dev.fluxi.futils.challenges;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.inventory.items.Toggleable;
import dev.fluxi.futils.utils.BlockUtils;
import dev.fluxi.futils.utils.ChallengeUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MirrorChunk extends Toggleable {
    private final Map<Location, Material> blockChanges = new HashMap<>();

    public MirrorChunk() {
        super(Material.TINTED_GLASS, coloredComponent("Mirror Chunk"), "mirror-chunk");
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!ChallengeUtils.shouldExecute(event.getPlayer())) {
            return;
        }
        blockChanges.put(BlockUtils.getInChunkCoordinates(event.getBlockPlaced().getLocation()), event.getBlockPlaced().getType());
        syncChunks();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!ChallengeUtils.shouldExecute(event.getPlayer())) {
            return;
        }
        blockChanges.put(BlockUtils.getInChunkCoordinates(event.getBlock().getLocation()), Material.AIR);
        syncChunks();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!FUtils.getInstance().getTimer().running() || event.getFrom().getChunk() == event.getTo().getChunk()) {
            return;
        }
        syncChunks();
    }

    private void syncChunks() {
        Bukkit.getScheduler().runTaskAsynchronously(FUtils.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                for (Chunk chunk : getSurroundingChunks(player.getChunk())) {
                    updateChunk(chunk);
                }
            }
        });
    }

    private void updateChunk(Chunk chunk) {
        for (Location location : blockChanges.keySet()) {
            Block block = BlockUtils.getBlockInChunk(chunk, location);
            Material material = blockChanges.get(location);
            block.setType(material, false);
        }
    }

    private List<Chunk> getSurroundingChunks(Chunk chunk) {
        int radius = 8;
        List<Chunk> chunks = new ArrayList<>();
        for (int x = -radius; x < radius; x++) {
            for (int z = -radius; z < radius; z++) {
                chunks.add(chunk.getWorld().getChunkAt(chunk.getX() + x, chunk.getZ() + z));
            }
        }
        return chunks;
    }
}
