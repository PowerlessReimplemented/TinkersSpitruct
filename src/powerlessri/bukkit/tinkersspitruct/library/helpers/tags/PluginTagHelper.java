package powerlessri.bukkit.tinkersspitruct.library.helpers.tags;

import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.tinkersspitruct.Reference;

public class PluginTagHelper {
    
    private PluginTagHelper() {
    }
    
    public static NBTTagCompound getPluginTag(ItemStack stack) {
        NBTTagCompound tag = TagHelper.getStackTag(stack);
        NBTTagCompound pluginTag = TagHelper.getTagSafe(tag, Reference.PLUGIN_ID);
        
        return pluginTag;
    }
    
    public static ItemStack saveToStack(ItemStack stack, NBTTagCompound tag) {
        return TagHelper.setStackTag(stack, tag);
    }
    
    
    public static boolean hasPluginTag(ItemStack stack) {
        if(!TagHelper.hasStackTag(stack)) {
            return false;
        }
        
        return TagHelper.getStackTag(stack).hasKey(Reference.PLUGIN_ID);
    }
    
    
    public static boolean isStackImmovable(ItemStack stack) {
        NBTTagCompound tag = getPluginTag(stack);
        if(tag.hasKey(CommonItemTags.IS_STACK_IMMOVABLE.getKey())) {
            return tag.getBoolean(CommonItemTags.IS_STACK_IMMOVABLE.getKey());
        }
        
        return false;
    }
    
}
