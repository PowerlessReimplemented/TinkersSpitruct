package powerlessri.bukkit.tinkersspitruct.library.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import powerlessri.bukkit.tinkersspitruct.library.tags.CommonTags.ItemTags;

public class InventoryHelper {
    
    public static final ItemStack EMPTY_STACK = new ItemStack(Material.AIR);
    
    private InventoryHelper() {
    }
    
    public static void removeAllMovables(Player player, Inventory inventory) {
        for(int i = 0; i < inventory.getSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            
            if(ItemTags.IS_STACK_IMMOVABLE.is(stack)) {
                inventory.setItem(i, EMPTY_STACK);
                player.getInventory().addItem(stack);
            }
        }
    }
    
}
