package powerlessri.bukkit.tinkersspitruct.tags;

import javax.annotation.Nullable;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.library.tags.CommonTags.ItemTags;
import powerlessri.bukkit.library.tags.TagHelper;
import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;

public class PluginTagHelper {

    public static final String REGISTRY_NAME = "registryName";

    private PluginTagHelper() {
    }

    @Nullable
    public static NBTTagCompound getPluginTag(ItemStack stack) {
        NBTTagCompound tag = TagHelper.getStackTag(stack);

        if(tag != null) {
            NBTTagCompound pluginTag = tag.getCompound(TinkersSpitruct.PLUGIN_ID);

            return pluginTag;
        }

        return null;
    }


    public static boolean hasPluginTag(ItemStack stack) {
        if(!TagHelper.hasTag(stack)) {
            return false;
        }

        return TagHelper.getStackTag(stack).hasKey(TinkersSpitruct.PLUGIN_ID);
    }

    public static ItemStack addPluginTag(ItemStack stack) {
        if(!TagHelper.hasTag(stack)) {
            stack = TagHelper.getTaggedStack(stack);
        }

        NBTTagCompound tag = TagHelper.getStackTag(stack);
        tag.set(TinkersSpitruct.PLUGIN_ID, new NBTTagCompound());
        stack = saveToStack(stack, tag);

        return stack;
    }

    public static ItemStack saveToStack(ItemStack stack, NBTTagCompound tag) {
        NBTTagCompound rootTag = TagHelper.getStackTag(stack);

        if(rootTag != null) {
            rootTag.set(TinkersSpitruct.PLUGIN_ID, tag);
            return TagHelper.getStackWithTag(stack, rootTag);
        }

        return stack;
    }



    public static boolean isStackImmovable(ItemStack stack) {
        NBTTagCompound tag = getPluginTag(stack);
        if(tag.hasKey(ItemTags.IS_STACK_IMMOVABLE.getKey())) {
            return tag.getBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey());
        }

        return false;
    }

    public static String getStackId(ItemStack stack) {
        return getStackId( getPluginTag(stack) );
    }

    public static String getStackId(NBTTagCompound pluginTag) {
        return pluginTag.getString(REGISTRY_NAME);
    }



    // These methods are in TagHelper because they relys on nbt in Vanilla Minecraft

    public static void setStackName(ItemStack stack, String name) {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);
        stack.setItemMeta(meta);
    }

    public static void addEnchantment(ItemStack stack, Enchantment enchant, int level) {
        ItemMeta meta = stack.getItemMeta();
        meta.addEnchant(enchant, level, true);
        stack.setItemMeta(meta);
    }

}
