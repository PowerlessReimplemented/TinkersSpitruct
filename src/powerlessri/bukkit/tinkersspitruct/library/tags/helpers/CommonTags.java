package powerlessri.bukkit.tinkersspitruct.library.tags.helpers;

import org.bukkit.inventory.ItemStack;

import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;
import powerlessri.bukkit.tinkersspitruct.library.tags.TaggedItemChanger;

public class CommonTags {
    
    private CommonTags() {
    }
    
    
    
    public enum ItemTags {
        
        IS_STACK_IMMOVABLE("isStackImmovable", CommonTags.IMMOVABLE_STACK_FIXER),
        CLICK_EVENT_CATEGORY("clickEventCategory", CommonTags.CLICK_EVENT_BOND_FIXER),
        CLICK_EVENT_ID("clickEventId", CommonTags.CLICK_EVENT_BOND_FIXER);
        
        String key;
        TaggedItemChanger fixer;
        
        private ItemTags(String key, TaggedItemChanger fixer, TaggedItemChanger.TagChangingRule... rules) {
            this.key = key;
            this.fixer = fixer;
        }
        
        public ItemStack fixStack(ItemStack stack) {
            return this.fixer.fixItem(stack);
        }
        
        
        public String getKey() {
            return key;
        }
        
    }
    
    
    
    public static final TaggedItemChanger IMMOVABLE_STACK_FIXER = TaggedItemChanger.fixerOf(null);
    public static final TaggedItemChanger CLICK_EVENT_BOND_FIXER = TaggedItemChanger.fixerOf(null);
    
    public static void resetItemTagFixers(String... rootPath) {
        IMMOVABLE_STACK_FIXER.addRule((tag) -> {
            tag.setBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey(), true);
        }, rootPath);
        
        CLICK_EVENT_BOND_FIXER.addRule((tag) -> {
            tag.setString(ItemTags.CLICK_EVENT_CATEGORY.getKey(), "default");
            tag.setInt(ItemTags.CLICK_EVENT_ID.getKey(), -1);
        }, rootPath);
    }
    
}
