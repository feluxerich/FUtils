package dev.fluxi.futils.challenges;

import dev.fluxi.futils.FUtils;
import dev.fluxi.futils.inventory.items.Toggleable;
import dev.fluxi.futils.utils.Base64;
import dev.fluxi.futils.utils.BlockUtils;
import dev.fluxi.futils.utils.ChallengeUtils;
import dev.fluxi.futils.utils.ConfigUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class MirrorChunk extends Toggleable {
    private Map<Location, Material> blockChanges = new HashMap<>();

    public MirrorChunk() {
        super(Material.TINTED_GLASS, coloredComponent("Mirror Chunk"), "mirror-chunk");
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!ChallengeUtils.shouldExecute(event.getPlayer())) {
            return;
        }
        setVariablesFromConfigIfAbsent();
        blockChanges.put(BlockUtils.getInChunkCoordinates(event.getBlockPlaced().getLocation()), event.getBlockPlaced().getType());
        syncChunks();
        writeConfig();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!ChallengeUtils.shouldExecute(event.getPlayer())) {
            return;
        }
        Block block = event.getBlock();
        World world = block.getWorld();
        for (ItemStack drop : block.getDrops()) {
            world.dropItemNaturally(block.getLocation(), drop);
        }
        setVariablesFromConfigIfAbsent();
        blockChanges.put(BlockUtils.getInChunkCoordinates(event.getBlock().getLocation()), Material.AIR);
        syncChunks();
        writeConfig();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!FUtils.getInstance().getTimer().running() || event.getFrom().getChunk() == event.getTo().getChunk()) {
            return;
        }
        setVariablesFromConfigIfAbsent();
        syncChunks();
    }

    private void syncChunks() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (Chunk chunk : getSurroundingChunks(player.getChunk())) {
                updateChunk(chunk);
            }
        }
    }

    private void updateChunk(Chunk chunk) {
        for (Location location : blockChanges.keySet()) {
            Block block = BlockUtils.getBlockInChunk(chunk, location);
            Material material = blockChanges.get(location);
            block.setType(material, false);
        }
    }

    private List<Chunk> getSurroundingChunks(Chunk chunk) {
        int radius = 2;
        List<Chunk> chunks = new ArrayList<>();
        for (int x = -radius; x < radius + 1; x++) {
            for (int z = -radius; z < radius + 1; z++) {
                chunks.add(chunk.getWorld().getChunkAt(chunk.getX() + x, chunk.getZ() + z));
            }
        }
        return chunks;
    }

    private void setVariablesFromConfigIfAbsent() {
        if (!blockChanges.isEmpty()) {
            return;
        }
        readConfig();
    }

    public void writeConfig() {
        ConfigurationSection section = ConfigUtils.getConfigSection("challenges");
        if (!section.isSet(config)) {
            section = section.createSection(config);
        }
        section.set("block-changes", Base64.serializeAndEncode(blockChanges));
        FUtils.getInstance().saveConfig();
    }

    public void readConfig() {
        ConfigurationSection section = ConfigUtils.getConfigSection("challenges");
        if (!section.isConfigurationSection(config)) {
            return;
        }
        section = section.getConfigurationSection(config);
        if (section == null || !section.isSet("block-changes")) {
            return;
        }
        blockChanges = (Map<Location, Material>) Base64.deserializeAndDecode(section.getString("block-changes"));
    }
}
