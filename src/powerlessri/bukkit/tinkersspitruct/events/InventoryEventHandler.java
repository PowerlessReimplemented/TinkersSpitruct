package powerlessri.bukkit.tinkersspitruct.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;
import powerlessri.bukkit.tinkersspitruct.library.tags.CommonTags.ItemTags;
import powerlessri.bukkit.tinkersspitruct.tags.PluginTagHelper;

public class InventoryEventHandler implements Listener {

    public InventoryEventHandler() {
    }
    
    @EventHandler
    public void onInventoryClicked(InventoryClickEvent event) {
        if(PluginTagHelper.hasPluginTag(event.getCurrentItem())) {
            NBTTagCompound tag = PluginTagHelper.getPluginTag(event.getCurrentItem());
            
            this.processStackClickEvent(tag);
            
            event.setCancelled(shouldCancelImmovable(tag));
        }
    }
    
    

    private void processStackClickEvent(NBTTagCompound tag) {
        if(tag.hasKey(InventoryToolBuilder.))
    }
    
    private boolean shouldCancelImmovable(NBTTagCompound tag) {
        if(tag.hasKey(ItemTags.IS_STACK_IMMOVABLE.getKey())) {
            return tag.getBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey());
        }
        return false;
    }

}
