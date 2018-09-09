package powerlessri.bukkit.tinkersspitruct.items;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.library.registry.ItemBase;
import powerlessri.bukkit.library.tags.CommonTags.ItemTags;

public class ItemInventoryButton extends ItemBase {
    
    public ItemInventoryButton() {
        super();
        
        this.setRegistryName("inventory_button");
        
        this.registryData.setBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey(), true);
    }

    @Override
    public void onItemClicked(ItemStack stack, NBTTagCompound tag, InventoryClickEvent event) {
    }
    
    @Override
    public ItemStack createItemStack() {
        return baseStack;
    }
    
}
