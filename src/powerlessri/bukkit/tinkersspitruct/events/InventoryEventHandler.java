package powerlessri.bukkit.tinkersspitruct.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;
import powerlessri.bukkit.tinkersspitruct.library.tags.CommonTags.ItemTags;
import powerlessri.bukkit.tinkersspitruct.tags.PluginTagHelper;

public class InventoryEventHandler implements Listener {

    public InventoryEventHandler() {
    }
    
    @EventHandler
    public void onInventoryClicked(InventoryClickEvent event) {
        TinkersSpitruct plugin = TinkersSpitruct.plugin;
        ItemStack stack = event.getCurrentItem();
        
        NBTTagCompound tag = PluginTagHelper.getPluginTag(stack);
        
        // Start counting slot from top, so the non-player inventory always have the same slot & raw slot
        if(event.getSlot() == event.getRawSlot() && tag != null) {
            plugin.toolBuilders.handleStackClicked(event, stack, tag);;
            
            event.setCancelled(shouldCancelImmovable(tag));
        }
    }
    
    

    
    private boolean shouldCancelImmovable(NBTTagCompound tag) {
        if(tag.hasKey(ItemTags.IS_STACK_IMMOVABLE.getKey())) {
            return tag.getBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey());
        }
        return false;
    }

}
