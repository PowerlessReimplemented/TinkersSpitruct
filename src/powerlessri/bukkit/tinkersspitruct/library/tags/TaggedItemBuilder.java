package powerlessri.bukkit.tinkersspitruct.library.tags;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.tinkersspitruct.library.tags.helpers.TagHelper;

public class TaggedItemBuilder {
    
    public static final String ROOT_TAG = "#MOVE: root";
    
    public static TaggedItemBuilder builderOf(ItemStack patternStack) {
        TaggedItemBuilder builder = new TaggedItemBuilder();
        
        if(patternStack != null && TagHelper.hasTag(patternStack)) {
            builder.rootTag = TagHelper.getStackTag(patternStack);
        }
        
        return builder;
    }
    
    
    
    /** Root tag for pattern usage */
    private NBTTagCompound rootTag;
    /** Reference to the tag (inside {@code rootTag}) */
    private NBTTagCompound workingPointer;
    
    public TaggedItemBuilder() {
        this.rootTag = new NBTTagCompound();
        this.workingPointer = rootTag;
    }
    
    
    
    public void cd(String key) {
        if(key.equals(ROOT_TAG)) {
            this.workingPointer = this.rootTag;
        }
        
        this.workingPointer = workingPointer.getCompound(key);
    }
    
    
    public void addTagCompound(String key) {
        this.addTagCompound(key, new NBTTagCompound());
    }
    
    public void addTagCompound(String key, NBTTagCompound base) {
        this.workingPointer.set(key, base);
    }
    
    
    public void addDefaultByte(String key, byte val) {
        this.workingPointer.setByte(key, val);
    }
    
    public void addDefaultInt(String key, int val) {
        this.workingPointer.setInt(key, val);
    }
    
    public void addDefaultShort(String key, short val) {
        this.workingPointer.setShort(key, val);
    }
    
    public void addDefaultLong(String key, long val) {
        this.workingPointer.setLong(key, val);
    }
    
    public void addDefaultFloat(String key, float val) {
        this.workingPointer.setFloat(key, val);
    }
    
    public void addDefaultDouble(String key, double val) {
        this.workingPointer.setDouble(key, val);
    }
    
    public void addDefaultBoolean(String key, boolean val) {
        this.workingPointer.setBoolean(key, val);
    }
    
    public void addDefaultString(String key, String val) {
        this.workingPointer.setString(key, val);
    }
    
    
    
    public ItemStack buildItem(Material item) {
        NBTTagCompound stackTag = (NBTTagCompound) this.rootTag.clone();
        net.minecraft.server.v1_12_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(new ItemStack(item));
        nmsStack.setTag(stackTag);
        
        return CraftItemStack.asCraftMirror(nmsStack);
    }
    
}
