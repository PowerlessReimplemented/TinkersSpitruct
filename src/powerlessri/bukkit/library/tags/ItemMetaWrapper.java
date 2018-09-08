package powerlessri.bukkit.library.tags;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_12_R1.NBTTagCompound;

public class ItemMetaWrapper extends NBTCompoundWrapper {
    
    public static ItemMetaWrapper wrap(ItemStack stack) {
        return new ItemMetaWrapper(stack.getItemMeta(), TagHelper.getStackTag(stack));
    }
    
    protected final ItemMeta stackMeta;
    
    protected ItemMetaWrapper(ItemMeta meta, NBTTagCompound tag) {
        super(tag);
        
        this.stackMeta = meta;
    }

}
