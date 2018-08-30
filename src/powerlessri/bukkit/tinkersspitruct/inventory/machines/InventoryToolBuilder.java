package powerlessri.bukkit.tinkersspitruct.inventory.machines;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;
import powerlessri.bukkit.tinkersspitruct.inventory.IMachineInventoryBuilder;
import powerlessri.bukkit.tinkersspitruct.library.inventory.InventoryBuilder;
import powerlessri.bukkit.tinkersspitruct.library.inventory.InventorySequence;
import powerlessri.bukkit.tinkersspitruct.library.inventory.InventorySequenceBuilder;
import powerlessri.bukkit.tinkersspitruct.library.tags.CommonTags.ItemTags;
import powerlessri.bukkit.tinkersspitruct.library.tags.helpers.TagHelper;

// Somehow make everything static does not work
public class InventoryToolBuilder implements IMachineInventoryBuilder {
    
    public static final String IS_TOOL_BUILDER = "isToolBuilder";
    
    private final String TOOL_BUILDER_CLICK_EVENT;
    private final byte BUILDER_PAGE;
    private final byte TOOL_CHOICE_PAGE;

    private final String INVENTORY_TITLE;

    private final InventoryBuilder builder;
    private final InventoryBuilder toolChoice;
    
    private final InventorySequenceBuilder sequence;

    public InventoryToolBuilder(String... listAviliableTools) {
        
        this.TOOL_BUILDER_CLICK_EVENT = "toolBuilderClickEvent";
        this.BUILDER_PAGE = (byte) 0;
        this.TOOL_CHOICE_PAGE = (byte) 1;
        
        this.INVENTORY_TITLE = TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.title");

        NBTTagCompound rootTag = new NBTTagCompound();
        rootTag.set(TinkersSpitruct.PLUGIN_ID, new NBTTagCompound());

        NBTTagCompound pluginTag = rootTag.getCompound(TinkersSpitruct.PLUGIN_ID);
        pluginTag.setBoolean(IS_TOOL_BUILDER, true);
        pluginTag.setBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey(), true);

        NBTTagCompound buttonBuilderTag = (NBTTagCompound) pluginTag.clone();
        NBTTagCompound buttonToolChoiceTag = (NBTTagCompound) pluginTag.clone();

        buttonBuilderTag.setByte(this.TOOL_BUILDER_CLICK_EVENT, this.BUILDER_PAGE);
        buttonToolChoiceTag.setByte(this.TOOL_BUILDER_CLICK_EVENT, this.TOOL_CHOICE_PAGE);

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

        this.builder = new InventoryBuilder("toolBuilder.builder", 6, this.INVENTORY_TITLE);
        this.toolChoice = new InventoryBuilder("toolBuilder.toolChoice", 6, this.INVENTORY_TITLE);

        this.builder.addImmovableSlot(0, buttonToolChoice);
        this.toolChoice.addImmovableSlot(0, buttonToolChoice);

        this.builder.blockEmptySlots();
        this.toolChoice.blockEmptySlots();

        this.sequence = new InventorySequenceBuilder("tile.toolBuilder", this.builder, this.toolChoice);
    }

    @Override
    public InventorySequence makeInventory() {
        return sequence.makeInventory();
    }

    @Override
    public void handleStackClicked(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        
    }

}
