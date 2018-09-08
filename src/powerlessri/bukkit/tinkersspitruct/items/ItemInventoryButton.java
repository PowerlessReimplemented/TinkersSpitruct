package powerlessri.bukkit.tinkersspitruct.items;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.library.registry.ItemBase;

public class ItemInventoryButton extends ItemBase {
    
    public ItemInventoryButton() {
        this.setRegistryName("inventory_button");
    }

    @Override
    public void onItemClicked(ItemStack stack, NBTTagCompound tag, InventoryClickEvent event) {
    }
    
    
    
}
