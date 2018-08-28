package powerlessri.bukkit.tinkersspitruct.tags;

import javax.annotation.Nullable;

import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;
import powerlessri.bukkit.tinkersspitruct.library.tags.helpers.TagHelper;

public class PluginTagHelper {
    
    private PluginTagHelper() {
    }
    
    @Nullable
    public static NBTTagCompound getPluginTag(ItemStack stack) {
        NBTTagCompound tag = TagHelper.getStackTag(stack);
        NBTTagCompound pluginTag = tag.getCompound(TinkersSpitruct.PLUGIN_ID);
        
        return pluginTag;
    }
    
    
    public static boolean hasPluginTag(ItemStack stack) {
        if(!TagHelper.hasTag(stack)) {
            return false;
        }
        
        return TagHelper.getStackTag(stack).hasKey(TinkersSpitruct.PLUGIN_ID);
    }
    
    
    public static boolean isStackImmovable(ItemStack stack) {
        NBTTagCompound tag = getPluginTag(stack);
        if(tag.hasKey(CommonItemTags.IS_STACK_IMMOVABLE.getKey())) {
            return tag.getBoolean(CommonItemTags.IS_STACK_IMMOVABLE.getKey());
        }
        
        return false;
    }
    
}
