package powerlessri.bukkit.tinkersspitruct.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.library.events.IInventoryEventHandler;
import powerlessri.bukkit.library.registry.ItemBase;
import powerlessri.bukkit.library.tags.CommonTags.ItemTags;
import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;
import powerlessri.bukkit.tinkersspitruct.tags.PluginTagHelper;

public class InventoryClickHandler implements Listener {

    private final TinkersSpitruct plugin;

    public InventoryClickHandler(TinkersSpitruct plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClicked(InventoryClickEvent event) {
        ItemStack stack = event.getCurrentItem();

        NBTTagCompound tag = PluginTagHelper.getPluginTag(stack);
        ItemBase item = plugin.itemRegistry.getRegistryItem( PluginTagHelper.getStackId(tag) );
        
        // TODO nested if clean-up

        // Start counting slot from top, so a non-player inventory will always have the same slot & raw slot
        if(event.getSlot() == event.getRawSlot() && tag != null) {
            // TODO move function to registry system
            plugin.toolBuilders.handleStackClicked(event, stack, tag);

            if(item != null && item instanceof IInventoryEventHandler) {
                ((IInventoryEventHandler) item).onItemClicked(stack, tag, event);
            }

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
