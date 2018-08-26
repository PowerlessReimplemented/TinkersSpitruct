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
    
}
