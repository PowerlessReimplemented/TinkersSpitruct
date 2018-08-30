package powerlessri.bukkit.tinkersspitruct.inventory.machines;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;
import powerlessri.bukkit.tinkersspitruct.inventory.IMachineInventoryBuilder;
import powerlessri.bukkit.tinkersspitruct.library.inventory.CompoundInventories;
import powerlessri.bukkit.tinkersspitruct.library.inventory.InventoryBuilder;
import powerlessri.bukkit.tinkersspitruct.library.tags.CommonTags.ItemTags;
import powerlessri.bukkit.tinkersspitruct.library.tags.helpers.TagHelper;

// Somehow make everything static does not work
public class InventoryToolBuilder implements IMachineInventoryBuilder {

    private final String INVENTORY_TITLE;

    private final InventoryBuilder builder;
    private final InventoryBuilder toolChoice;

    public InventoryToolBuilder(String... listAviliableTools) {

        Runnable builderButton = () -> {

        };
        Runnable toolChoiceButton = () -> {

        };

        int buttonBuilderId = TinkersSpitruct.plugin.addEventCall("inventoryEntry_toolBuilder", builderButton);
        int buttonToolChoiceId = TinkersSpitruct.plugin.addEventCall("inventoryEntry_toolBuilder", toolChoiceButton);

        // ================================ //

        this.INVENTORY_TITLE = TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.title");

        NBTTagCompound rootTag = new NBTTagCompound();
        rootTag.set(TinkersSpitruct.PLUGIN_ID, new NBTTagCompound());

        NBTTagCompound pluginTag = rootTag.getCompound(TinkersSpitruct.PLUGIN_ID);
        pluginTag.setBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey(), true);
        pluginTag.setString(ItemTags.CLICK_EVENT_CATEGORY.getKey(), "inventoryEntry_toolBuilder");

        NBTTagCompound buttonBuilderTag = (NBTTagCompound) pluginTag.clone();
        NBTTagCompound buttonToolChoiceTag = (NBTTagCompound) pluginTag.clone();

        buttonBuilderTag.setInt(ItemTags.CLICK_EVENT_ID.getKey(), buttonBuilderId);
        buttonToolChoiceTag.setInt(ItemTags.CLICK_EVENT_ID.getKey(), buttonToolChoiceId);

        ItemStack buttonBuilder = TagHelper.getStackWithTag(new ItemStack(Material.FURNACE), buttonBuilderTag);
        ItemStack buttonToolChoice = TagHelper.getStackWithTag(new ItemStack(Material.DIAMOND_PICKAXE), buttonToolChoiceTag);

        ItemMeta metaBuilder = buttonBuilder.getItemMeta();
        ItemMeta metaToolChoice = buttonToolChoice.getItemMeta();

        // ================================ //

        metaBuilder.setDisplayName( TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.button.builder") );
        metaToolChoice.setDisplayName( TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.button.toolChoice") );
        metaBuilder.addEnchant(TinkersSpitruct.plugin.glow, 1, true);
        metaToolChoice.addEnchant(TinkersSpitruct.plugin.glow, 1, true);

        buttonBuilder.setItemMeta(metaBuilder);
        buttonToolChoice.setItemMeta(metaToolChoice);

        // ================================ //

        this.builder = InventoryBuilder.createBuilder(6, this.INVENTORY_TITLE);
        this.toolChoice = InventoryBuilder.createBuilder(6, this.INVENTORY_TITLE);

        this.builder.addImmovableSlot(0, buttonToolChoice);
        this.toolChoice.addImmovableSlot(0, buttonToolChoice);

        this.builder.blockEmptySlots();
        this.toolChoice.blockEmptySlots();


    }

    @Override
    public CompoundInventories makeInventory() {
        CompoundInventories result = new CompoundInventories();

        result.addInventory("builder", this.builder.makeInventory());
        result.addInventory("toolChoice", this.toolChoice.makeInventory());

        return result;
    }

}
