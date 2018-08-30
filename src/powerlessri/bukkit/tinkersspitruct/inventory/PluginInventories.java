package powerlessri.bukkit.tinkersspitruct.inventory;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.inventory.Inventory;

import net.minecraft.server.v1_12_R1.BlockPosition;
import powerlessri.bukkit.tinkersspitruct.library.inventory.CompoundInventories;
import powerlessri.bukkit.tinkersspitruct.library.inventory.InventoryBuilder;

public class PluginInventories {
    
    private final IMachineInventoryBuilder builder;
    private final Map<BlockPosition, CompoundInventories> inventories;
    
    public PluginInventories(IMachineInventoryBuilder builder) {
        this.builder = builder;
        this.inventories = new HashMap<BlockPosition, CompoundInventories>();
    }
    
    public CompoundInventories getInventory(BlockPosition pos) {
        if(!this.inventories.containsKey(pos)) {
            this.inventories.put(pos, this.builder.makeInventory());
        }
        
        return this.inventories.get(pos);
    }
    
}
