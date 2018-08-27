package powerlessri.bukkit.tinkersspitruct.library.helpers.tags;

import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;

public class TagHelper {
    
    private TagHelper() {
    }
    
    public static boolean hasStackTag(ItemStack stack) {
        return CraftItemStack.asNMSCopy(stack).hasTag();
    }
    
    /** Get an {@code ItemStack}'s NBT tag. Will create an empty tag if the stack doesn't have one. */
    public static NBTTagCompound getStackTag(ItemStack stack) {
        return getStackTag(CraftItemStack.asNMSCopy(stack));
    }
    
    public static NBTTagCompound getStackTag(net.minecraft.server.v1_12_R1.ItemStack stack) {
        if(!stack.hasTag()) {
            stack.setTag(new NBTTagCompound());
        }
        
        return stack.getTag();
    }
    
    public static ItemStack setStackTag(ItemStack stack, NBTTagCompound tag) {
        net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(stack);
        nmsStack.setTag(tag);
        return CraftItemStack.asCraftMirror(nmsStack);
    }
    
    
    public static NBTTagCompound getTagSafe(ItemStack stack, String key) {
        return getTagSafe(CraftItemStack.asNMSCopy(stack), key);
    }
    
    public static NBTTagCompound getTagSafe(net.minecraft.server.v1_12_R1.ItemStack stack, String key) {
        return getTagSafe(getStackTag(stack), key);
    }
    
    /** Get a sub tag from the root tag. Will create an empty tag if the root tag doesn't have one. */
    public static NBTTagCompound getTagSafe(NBTTagCompound parent, String key) {
        if(!parent.hasKey(key)) {
            parent.set(key, new NBTTagCompound());
        }
        
        return parent.getCompound(key);
    }
    
}
