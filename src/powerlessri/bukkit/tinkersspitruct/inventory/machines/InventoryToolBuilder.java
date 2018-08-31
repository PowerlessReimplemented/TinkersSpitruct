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
import powerlessri.bukkit.tinkersspitruct.library.tags.TaggedItemBuilder;
import powerlessri.bukkit.tinkersspitruct.library.tags.helpers.TagHelper;
import powerlessri.bukkit.tinkersspitruct.tags.PluginTagHelper;

// Somehow make everything static does not work
public class InventoryToolBuilder implements IMachineInventoryBuilder {

    public final String IS_TOOL_BUILDER = "isToolBuilder";
    private final String CLICK_EVENT_ID;

    private final String builderName;
    private final String toolChoiceName;
    private final byte builderId;
    private final byte toolChoiceId;

    private final String inventoryTitle;

    private final TaggedItemBuilder toolBuilder;
    
    private final ItemStack PICKAXE;
    private final ItemStack HATCHET;
    private final ItemStack SHOVEL;
    private final ItemStack KAMA;
    private final ItemStack SHEAR;
    private final ItemStack HAMMER;
    private final ItemStack LUMBERAXE;
    private final ItemStack EXCAVATOR;
    private final ItemStack FISHING_ROD;

    private final byte PICKAXE_ID;
    private final byte HATCHET_ID;
    private final byte SHOVEL_ID;
    private final byte KAMA_ID;
    private final byte SHEAR_ID;
    private final byte HAMMER_ID;
    private final byte LUMBERAXE_ID;
    private final byte EXCAVATOR_ID;
    private final byte FISHING_ROD_ID;
    
    private final ItemStack[] toolChoiceList;



    private final InventorySequenceBuilder sequence;

    private final Map<UUID, InventorySequence> playerMap;
    private final Set<BlockPosition> activePoses;

    public InventoryToolBuilder(String... listAviliableTools) {

        this.CLICK_EVENT_ID = "toolBuilderClickEvent";

        this.builderName = "toolBuilder.builder";
        this.toolChoiceName = "toolBuilder.toolChoice";

        int nextId;

        nextId = 0;
        this.builderId = (byte) nextId++;
        this.toolChoiceId = (byte) nextId++;

        this.inventoryTitle = TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.title");

        // ================================ //

        NBTTagCompound rootTag = new NBTTagCompound();
        rootTag.set(TinkersSpitruct.PLUGIN_ID, new NBTTagCompound());

        NBTTagCompound pluginTag = rootTag.getCompound(TinkersSpitruct.PLUGIN_ID);
        pluginTag.setBoolean(IS_TOOL_BUILDER, true);
        pluginTag.setBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey(), true);


        NBTTagCompound buttonBuilderTag = (NBTTagCompound) rootTag.clone();
        NBTTagCompound buttonToolChoiceTag = (NBTTagCompound) rootTag.clone();

        buttonBuilderTag.getCompound(TinkersSpitruct.PLUGIN_ID).setByte(this.CLICK_EVENT_ID, this.builderId);
        buttonToolChoiceTag.getCompound(TinkersSpitruct.PLUGIN_ID).setByte(this.CLICK_EVENT_ID, this.toolChoiceId);

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

        
        this.toolBuilder = TaggedItemBuilder.builderOf(null);
        
        this.toolBuilder.addTagCompound(TinkersSpitruct.PLUGIN_ID);
        this.toolBuilder.cd(TinkersSpitruct.PLUGIN_ID);
        
        this.toolBuilder.addDefaultBoolean(IS_TOOL_BUILDER, true);
        this.toolBuilder.addDefaultByte(CLICK_EVENT_ID, (byte) -1);
        
        this.toolBuilder.addEnchant(TinkersSpitruct.plugin.glow, 1);
        
        
        this.PICKAXE_ID = (byte) nextId++;
        this.HATCHET_ID = (byte) nextId++;
        this.SHOVEL_ID = (byte) nextId++;
        this.KAMA_ID = (byte) nextId++;
        this.SHEAR_ID = (byte) nextId++;
        this.HAMMER_ID = (byte) nextId++;
        this.LUMBERAXE_ID = (byte) nextId++;
        this.EXCAVATOR_ID = (byte) nextId++;
        this.FISHING_ROD_ID = (byte) nextId++;
        
        this.PICKAXE = this.setClickId(this.toolBuilder.buildItem(Material.IRON_PICKAXE), PICKAXE_ID);
        this.HATCHET = this.setClickId(this.toolBuilder.buildItem(Material.IRON_AXE), HATCHET_ID);
        this.SHOVEL = this.setClickId(this.toolBuilder.buildItem(Material.IRON_SPADE), SHOVEL_ID);
        this.KAMA = this.setClickId(this.toolBuilder.buildItem(Material.IRON_HOE), KAMA_ID);
        this.SHEAR = this.setClickId(this.toolBuilder.buildItem(Material.SHEARS), SHEAR_ID);
        this.HAMMER = this.setClickId(this.toolBuilder.buildItem(Material.DIAMOND_PICKAXE), HAMMER_ID);
        this.LUMBERAXE = this.setClickId(this.toolBuilder.buildItem(Material.DIAMOND_AXE), LUMBERAXE_ID);
        this.EXCAVATOR = this.setClickId(this.toolBuilder.buildItem(Material.DIAMOND_SPADE), EXCAVATOR_ID);
        this.FISHING_ROD = this.setClickId(this.toolBuilder.buildItem(Material.FISHING_ROD), FISHING_ROD_ID);

        TagHelper.setStackName(this.PICKAXE, TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.toolChoice.pickaxe"));
        TagHelper.setStackName(this.HATCHET, TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.toolChoice.hatchet"));
        TagHelper.setStackName(this.SHOVEL, TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.toolChoice.shovel"));
        TagHelper.setStackName(this.KAMA, TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.toolChoice.kama"));
        TagHelper.setStackName(this.SHEAR, TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.toolChoice.shears"));
        TagHelper.setStackName(this.HAMMER, TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.toolChoice.hammer"));
        TagHelper.setStackName(this.LUMBERAXE, TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.toolChoice.lumberAxe"));
        TagHelper.setStackName(this.EXCAVATOR, TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.toolChoice.excavator"));
        TagHelper.setStackName(this.FISHING_ROD, TinkersSpitruct.plugin.lang.translate("inventory.toolBuilder.gui.toolChoice.fishingRod"));
        
        // Amount of slots left in the tool choice page
        this.toolChoiceList = new ItemStack[27];
        
        nextId = 0;
        this.toolChoiceList[nextId++] = this.PICKAXE;
        this.toolChoiceList[nextId++] = this.HATCHET;
        this.toolChoiceList[nextId++] = this.SHOVEL;
        this.toolChoiceList[nextId++] = this.KAMA;
        this.toolChoiceList[nextId++] = this.SHEAR;
        this.toolChoiceList[nextId++] = this.HAMMER;
        this.toolChoiceList[nextId++] = this.LUMBERAXE;
        this.toolChoiceList[nextId++] = this.EXCAVATOR;
        this.toolChoiceList[nextId++] = this.FISHING_ROD;
        
        // ================================ //

        InventoryBuilder builder = new InventoryBuilder(this.builderName, 6, this.inventoryTitle);
        InventoryBuilder toolChoice = new InventoryBuilder(this.toolChoiceName, 6, this.inventoryTitle);

        builder.addImmovableSlot(0, buttonToolChoice);
        toolChoice.addImmovableSlot(0, buttonBuilder);

        nextId = 18; // Start of third line
        toolChoice.addImmovableSlot(nextId++, this.PICKAXE);
        toolChoice.addImmovableSlot(nextId++, this.HATCHET);
        toolChoice.addImmovableSlot(nextId++, this.SHOVEL);
        toolChoice.addImmovableSlot(nextId++, this.KAMA);
        toolChoice.addImmovableSlot(nextId++, this.SHEAR);
        toolChoice.addImmovableSlot(nextId++, this.HAMMER);
        toolChoice.addImmovableSlot(nextId++, this.LUMBERAXE);
        toolChoice.addImmovableSlot(nextId++, this.EXCAVATOR);
        toolChoice.addImmovableSlot(nextId++, this.FISHING_ROD);

        builder.blockEmptySlots();
        toolChoice.blockEmptySlots();

        this.sequence = new InventorySequenceBuilder("tile.toolBuilder", builder, toolChoice);

        // ================================ //

        this.playerMap = new HashMap<UUID, InventorySequence>();
        this.activePoses = new HashSet<BlockPosition>();
    }
    
    private ItemStack setClickId(ItemStack stack, byte id) {
        NBTTagCompound pluginTag = PluginTagHelper.getPluginTag(stack);
        pluginTag.setByte(CLICK_EVENT_ID, id);
        return PluginTagHelper.saveToStack(stack, pluginTag);
    }

    @Override
    public InventorySequence makeInventory() {
        return sequence.makeInventory();
    }

    // ======== Handles start ======== //

    @Override
    public void handleStackClicked(InventoryClickEvent event, ItemStack stack, NBTTagCompound tag) {
        if(tag != null && tag.getBoolean(IS_TOOL_BUILDER)) {
            Player player = (Player) event.getWhoClicked();
            Inventory inventory = event.getInventory();
            byte eventId = tag.getByte(this.CLICK_EVENT_ID);

            buttonClicked(player, eventId);
            toolSelected(inventory, player, eventId);
        }
    }

    @Override
    public void handleInventorySwitching(InventorySequence inventories) {

    }

    private boolean buttonClicked(Player player, byte id) {
        if(id < 0) {
            return false;
        }
    
        UUID uuid = player.getUniqueId();
        InventorySequence playerInv = this.getPlayerOwnedInv(uuid);

        switch(id) {
        // this.BUILDER_PAGE
        case 0:
            playerInv.setCurrentInventory(this.builderName);
            break;

            // this.BUILDER_PAGE
        case 1:
            playerInv.setCurrentInventory(this.toolChoiceName);
            break;
        }

        player.openInventory(playerInv.getInventory());
        return false;
    }
    
    private boolean toolSelected(Inventory inventory, Player player, byte id) {
        // Tool id are from 2..27, in total 27 of them (fills the chest)
        if(id <= 1 || id >= 27) {
            return false;
        }
        TinkersSpitruct.plugin.getLogger().info("clicked tool: " + id);
        // 2 is the the third slot, left an empty one besides the button
        // - 2 because tool is starts at 2, but array index starts at 0
        inventory.setItem(2, this.toolChoiceList[id - 2]);
        
        return true;
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
