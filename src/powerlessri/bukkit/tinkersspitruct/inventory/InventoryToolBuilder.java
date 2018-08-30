package powerlessri.bukkit.tinkersspitruct.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;
import powerlessri.bukkit.tinkersspitruct.library.inventory.CompoundInventories;
import powerlessri.bukkit.tinkersspitruct.library.inventory.InventoryBuilder;
import powerlessri.bukkit.tinkersspitruct.library.tags.CommonTags.ItemTags;
import powerlessri.bukkit.tinkersspitruct.library.tags.TaggedItemBuilder;

// Somehow make everything static does not work
public class InventoryToolBuilder {
    
    private static final TaggedItemBuilder itemBuilderButton = TaggedItemBuilder.builderOf(null);
    private static final TaggedItemBuilder itemToolChoiceButton = TaggedItemBuilder.builderOf(null);
    
    static {
        // Plugin data
        
        itemBuilderButton.addTagCompound(TinkersSpitruct.PLUGIN_ID);
        itemToolChoiceButton.addTagCompound(TinkersSpitruct.PLUGIN_ID);
        
        itemBuilderButton.cd(TinkersSpitruct.PLUGIN_ID);
        itemToolChoiceButton.cd(TinkersSpitruct.PLUGIN_ID);
        
        itemBuilderButton.addDefaultBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey(), true);
        itemToolChoiceButton.addDefaultBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey(), true);
        
        itemBuilderButton.addDefaultString(ItemTags.CLICK_EVENT_CATEGORY.getKey(), "inventoryEntry_toolBuilder");
        itemToolChoiceButton.addDefaultString(ItemTags.CLICK_EVENT_CATEGORY.getKey(), "inventoryEntry_toolBuilder");
        
        itemBuilderButton.addDefaultInt(ItemTags.CLICK_EVENT_ID.getKey(), 0);
        itemToolChoiceButton.addDefaultInt(ItemTags.CLICK_EVENT_ID.getKey(), 1);
    }
    
    
    private final String INVENTORY_TITLE;
    
    private final InventoryBuilder builder;
    private final InventoryBuilder toolChoice;
    
    public InventoryToolBuilder(String... listAviliableTools) {
        this.INVENTORY_TITLE = TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.title");
        
        
        ItemStack buttonBuilder = itemBuilderButton.buildItem(Material.FURNACE);
        ItemStack buttonToolChoice = itemToolChoiceButton.buildItem(Material.DIAMOND_PICKAXE);
        
        ItemMeta metaBuilder = buttonBuilder.getItemMeta();
        ItemMeta metaToolChoice = buttonToolChoice.getItemMeta();
        
        metaBuilder.setDisplayName( TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.gotoBuilder") );
        metaToolChoice.setDisplayName( TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.gotoToolChoice") );
        metaBuilder.addEnchant(TinkersSpitruct.plugin.glow, 1, true);
        metaToolChoice.addEnchant(TinkersSpitruct.plugin.glow, 1, true);
        
        buttonBuilder.setItemMeta(metaBuilder);
        buttonToolChoice.setItemMeta(metaToolChoice);
        
        
        this.builder = InventoryBuilder.createBuilder(6, this.INVENTORY_TITLE);
        this.toolChoice = InventoryBuilder.createBuilder(6, this.INVENTORY_TITLE);
        
        this.builder.addImmovableSlot(0, buttonToolChoice);
        this.toolChoice.addImmovableSlot(0, buttonToolChoice);
    }
    
    public CompoundInventories makeInventory() {
        CompoundInventories result = new CompoundInventories();
        
        result.addInventory("builder", this.builder.makeInventory());
        result.addInventory("toolChoice", this.toolChoice.makeInventory());
        
        return result;
    }
    
}
