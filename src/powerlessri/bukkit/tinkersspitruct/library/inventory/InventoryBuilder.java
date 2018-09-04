package powerlessri.bukkit.tinkersspitruct.library.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import powerlessri.bukkit.tinkersspitruct.library.tags.TagHelper;
import powerlessri.bukkit.tinkersspitruct.library.tags.CommonTags.ItemTags;

public class InventoryBuilder {
    
    /** ItemStack used for marking the slot will be left blank */
    public static final ItemStack EMPTY_STACK = new ItemStack(Material.AIR);
    
    /** A light gray glass pane for blocking the inventory */
    public static final ItemStack SLOT_BLOCKER_GLASSPANE = 
            ItemTags.IS_STACK_IMMOVABLE.fix(
            ItemTags.ROOT_TAG.fix(
                    TagHelper.getTaggedStack(
                            new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 8))));
    
    static {
        ItemMeta blockerMeta = SLOT_BLOCKER_GLASSPANE.getItemMeta();
        
        blockerMeta.setDisplayName("");
        
        SLOT_BLOCKER_GLASSPANE.setItemMeta(blockerMeta);
    }
    
    
    
    private static int currentId = 0;
    
    private static int nextId() {
        return currentId++;
    }
    
    
    
    public final String id;
    public final int amountRows;
    public final int amountSlots;
    
    private final String inventoryTitle;
    
    private ItemStack[] inventoryMap;
    
    @Deprecated
    public InventoryBuilder(int amountRows) {
        this(amountRows, "Chest");
    }
    
    @Deprecated
    public InventoryBuilder(int amountRows, String title) {
        this(String.valueOf(nextId()), amountRows, title);
    }
    
    /** Parameter {@code id} is suggested to be unique, but does not need to be inside this implementation. */
    public InventoryBuilder(String id, int amountRows, String title) {
        this.id = id;
        
        this.amountRows = amountRows;
        this.amountSlots = amountRows * 9;
        this.inventoryTitle = title;
        
        this.inventoryMap = new ItemStack[amountSlots];
    }
    
    public void addImmovableSlot(int slot, ItemStack stack) {
        if(!ItemTags.IS_STACK_IMMOVABLE.is(stack)) {
            stack = ItemTags.IS_STACK_IMMOVABLE.fix(stack);
        }
        
        this.addSlot(slot, stack);
    }
    
    public void addSlot(int slot, ItemStack stack) {
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
                // When adding an ItemStack to an inventory, it will
                // automatically make a copy of the ItemStack.
                result.setItem(i, this.inventoryMap[i]);
            }
        }
        
        return result;
    }
    
}
