package powerlessri.bukkit.library.events;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;

public interface IInventoryEventHandler {
    
    void onItemClicked(ItemStack stack, NBTTagCompound tag, InventoryClickEvent event);
    
}
