package powerlessri.bukkit.library.tags;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import powerlessri.bukkit.library.IWrapper;

public class NBTCompoundWrapper implements IWrapper<NBTCompoundWrapper, NBTTagCompound> {
    
    public static final String ROOT_TAG = "#MOVE: root";
    
    /** Root tag for pattern usage */
    protected final NBTTagCompound rootTag;
    /** Reference to the tag (inside {@code rootTag}) */
    protected NBTTagCompound workingPointer;
    
    public NBTCompoundWrapper(NBTTagCompound tag) {
        this.rootTag = tag;
        this.workingPointer = tag;
    }
    
    
    
    public void cd(String key) {
        if(key.equals(ROOT_TAG)) {
            this.workingPointer = this.rootTag;
        }
        
        this.workingPointer = workingPointer.getCompound(key);
    }
    
    
    
    public void addTagCompound(String key) {
        this.setTagCompound(key, new NBTTagCompound());
    }
    
    public void setTagCompound(String key, NBTTagCompound base) {
        this.workingPointer.set(key, base);
    }
    
    public void addTagList(String key) {
        this.setTagList(key, new NBTTagList());
    }
    
    public void setTagList(String key, NBTTagList list) {
        this.workingPointer.set(key, list);
    }
    
    
    
    public byte getByte(String key) {
        return this.workingPointer.getByte(key);
    }
    
    public int getInt(String key) {
        return this.workingPointer.getInt(key);
    }
    
    public short getShort(String key) {
        return this.workingPointer.getShort(key);
    }
    
    public long getLong(String key) {
        return this.workingPointer.getLong(key);
    }
    
    public float getFloat(String key) {
        return this.workingPointer.getFloat(key);
    }
    
    public double getDouble(String key) {
        return this.workingPointer.getDouble(key);
    }
    
    public boolean getBoolean(String key) {
        return this.workingPointer.getBoolean(key);
    }
    
    public String getString(String key) {
        return this.workingPointer.getString(key);
    }
    
    
    
    public void setByte(String key, byte val) {
        this.workingPointer.setByte(key, val);
    }
    
    public void setInt(String key, int val) {
        this.workingPointer.setInt(key, val);
    }
    
    public void setShort(String key, short val) {
        this.workingPointer.setShort(key, val);
    }
    
    public void setLong(String key, long val) {
        this.workingPointer.setLong(key, val);
    }
    
    public void setFloat(String key, float val) {
        this.workingPointer.setFloat(key, val);
    }
    
    public void setDouble(String key, double val) {
        this.workingPointer.setDouble(key, val);
    }
    
    public void setBoolean(String key, boolean val) {
        this.workingPointer.setBoolean(key, val);
    }
    
    public void setString(String key, String val) {
        this.workingPointer.setString(key, val);
    }



    @Override
    public NBTTagCompound unwrap() {
        return this.rootTag;
    }
    
}
