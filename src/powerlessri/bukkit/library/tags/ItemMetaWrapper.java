package powerlessri.bukkit.library.tags;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;

public class ItemMetaWrapper extends NBTWrapper {

    protected ItemStack referenceStack;
    private int tagChangeCount;

    public ItemMetaWrapper(ItemStack stack, NBTTagCompound tag) {
        super(tag);

        this.referenceStack = stack;
    }


    // Too lazy to redirect all method from ItemMeta, just get the reference and do them over here.
    
    public ItemMeta getProperMeta() {
        // Copy work done on the actual nbt to item
        this.updateInternalStack(true);
        // And then get the meta so the meta would have the same nbt
        return this.referenceStack.getItemMeta();
    }
    
    // Trash Bukkit which doesn't return the reference
    public void setProperMeta(ItemMeta meta) {
        this.referenceStack.setItemMeta(meta);
        // Extract new information (e.g. changed display name) to the external nbt storage
        this.rootTag = TagHelper.getStackTag(this.referenceStack);
    }



    public void setTagCompound(String key, NBTTagCompound tag) {
        this.tagChangeCount++;
        super.setTagCompound(key, tag);
    }

    public void setTagList(String key, NBTTagList list) {
        this.tagChangeCount++;
        super.setTagList(key, list);
    }

    public void setByte(String key, byte val) {
        this.tagChangeCount++;
        super.setByte(key, val);
    }

    public void setInt(String key, int val) {
        this.tagChangeCount++;
        super.setInt(key, val);
    }

    public void setShort(String key, short val) {
        this.tagChangeCount++;
        super.setShort(key, val);
    }

    public void setLong(String key, long val) {
        this.tagChangeCount++;
        super.setLong(key, val);
    }

    public void setFloat(String key, float val) {
        this.tagChangeCount++;
        super.setFloat(key, val);
    }

    public void setDouble(String key, double val) {
        this.tagChangeCount++;
        super.setDouble(key, val);
    }

    public void setBoolean(String key, boolean val) {
        this.tagChangeCount++;
        super.setBoolean(key, val);
    }

    public void setString(String key, String val) {
        this.tagChangeCount++;
        super.setString(key, val);
    }



    protected void updateInternalStack() {
        this.updateInternalStack(false);
    }

    protected void updateInternalStack(boolean isForced) {
        if(this.tagChangeCount > 6 || isForced) {
            this.referenceStack = TagHelper.getStackWithTag(this.referenceStack, this.rootTag);
            this.tagChangeCount = 0;
        }
    }

    public void applyTraits(ItemStack applicator) {
        this.updateInternalStack();

        applicator.setItemMeta(referenceStack.getItemMeta());
    }

}
