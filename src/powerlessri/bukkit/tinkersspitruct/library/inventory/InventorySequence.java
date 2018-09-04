package powerlessri.bukkit.tinkersspitruct.library.inventory;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

/**
 * A inventory provider that is used for switch between different modes
 * on a machine.
 */
public class InventorySequence {
    
    private final Map<String, Inventory> inventoryList;
    private String currentKey;
    
    public InventorySequence() {
        this.inventoryList = new HashMap<String, Inventory>();
    }
    
    public void setCurrentInventory(String key) {
        this.currentKey = key;
    }
    
    public Inventory getInventory() {
        Inventory result = this.inventoryList.get(this.currentKey);
        
        if(result == null) {
            return this.inventoryList.entrySet().iterator().next().getValue();
        }
        
        return result;
    }
    
    public void addInventory(String key, Inventory inventory) {
        if(this.currentKey == null || this.currentKey.equals("")) {
            this.currentKey = key;
        }
        
        this.inventoryList.put(key, inventory);
    }
    
}
