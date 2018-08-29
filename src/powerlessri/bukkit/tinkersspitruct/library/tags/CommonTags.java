package powerlessri.bukkit.tinkersspitruct.library.tags;

import java.util.stream.Stream;

import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;

public class CommonTags {
    
    private CommonTags() {
    }
    
    
    
    public enum ItemTags {
        
        ROOT_TAG("", CommonTags.ROOT_PLUGIN_TAG_FIXER),
        
        IS_STACK_IMMOVABLE("isStackImmovable", CommonTags.IMMOVABLE_STACK_FIXER),
        CLICK_EVENT_CATEGORY("clickEventCategory", CommonTags.CLICK_EVENT_BOND_FIXER),
        CLICK_EVENT_ID("clickEventId", CommonTags.CLICK_EVENT_BOND_FIXER);
        
        String key;
        TaggedItemChanger fixer;
        
        private ItemTags(String key, TaggedItemChanger fixer) {
            this.key = key;
            this.fixer = fixer;
        }
        
        public ItemStack fixItem(ItemStack stack) {
            return this.fixer.fixItem(stack);
        }
        
        
        public String getKey() {
            return key;
        }
        
    }
    
    
    
    public static final TaggedItemChanger ROOT_PLUGIN_TAG_FIXER = TaggedItemChanger.fixerOf(null);
    
    public static final TaggedItemChanger IMMOVABLE_STACK_FIXER = TaggedItemChanger.fixerOf(null);
    public static final TaggedItemChanger CLICK_EVENT_BOND_FIXER = TaggedItemChanger.fixerOf(null);
    
    public static void resetItemTagFixers(String... rootPath) {
        ROOT_PLUGIN_TAG_FIXER.addRule((tag) -> {
            Stream.of(rootPath).forEach((key) -> {
                tag.set(key, new NBTTagCompound());
            });
        });
        
        
        IMMOVABLE_STACK_FIXER.addRule((tag) -> {
            tag.setBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey(), true);
        }, rootPath);
        
        CLICK_EVENT_BOND_FIXER.addRule((tag) -> {
            tag.setString(ItemTags.CLICK_EVENT_CATEGORY.getKey(), "default");
            tag.setInt(ItemTags.CLICK_EVENT_ID.getKey(), -1);
        }, rootPath);
    }
    
}
