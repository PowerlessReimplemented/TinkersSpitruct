package powerlessri.bukkit.tinkersspitruct.inventory;

import org.bukkit.event.inventory.InventoryClickEvent;

import powerlessri.bukkit.tinkersspitruct.library.inventory.InventorySequence;

/** Builder for making compound inventories */
public interface IMachineInventoryBuilder {
    
    void handleStackClicked(InventoryClickEvent event);
    
    InventorySequence makeInventory();
    
}
