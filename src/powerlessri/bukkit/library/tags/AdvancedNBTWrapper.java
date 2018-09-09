package powerlessri.bukkit.library.tags;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_12_R1.NBTBase;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import powerlessri.bukkit.library.Reference;

/**
 * Advanced nbt wrapper that supports operations like ones {@link ItemMeta} support. <br /><br />
 * 
 * <b>Deprecated</b><br />
 *     Reason: The {@link ItemMeta} similar-ed operation does not function 100% accurately, and it's not DRY. <br />
 *     Use {@link ItemMetaWrapper} instead.
 */
// Unfortunately needed to repeat the code over at ItemMetaWrapper, it would be too messy
// to extend from here
@Deprecated
public class AdvancedNBTWrapper extends NBTWrapper {

    public static AdvancedNBTWrapper wrap(ItemStack stack) {
        return new AdvancedNBTWrapper(stack, TagHelper.getStackTag(stack));
    }



    protected ItemStack referenceStack;
    private int tagChangeCount;

    // An external storage to save the cost of iterating lore & copy element to a list every time
    private List<String> loreBackup;
    private Set<Enchantment> enchantmentsBackup;

    // Implementation Note:
    //     All keys used on this tag can just be a random name, just to make sure put/extract on the same element
    //     No actual meaning
    //     So no constant for them
    private NBTTagCompound nbtBaseConverter;



    /** This constructor assumes that {@code tag} is from the same item as {@code stack} */
    protected AdvancedNBTWrapper(ItemStack stack, NBTTagCompound tag) {
        super(tag);

        this.referenceStack = stack;
        this.tagChangeCount = 0;

        this.loreBackup = new ArrayList<>();
        this.enchantmentsBackup = new HashSet<>();

        this.nbtBaseConverter = new NBTTagCompound();
    }



    public NBTTagCompound getDisplayTag() {
        return this.rootTag.getCompound(TAG_DISPLAY);
    }

    public String getDisplayName() {
        return this.getDisplayTag().getString(TAG_DISPLAY_NAME);
    }

    // Note: call this method also updates the external reference
    public List<String> getLore() {
        NBTTagList lore = this.getDisplayTag().getList(TAG_DISPLAY_LORE, Reference.TYPE_TAG_LIST);

        if(this.loreBackup.size() != lore.size()) {
            int loreSize = lore.size();

            for(int i = 0; i < loreSize; i++) {
                this.loreBackup.set(i, lore.getString(i));
            }
        }

        return this.loreBackup;
    }

    public int getColor() {
        return this.getDisplayTag().getInt(TAG_DISPLAY_COLOR);
    }

    public boolean isEnchanted() {
        getEnchantments();
        return this.enchantmentsBackup.size() != 0;
    }

    public boolean hasEnchantment(Enchantment enchantment) {
        getEnchantments();
        return this.enchantmentsBackup.contains(enchantment);
    }

    // Note: call this method also updates the external reference
    public Set<Enchantment> getEnchantments() {
        NBTTagList enchantments = this.rootTag.getList(TAG_ENCHANTMENTS, Reference.TYPE_TAG_COMPOUND);
                
        if(this.enchantmentsBackup.size() != enchantments.size()) {
            int enchantmentsSize = enchantments.size();

            for(int i = 0; i < enchantmentsSize; i++) {
                NBTTagCompound enchantment = enchantments.get(i);
                Enchantment enchant = Enchantment.getById(enchantment.getShort(TAG_ENCHANTMENT_ID));
                //TODO store levels too
                int level = enchantment.getShort(TAG_ENCHANTMENT_LVL);
                
                this.enchantmentsBackup.add(enchant);
            }
        }

        return this.enchantmentsBackup;
    }

    public boolean isUnbreakable() {
        return this.getBoolean(TAG_UNBREAKABLE);
    }



    public void setDisplayName(String name) {
        this.getDisplayTag().setString(TAG_DISPLAY_NAME, name);
    }

    public void resetLore() {
        this.getDisplayTag().set(TAG_DISPLAY_LORE, new NBTTagList());
    }

    public void addLore(String line) {
        this.nbtBaseConverter.setString("string", line);
        NBTBase nbtLine = this.nbtBaseConverter.get("string");

        this.getDisplayTag().getList(TAG_DISPLAY_LORE, Reference.TYPE_TAG_STRNG).add(nbtLine);
    }

    public void removeLore(int index) {
        this.getDisplayTag().getList(TAG_DISPLAY_LORE, Reference.TYPE_TAG_STRNG).remove(index);
    }

    public void setColor(int color) {
        this.getDisplayTag().setInt(TAG_DISPLAY_COLOR, color);
    }
    
    public void addEnchantment(Enchantment enchantment, int level) {
        NBTTagList enchantments = this.rootTag.getList(TAG_ENCHANTMENTS, Reference.TYPE_TAG_COMPOUND);
        
        // Enchantment compound in form of {id:<enchantment id>,lvl:<level>}
        NBTTagCompound enchant = new NBTTagCompound();
        enchant.setShort(TAG_ENCHANTMENT_ID, (short) enchantment.getId());
        enchant.setShort(TAG_ENCHANTMENT_LVL, (short) level);
        
        enchantments.add(enchant);
    }

    public void setUnbreakable(boolean state) {
        this.setBoolean(TAG_UNBREAKABLE, state);
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


    // TODO finalize 'public' or 'protected'
    protected void updateInternalStack() {
        if(this.tagChangeCount > 6) {
            this.referenceStack = TagHelper.getStackWithTag(this.referenceStack, this.rootTag);
            this.tagChangeCount = 0;
        }
    }

    public void applyTraits(ItemStack applicator) {
        this.updateInternalStack();

        applicator.setItemMeta(referenceStack.getItemMeta());
    }

}
