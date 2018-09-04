package powerlessri.bukkit.tinkersspitruct.library.tags;

import javax.annotation.Nullable;

import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_12_R1.NBTTagCompound;

public class TagHelper {
    
    private TagHelper() {
    }
    
    public static boolean hasTag(ItemStack stack) {
        return CraftItemStack.asNMSCopy(stack).hasTag();
    }
    
    @Nullable
    public static NBTTagCompound getStackTag(ItemStack stack) {
        return CraftItemStack.asNMSCopy(stack).getTag();
    }
    
    /** Returns {@code null} when the stack doesn't have a tag, but will create one for the stack */
    @Nullable
    public static NBTTagCompound getStackTag(net.minecraft.server.v1_12_R1.ItemStack stack) {
        if(!stack.hasTag()) {
            stack.setTag(new NBTTagCompound());
            return null;
        }
        
        return stack.getTag();
    }
    
    /** Returns the stack with the given tag. <b>Does not change the given stack by itself.</b> */
    public static ItemStack getStackWithTag(ItemStack stack, NBTTagCompound tag) {
        net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(stack);
        nmsStack.setTag(tag);
        return CraftItemStack.asCraftMirror(nmsStack);
    }
    
    /** If the given stack does not have a tag, create one for it & return */
    public static ItemStack getTaggedStack(ItemStack stack) {
        net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(stack);
        
        if(nmsStack.hasTag()) {
            return stack;
        } else {
            nmsStack.setTag(new NBTTagCompound());
            return CraftItemStack.asCraftMirror(nmsStack);
        }
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
