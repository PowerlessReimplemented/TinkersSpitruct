package powerlessri.bukkit.tinkersspitruct.inventory.machines;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_12_R1.BlockPosition;
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

    private final String CLICK_EVENT;
    
    private final String BUILDER;
    private final String TOOL_CHOICE;
    private final byte BUILDER_PAGE;
    private final byte TOOL_CHOICE_PAGE;

    private final String INVENTORY_TITLE;

    private final InventorySequenceBuilder sequence;

    private final Map<UUID, InventorySequence> playerMap;
    private final Set<BlockPosition> activePoses;

    public InventoryToolBuilder(String... listAviliableTools) {

        this.CLICK_EVENT = "toolBuilderClickEvent";
        this.BUILDER = "toolBuilder.builder";
        this.TOOL_CHOICE = "toolBuilder.toolChoice";
        this.BUILDER_PAGE = (byte) 0;
        this.TOOL_CHOICE_PAGE = (byte) 1;

        this.INVENTORY_TITLE = TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.title");

        // ================================ //

        NBTTagCompound rootTag = new NBTTagCompound();
        rootTag.set(TinkersSpitruct.PLUGIN_ID, new NBTTagCompound());

        NBTTagCompound pluginTag = rootTag.getCompound(TinkersSpitruct.PLUGIN_ID);
        pluginTag.setBoolean(IS_TOOL_BUILDER, true);
        pluginTag.setBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey(), true);


        NBTTagCompound buttonBuilderTag = (NBTTagCompound) rootTag.clone();
        NBTTagCompound buttonToolChoiceTag = (NBTTagCompound) rootTag.clone();

        buttonBuilderTag.getCompound(TinkersSpitruct.PLUGIN_ID).setByte(this.CLICK_EVENT, this.BUILDER_PAGE);
        buttonToolChoiceTag.getCompound(TinkersSpitruct.PLUGIN_ID).setByte(this.CLICK_EVENT, this.TOOL_CHOICE_PAGE);

        ItemStack buttonBuilder = TagHelper.getStackWithTag(new ItemStack(Material.FURNACE), buttonBuilderTag);
        ItemStack buttonToolChoice = TagHelper.getStackWithTag(new ItemStack(Material.DIAMOND_PICKAXE), buttonToolChoiceTag);

        // ================================ //

        ItemMeta metaBuilder = buttonBuilder.getItemMeta();
        ItemMeta metaToolChoice = buttonToolChoice.getItemMeta();

        metaBuilder.setDisplayName( TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.button.builder") );
        metaToolChoice.setDisplayName( TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.button.toolChoice") );
        metaBuilder.addEnchant(TinkersSpitruct.plugin.glow, 1, true);
        metaToolChoice.addEnchant(TinkersSpitruct.plugin.glow, 1, true);

        buttonBuilder.setItemMeta(metaBuilder);
        buttonToolChoice.setItemMeta(metaToolChoice);

        // ================================ //

        InventoryBuilder builder = new InventoryBuilder(this.BUILDER, 6, this.INVENTORY_TITLE);
        InventoryBuilder toolChoice = new InventoryBuilder(this.TOOL_CHOICE, 6, this.INVENTORY_TITLE);

        builder.addImmovableSlot(0, buttonToolChoice);
        toolChoice.addImmovableSlot(0, buttonBuilder);

        builder.blockEmptySlots();
        toolChoice.blockEmptySlots();

        this.sequence = new InventorySequenceBuilder("tile.toolBuilder", builder, toolChoice);

        // ================================ //

        this.playerMap = new HashMap<UUID, InventorySequence>();
        this.activePoses = new HashSet<BlockPosition>();
    }

    @Override
    public InventorySequence makeInventory() {
        return sequence.makeInventory();
    }

    // ======== Handles start ======== //

    @Override
    public void handleStackClicked(InventoryClickEvent event, ItemStack stack, NBTTagCompound tag) {
        if(tag != null && tag.getBoolean(IS_TOOL_BUILDER)) {
            TinkersSpitruct.plugin.getLogger().info("entered");
            
            Player player = (Player) event.getWhoClicked();
            Inventory inventory = event.getInventory();
            byte eventId = tag.getByte(this.CLICK_EVENT);
            
            event.setCancelled(clickStack(player, eventId));
        }
    }

    @Override
    public void handleInventorySwitching(InventorySequence inventories) {

    }
    
    private boolean clickStack(Player player, byte id) {
        UUID uuid = player.getUniqueId();
        InventorySequence playerInv = this.getPlayerOwnedInv(uuid);
        
        switch(id) {
        // this.BUILDER_PAGE
        case 0:
            TinkersSpitruct.plugin.getLogger().info("go to builder");
            playerInv.setCurrentInventory(BUILDER);
            break;
        
        // this.BUILDER_PAGE
        case 1:
            TinkersSpitruct.plugin.getLogger().info("go to tool choice");
            playerInv.setCurrentInventory(TOOL_CHOICE);
            break;

        default: return true;
        }
        
//        player.closeInventory();
        player.openInventory(playerInv.getInventory());
        return false;
    }
    
    // ======== Handles end ======== //

    @Override
    public Map<UUID, InventorySequence> getPlayerMap() {
        return this.playerMap;
    }

    @Override
    public InventorySequence getPlayerOwnedInv(UUID uuid) {
        InventorySequence result = this.playerMap.get(uuid);

        if(result == null) {
            result = this.makeInventory();
            this.playerMap.put(uuid, result);
        }

        return result;
    }

    @Override
    public boolean doesPosExists(BlockPosition pos) {
        return this.activePoses.contains(pos);
    }

    @Override
    public boolean doesPosExists(int x, int y, int z) {
        return this.doesPosExists(new BlockPosition(x, y, z));
    }



}
