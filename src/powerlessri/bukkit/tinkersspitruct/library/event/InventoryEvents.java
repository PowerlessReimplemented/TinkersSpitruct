package powerlessri.bukkit.tinkersspitruct.library.event;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.tinkersspitruct.Reference;
import powerlessri.bukkit.tinkersspitruct.library.helpers.tags.CommonItemTags;

public class InventoryEvents {

    private InventoryEvents() {
    }

    public static void processStackClickEvent(NBTTagCompound tag) {
        if(tag.hasKey(CommonItemTags.CLICK_EVENT_CATEGORY.getKey()) &&
                tag.hasKey(CommonItemTags.CLICK_EVENT_ID.getKey())) {
            
            Reference.getPlugin()
                .getEventCalls(tag.getString(CommonItemTags.CLICK_EVENT_CATEGORY.getKey()))
                .call(tag.getInt(CommonItemTags.CLICK_EVENT_ID.getKey()));
        }
    }
    
    public static boolean shouldCancelImmovable(NBTTagCompound tag) {
        if(tag.hasKey(CommonItemTags.IS_STACK_IMMOVABLE.getKey())) {
            return tag.getBoolean(CommonItemTags.IS_STACK_IMMOVABLE.getKey());
        }

        return false;
    }

}
