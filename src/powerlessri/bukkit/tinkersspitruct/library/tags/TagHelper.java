package powerlessri.bukkit.tinkersspitruct.library.tags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.meta.When;

import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

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
    
    public static ItemStack getTaggedStack(ItemStack stack) {
        net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(stack);
        
        if(nmsStack.hasTag()) {
            return stack;
        } else {
            nmsStack.setTag(new NBTTagCompound());
            return CraftItemStack.asCraftMirror(nmsStack);
        }
    }
    
//    /** Returns the stack with the given tag. <b>Does not change the given stack by itself.</b> */
//    public static ItemStack getStackWithTag(ItemStack stack, NBTTagCompound tag) {
//        net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(stack);
//        nmsStack.setTag(tag);
//        return CraftItemStack.asCraftMirror(nmsStack);
//    }
//    
//    
//    
//    @Deprecated
//    public static NBTTagCompound getTagSafe(ItemStack stack, String key) {
//        return getTagSafe(CraftItemStack.asNMSCopy(stack), key);
//    }
//    
//    @Deprecated
//    public static NBTTagCompound getTagSafe(net.minecraft.server.v1_12_R1.ItemStack stack, String key) {
//        return getTagSafe(getStackTag(stack), key);
//    }
//    
//    /** Get a sub tag from the root tag. Will create an empty tag if the root tag doesn't have one. */
//    @Deprecated
//    public static NBTTagCompound getTagSafe(NBTTagCompound parent, String key) {
//        if(!parent.hasKey(key)) {
//            parent.set(key, new NBTTagCompound());
//        }
//        
//        return parent.getCompound(key);
//    }
    
}
