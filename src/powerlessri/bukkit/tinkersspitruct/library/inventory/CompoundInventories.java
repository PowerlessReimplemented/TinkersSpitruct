package powerlessri.bukkit.tinkersspitruct.library.inventory;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.inventory.Inventory;

/**
 * A inventory provider that is used for switch between different modes
 * on a machine.
 */
public class CompoundInventories {
    
    private final Map<String, Inventory> inventoryList;
    private String key;
    
    public CompoundInventories() {
        this.inventoryList = new HashMap<String, Inventory>();
    }
    
    public void setCurrentInventory(String key) {
        this.key = key;
    }
    
    public Inventory getInventory() {
        if(this.inventoryList.containsKey(key)) {
            return this.inventoryList.get(key);
        }
        
        return this.inventoryList.entrySet().iterator().next().getValue();
    }
    
    public void addInventory(String key, Inventory inventory) {
        this.inventoryList.put(key, inventory);
    }
    
}
