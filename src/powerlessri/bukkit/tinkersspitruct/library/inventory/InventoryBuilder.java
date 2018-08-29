package powerlessri.bukkit.tinkersspitruct.library.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import powerlessri.bukkit.tinkersspitruct.library.tags.CommonTags.ItemTags;
import powerlessri.bukkit.tinkersspitruct.library.tags.helpers.TagHelper;
import powerlessri.bukkit.tinkersspitruct.tags.PluginTagHelper;

public class InventoryBuilder {
    
    /** ItemStack used for marking the slot will be left blank */
    public static final ItemStack EMPTY_STACK = new ItemStack(Material.AIR);
    
    /** A light gray glass pane for blocking the inventory */
    public static final ItemStack SLOT_BLOCKER_GLASSPANE = 
            ItemTags.IS_STACK_IMMOVABLE.fixItem(
            ItemTags.ROOT_TAG.fixItem(
                    TagHelper.getTaggedStack(
                            new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 8))));
    
    static {
    }
    
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
//            TinkersSpitruct.plugin.getLogger().info(stack.toString());
            stack = ItemTags.IS_STACK_IMMOVABLE.fixItem(stack);
        }
        
        this.inventoryMap[slot] = stack;
    }
    
    public void addBlankSlot(int slot) {
        this.inventoryMap[slot] = EMPTY_STACK;
    }
    
    public void blockEmptySlots() {
        for(int i = 0; i < this.amountSlots; i++) {
            if(this.inventoryMap[i] == null) {
                inventoryMap[i] = SLOT_BLOCKER_GLASSPANE;
            }
        }
    }
    
    public Inventory makeInventory() {
        Inventory result = Bukkit.createInventory(null, this.amountSlots, inventoryTitle);
        
        for(int i = 0; i < this.amountSlots; i++) {
            if(this.inventoryMap[i] != null && this.inventoryMap[i] != EMPTY_STACK) {
                // When adding an ItemStack to an inventory, the inventory will
                // automatically make a copy of the ItemStack.
                result.setItem(i, this.inventoryMap[i]);
            }
        }
        
        return result;
    }
    
}
