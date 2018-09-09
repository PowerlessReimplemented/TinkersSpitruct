package powerlessri.bukkit.library.tags.wrapper;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import powerlessri.bukkit.library.tags.TagHelper;

public class ItemMetaWrapper extends NBTWrapper {
    
    public static ItemMetaWrapper wrap() {
        return wrap(new ItemStack(Material.AIR));
    }
    
    /** Wraps the information stored inside the given {@code stack}, any operation done to the result ItemMetaWrapper will not affect the parameter {@code stack}. */
    public static ItemMetaWrapper wrap(ItemStack stack) {
        NBTTagCompound tag = TagHelper.getStackTag(stack);
        
        if(tag != null) {
            return new ItemMetaWrapper(new ItemStack(stack), tag);
        }
        
        return new ItemMetaWrapper(new ItemStack(stack), new NBTTagCompound());
    }

    protected ItemStack referenceStack;
    private int tagChangeCount;

    /** This constructor assumes that {@code tag} is from the given {@code stack}. */
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
    
    /* Trash Bukkit which doesn't return the reference */
    public void setProperMeta(ItemMeta meta) {
        this.referenceStack.setItemMeta(meta);
        // Extract new information (e.g. changed display name) to the external nbt storage
        this.rootTag = TagHelper.getStackTag(this.referenceStack);
    }



    @Override
    public void setTagCompound(String key, NBTTagCompound tag) {
        this.tagChangeCount++;
        super.setTagCompound(key, tag);
    }

    @Override
    public void setTagList(String key, NBTTagList list) {
        this.tagChangeCount++;
        super.setTagList(key, list);
    }

    @Override
    public void setByte(String key, byte val) {
        this.tagChangeCount++;
        super.setByte(key, val);
    }

    @Override
    public void setInt(String key, int val) {
        this.tagChangeCount++;
        super.setInt(key, val);
    }

    @Override
    public void setShort(String key, short val) {
        this.tagChangeCount++;
        super.setShort(key, val);
    }
    
    @Override
    public void setLong(String key, long val) {
        this.tagChangeCount++;
        super.setLong(key, val);
    }

    @Override
    public void setFloat(String key, float val) {
        this.tagChangeCount++;
        super.setFloat(key, val);
    }

    @Override
    public void setDouble(String key, double val) {
        this.tagChangeCount++;
        super.setDouble(key, val);
    }

    @Override
    public void setBoolean(String key, boolean val) {
        this.tagChangeCount++;
        super.setBoolean(key, val);
    }

    @Override
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

    @Override
    public void applyTraits(ItemStack applicator) {
        this.updateInternalStack();

        applicator.setItemMeta(referenceStack.getItemMeta());
    }

}
