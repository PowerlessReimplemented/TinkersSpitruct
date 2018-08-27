package powerlessri.bukkit.tinkersspitruct.library.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.tinkersspitruct.library.helpers.tags.CommonItemTags;
import powerlessri.bukkit.tinkersspitruct.library.helpers.tags.PluginTagHelper;

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
    
    private Map<ItemStack, Byte> stackMap;
    private List<ItemStack> stacks;
    private byte nextId;
    
    private byte[] inventoryMap;
    
    public void clear(int amountSlots, String title) {
        this.amountSlots = amountSlots;
        this.inventoryTitle = title;
        
        // 9 * 6 (amount per row * maximum amount of rows)
        this.inventoryMap = new byte[amountSlots];
        this.stackMap = new HashMap<ItemStack, Byte>();
        this.stacks = new ArrayList<ItemStack>();
        // Start with 1 because 0 represents empty stack
        this.nextId = (byte) 1;
    }
    
    public void addImmovableSlot(ItemStack stack, int slot) {
        if(!PluginTagHelper.isStackImmovable(stack)) {
            NBTTagCompound tag = PluginTagHelper.getPluginTag(stack);
            tag.setBoolean(CommonItemTags.IS_STACK_IMMOVABLE.getKey(), true);
        }
        
        if(!this.stackMap.containsKey(stack)) {
            this.stackMap.put(stack, nextId);
            this.stacks.add(stack);
            nextId++;
        }
        
        inventoryMap[slot] = this.stackMap.get(stack);
    }
    
    public Inventory makeInventory() {
        Inventory result = Bukkit.createInventory(null, this.amountSlots, inventoryTitle);
        
        for(int i = 0; i < this.amountSlots; i++) {
            if(inventoryMap[i] == 0) {
                continue;
            }
            result.setItem(i, this.stacks.get( inventoryMap[i]-1 ));
        }
        
        return result;
    }
    
}
