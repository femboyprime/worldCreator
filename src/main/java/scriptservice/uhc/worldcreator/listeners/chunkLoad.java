package scriptservice.uhc.worldcreator.listeners;

import org.bukkit.Chunk;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkPopulateEvent;

public class chunkLoad implements Listener {
    private void setRoofedForest(Chunk chunk) {
        int roofedSize = 21; // ~325 block

        if (chunk.getX() <= roofedSize && chunk.getZ() <= roofedSize && chunk.getX() >= -roofedSize && chunk.getZ() >= -roofedSize) {
            for (int x = 0; x <= 16; x++) {
                for (int z = 0; z <= 16; z++) {
                    chunk.getBlock(x, 60, z).setBiome(Biome.ROOFED_FOREST);
                }
            }
        }
    }

    @EventHandler
    public void onChunkPopulate(ChunkPopulateEvent event) {
        if (!event.getWorld().getName().equalsIgnoreCase("world")) {
            setRoofedForest(event.getChunk());
        }

    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        if (!event.getWorld().getName().equalsIgnoreCase("world")) {
            setRoofedForest(event.getChunk());
        }
    }
}
