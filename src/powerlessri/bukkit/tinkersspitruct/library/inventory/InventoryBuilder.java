package powerlessri.bukkit.tinkersspitruct.library.inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.tinkersspitruct.library.tags.CommonItemTags;
import powerlessri.bukkit.tinkersspitruct.library.tags.PluginTagHelper;

public class InventoryBuilder {
    
    public static InventoryBuilder createBuilder(int amountRows, String title) {
        InventoryBuilder builder = new InventoryBuilder();
        builder.clear(amountRows * 9, title);
        return builder;
    }
    
    
    public InventoryBuilder() {
    }
    
    private int amountSlots;
    private String inventoryTitle;
    
    private ItemStack[] inventoryMap;
    
    public void clear(int amountSlots, String title) {
        this.amountSlots = amountSlots;
        this.inventoryTitle = title;
        
        // 9 * 6 (amount per row * maximum amount of rows)
        this.inventoryMap = new ItemStack[amountSlots];
    }
    
    public void addImmovableSlot(int slot, ItemStack stack) {
        if(!PluginTagHelper.isStackImmovable(stack)) {
            NBTTagCompound tag = PluginTagHelper.getPluginTag(stack);
            tag.setBoolean(CommonItemTags.IS_STACK_IMMOVABLE.getKey(), true);
        }
        
        inventoryMap[slot] = stack;
    }
    
    public Inventory makeInventory() {
        Inventory result = Bukkit.createInventory(null, this.amountSlots, inventoryTitle);
        
        for(int i = 0; i < this.amountSlots; i++) {
            result.setItem(i, inventoryMap[i].clone());
        }
        
        return result;
    }
    
}
