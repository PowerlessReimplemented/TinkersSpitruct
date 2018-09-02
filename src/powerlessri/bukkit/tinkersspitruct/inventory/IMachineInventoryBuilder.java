package powerlessri.bukkit.tinkersspitruct.inventory;

import java.util.Map;
import java.util.UUID;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.BlockPosition;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.tinkersspitruct.library.inventory.InventorySequence;

/** Builder for making compound inventories */
public interface IMachineInventoryBuilder {
    
    void handleStackClicked(InventoryClickEvent event, ItemStack stack, NBTTagCompound tag);
    void handleOpenInventory(PlayerInteractEvent event, int x, int y, int z);
    void handleBlockPlacement(PlayerInteractEvent event, int x, int y, int z);
    
    InventorySequence makeInventory();
    
    /** An UUID:Inventory map for storing playing specific data */
    Map<UUID, InventorySequence> getPlayerMap();
    
    InventorySequence getPlayerOwnedInv(UUID uuid);
    
    boolean doesPosExists(int x, int y, int z);
    boolean doesPosExists(BlockPosition pos);
    
}
