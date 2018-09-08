package powerlessri.bukkit.tinkersspitruct.inventory;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.server.v1_12_R1.BlockPosition;
import powerlessri.bukkit.library.inventory.InventorySequence;

/** Store inventories by position in the world */
public class PositionalTiles {
    
    private final IMachineInventoryBuilder builder;
    private final Map<BlockPosition, InventorySequence> inventories;
    
    public PositionalTiles(IMachineInventoryBuilder builder) {
        this.builder = builder;
        this.inventories = new HashMap<BlockPosition, InventorySequence>();
    }
    
    public InventorySequence getInventory(BlockPosition pos) {
        if(!this.inventories.containsKey(pos)) {
            this.inventories.put(pos, this.builder.makeInventory());
        }
        
        return this.inventories.get(pos);
    }
    
}
