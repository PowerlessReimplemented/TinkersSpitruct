package powerlessri.bukkit.tinkersspitruct.library.tags;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;

public class TaggedItemChanger {
    
    public static class TagChangingRule {
        
        private final String[] path;
        private final Consumer<NBTTagCompound> modifyFunction;
        
        public TagChangingRule(Consumer<NBTTagCompound> modifyFunction, String... path) {
            this.path = path;
            this.modifyFunction = modifyFunction;
        }
        
        public void modifyTag(NBTTagCompound tag) {
            NBTTagCompound workingPointer = tag;
            
            for(int i = 0; i < path.length; i++) {
                workingPointer = workingPointer.getCompound(path[i]);
            }
            
            this.modifyFunction.accept(workingPointer);
        }
        
    }
    
    public static TaggedItemChanger fixerOf(ItemStack parentStack) {
        return new TaggedItemChanger();
    }
    
    
    private final List<TagChangingRule> rules;
    
    public TaggedItemChanger() {
        this.rules = new ArrayList<TagChangingRule>();
    }
    
    public void addRule(Consumer<NBTTagCompound> modifyFunction, String... path) {
        this.addRule(new TagChangingRule(modifyFunction, path));
    }
    
    public void addRule(TagChangingRule rule) {
        this.rules.add(rule);
    }
    
    
    public ItemStack fixItem(ItemStack stack) {
        NBTTagCompound tag = TagHelper.getStackTag(stack);
        
        if(tag == null) {
            return stack;
        }
        
        this.rules.forEach((rule) -> {
            rule.modifyTag(tag);
        });
        
        return TagHelper.getStackWithTag(stack, tag);
    }
    
}
