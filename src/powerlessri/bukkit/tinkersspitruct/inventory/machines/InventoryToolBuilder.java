package powerlessri.bukkit.tinkersspitruct.inventory.machines;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;
import powerlessri.bukkit.tinkersspitruct.inventory.IMachineInventoryBuilder;
import powerlessri.bukkit.tinkersspitruct.library.inventory.CompoundInventories;
import powerlessri.bukkit.tinkersspitruct.library.inventory.InventoryBuilder;
import powerlessri.bukkit.tinkersspitruct.library.tags.CommonTags.ItemTags;
import powerlessri.bukkit.tinkersspitruct.library.tags.TaggedItemBuilder;

// Somehow make everything static does not work
public class InventoryToolBuilder implements IMachineInventoryBuilder {

    private final TaggedItemBuilder itemBuilderButton = TaggedItemBuilder.builderOf(null);
    private final TaggedItemBuilder itemToolChoiceButton = TaggedItemBuilder.builderOf(null);

    private final String INVENTORY_TITLE;

    private final InventoryBuilder builder;
    private final InventoryBuilder toolChoice;

    public InventoryToolBuilder(String... listAviliableTools) {
        this.INVENTORY_TITLE = TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.title");


        ItemStack buttonBuilder = itemBuilderButton.buildItem(Material.FURNACE);
        ItemStack buttonToolChoice = itemToolChoiceButton.buildItem(Material.DIAMOND_PICKAXE);

        ItemMeta metaBuilder = buttonBuilder.getItemMeta();
        ItemMeta metaToolChoice = buttonToolChoice.getItemMeta();

        metaBuilder.setDisplayName( TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.button.builder") );
        metaToolChoice.setDisplayName( TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.button.toolChoice") );
        metaBuilder.addEnchant(TinkersSpitruct.plugin.glow, 1, true);
        metaToolChoice.addEnchant(TinkersSpitruct.plugin.glow, 1, true);

        buttonBuilder.setItemMeta(metaBuilder);
        buttonToolChoice.setItemMeta(metaToolChoice);


        this.builder = InventoryBuilder.createBuilder(6, this.INVENTORY_TITLE);
        this.toolChoice = InventoryBuilder.createBuilder(6, this.INVENTORY_TITLE);

        this.builder.addImmovableSlot(0, buttonToolChoice);
        this.toolChoice.addImmovableSlot(0, buttonToolChoice);

        this.builder.blockEmptySlots();
        this.toolChoice.blockEmptySlots();

        // ================================ //

        Runnable builderButton = () -> {

        };
        Runnable toolChoiceButton = () -> {

        };

        int builderButtonId = TinkersSpitruct.plugin.addEventCall("inventoryEntry_toolBuilder", builderButton);
        int toolChoiceButtonId = TinkersSpitruct.plugin.addEventCall("inventoryEntry_toolBuilder", toolChoiceButton);

        // Plugin data
        itemBuilderButton.addTagCompound(TinkersSpitruct.PLUGIN_ID);
        itemToolChoiceButton.addTagCompound(TinkersSpitruct.PLUGIN_ID);

        itemBuilderButton.cd(TinkersSpitruct.PLUGIN_ID);
        itemToolChoiceButton.cd(TinkersSpitruct.PLUGIN_ID);

        itemBuilderButton.addDefaultBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey(), true);
        itemToolChoiceButton.addDefaultBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey(), true);

        itemBuilderButton.addDefaultString(ItemTags.CLICK_EVENT_CATEGORY.getKey(), "inventoryEntry_toolBuilder");
        itemToolChoiceButton.addDefaultString(ItemTags.CLICK_EVENT_CATEGORY.getKey(), "inventoryEntry_toolBuilder");

        itemBuilderButton.addDefaultInt(ItemTags.CLICK_EVENT_ID.getKey(), builderButtonId);
        itemToolChoiceButton.addDefaultInt(ItemTags.CLICK_EVENT_ID.getKey(), toolChoiceButtonId);
    }

    @Override
    public CompoundInventories makeInventory() {
        CompoundInventories result = new CompoundInventories();

        result.addInventory("builder", this.builder.makeInventory());
        result.addInventory("toolChoice", this.toolChoice.makeInventory());

        return result;
    }

}
