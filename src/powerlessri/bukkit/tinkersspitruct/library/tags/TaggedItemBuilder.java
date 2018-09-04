package powerlessri.bukkit.tinkersspitruct.library.tags;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_12_R1.NBTTagCompound;

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
    
    private List<Enchantment> enchantments;
    private List<Integer> levels;
    
    private String displayName;
    private List<String> lore;
    
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
    
    
    public void addEnchant(Enchantment enchantment, int level) {
        if(this.enchantments == null) {
            this.enchantments = new ArrayList<Enchantment>();
        }
        if(this.levels == null) {
            this.levels = new ArrayList<Integer>();
        }
        
        this.enchantments.add(enchantment);
        this.levels.add(level);
    }
    
    public void setDisplayName(String name) {
        this.displayName = name;
    }
    
    public void addLore(String lore) {
        if(this.lore == null) {
            this.lore = new ArrayList<String>();
        }
        
        this.lore.add(lore);
    }
    
    
    public ItemStack buildItem(Material material) {
        return this.buildItem(material, null);
    }
    
    public ItemStack buildItem(Material material, String name) {
        NBTTagCompound tag = (NBTTagCompound) this.rootTag.clone();
        ItemStack result = TagHelper.getStackWithTag(new ItemStack(material), tag);
        
        if(this.enchantments.size() != this.levels.size()) {
            return result;
        }
        
        ItemMeta meta = result.getItemMeta();
        
        if(this.enchantments != null) {
            for(int i = 0; i < this.enchantments.size(); i++) {
               meta.addEnchant(this.enchantments.get(i), this.levels.get(i), true);
            }
        }
        
        meta.setDisplayName(name == null ? this.displayName : name);
        
        if(this.lore != null) {
            meta.setLore(this.lore);
        }
        
        result.setItemMeta(meta);
        return result;
    }
    
}
