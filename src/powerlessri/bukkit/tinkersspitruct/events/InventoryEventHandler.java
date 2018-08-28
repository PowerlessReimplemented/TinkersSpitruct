package powerlessri.bukkit.tinkersspitruct.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;
import powerlessri.bukkit.tinkersspitruct.library.tags.helpers.CommonItemTags;
import powerlessri.bukkit.tinkersspitruct.tags.PluginTagHelper;

public class InventoryEventHandler implements Listener {

    public InventoryEventHandler() {
    }
    
    @EventHandler
    public void onInventoryClicked(InventoryClickEvent event) {
        if(PluginTagHelper.hasPluginTag(event.getCurrentItem())) {
            NBTTagCompound tag = PluginTagHelper.getPluginTag(event.getCurrentItem());
            
            this.processStackClickEvent(tag);
            
            if(this.shouldCancelImmovable(tag)) {
                event.setCancelled(true);
            }
        }
    }
    
    

    public void processStackClickEvent(NBTTagCompound tag) {
        if(tag.hasKey(CommonItemTags.CLICK_EVENT_CATEGORY.getKey()) &&
                tag.hasKey(CommonItemTags.CLICK_EVENT_ID.getKey())) {
            
            TinkersSpitruct.plugin
                .getEventCalls(tag.getString(CommonItemTags.CLICK_EVENT_CATEGORY.getKey()))
                .call(tag.getInt(CommonItemTags.CLICK_EVENT_ID.getKey()));
        }
    }
    
    public boolean shouldCancelImmovable(NBTTagCompound tag) {
        if(tag.hasKey(CommonItemTags.IS_STACK_IMMOVABLE.getKey())) {
            return tag.getBoolean(CommonItemTags.IS_STACK_IMMOVABLE.getKey());
        }

        return false;
    }

}
