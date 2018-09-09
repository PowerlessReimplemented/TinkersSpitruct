package powerlessri.bukkit.library.tags;

import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;

public class ItemMetaWrapper extends AdvancedNBTWrapper {

    protected ItemMetaWrapper(ItemStack stack, NBTTagCompound tag) {
        super(stack, tag);
    }

}
