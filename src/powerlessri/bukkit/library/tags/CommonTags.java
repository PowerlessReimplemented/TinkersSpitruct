package powerlessri.bukkit.library.tags;

import java.util.stream.Stream;

import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTBase;
import net.minecraft.server.v1_12_R1.NBTTagCompound;

public class CommonTags {
    
    private CommonTags() {
    }
    
    
    
    public enum ItemTags {
        
        ROOT_TAG("", CommonTags.ROOT_PLUGIN_TAG_FIXER),
        
        IS_STACK_IMMOVABLE("isStackImmovable", CommonTags.IMMOVABLE_STACK_FIXER),
        
        CLICK_EVENT_CATEGORY("clickEventCategory", CommonTags.CLICK_EVENT_BOND_FIXER),
        CLICK_EVENT_ID("clickEventId", CommonTags.CLICK_EVENT_BOND_FIXER),
        
        OWNER("", CommonTags.INTERNAL_OWNER_FIXER);
        
        String key;
        TaggedItemChanger fixer;
        
        private ItemTags(String key, TaggedItemChanger fixer) {
            this.key = key;
            this.fixer = fixer;
        }
        
        public boolean isInPath(ItemStack stack) {
            NBTTagCompound tag = TagHelper.getStackTag(stack);
            
            if(tag != null) {
                NBTTagCompound working = tag;
                
                // Follow the path to the actual workspace tag
                for(int i = 0; i < rootPath.length; i++) {
                    // We don't want mess around with a wrong path
                    if(working == null) {
                        return false;
                    }
                    
                    working = working.getCompound(rootPath[i]);
                }
                
                NBTBase result = working.get(this.key);
                
                if(result == null) {
                    return false;
                }
                
                return !result.isEmpty();
            }
            
            return false;
        }
        
        public boolean is(ItemStack stack) {
            NBTTagCompound tag = TagHelper.getStackTag(stack);
            
            if(tag != null) {
                NBTTagCompound mainTag = tag.getCompound(rootPath[0]);
                
                if(mainTag != null) {
                    NBTBase result = mainTag.get(this.key);
                    
                    if(result != null) {
                        return !result.isEmpty();
                    }
                }
            }
            
            return false;
        }
        
        public ItemStack fix(ItemStack stack) {
            return this.fixer.fixItem(stack);
        }
        
        
        public String getKey() {
            return key;
        }
        
    }
    
    
    private static String[] rootPath;
    
    private static final TaggedItemChanger ROOT_PLUGIN_TAG_FIXER = TaggedItemChanger.fixerOf(null);
    private static final TaggedItemChanger INTERNAL_OWNER_FIXER = TaggedItemChanger.fixerOf(null);
    private static final TaggedItemChanger IMMOVABLE_STACK_FIXER = TaggedItemChanger.fixerOf(null);
    private static final TaggedItemChanger CLICK_EVENT_BOND_FIXER = TaggedItemChanger.fixerOf(null);
    
    public static void resetItemTagFixers(String... rootPath) {
        CommonTags.rootPath = rootPath;
        
        ROOT_PLUGIN_TAG_FIXER.addRule((tag) -> {
            Stream.of(rootPath).forEach((key) -> {
                tag.set(key, new NBTTagCompound());
            });
        });
        
        
        INTERNAL_OWNER_FIXER.addRule((tag) -> {
            tag.setString("owner", "");
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
