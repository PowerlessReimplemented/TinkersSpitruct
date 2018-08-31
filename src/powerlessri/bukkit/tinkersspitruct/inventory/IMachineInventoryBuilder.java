package powerlessri.bukkit.tinkersspitruct.inventory;

import java.util.Map;
import java.util.UUID;

import org.bukkit.event.inventory.InventoryClickEvent;

import powerlessri.bukkit.tinkersspitruct.library.inventory.InventorySequence;

/** Builder for making compound inventories */
public interface IMachineInventoryBuilder {
    
    void handleStackClicked(InventoryClickEvent event);
    void handleInventorySwitching(InventorySequence inventories);
    
    InventorySequence makeInventory();
    
    /** An UUID:Inventory map for storing playing specific data */
    Map<UUID, InventorySequence> getPlayerMap();
    
    InventorySequence getPlayerOwnedInv(UUID uuid);
    
}
