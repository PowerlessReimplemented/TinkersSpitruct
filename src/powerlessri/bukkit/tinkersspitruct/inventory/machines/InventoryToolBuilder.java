package powerlessri.bukkit.tinkersspitruct.inventory.machines;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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
import powerlessri.bukkit.tinkersspitruct.library.tags.TagHelper;
import powerlessri.bukkit.tinkersspitruct.library.tags.TaggedItemBuilder;
import powerlessri.bukkit.tinkersspitruct.tags.PluginTagHelper;

// Somehow make everything static does not work
public class InventoryToolBuilder implements IMachineInventoryBuilder {

    public static final String TOOL_BUILDER = "toolBuilder";
    public static final String PAGE_BUILDER = TOOL_BUILDER + ".builder";
    public static final String PAGE_TOOL_CHOICE = TOOL_BUILDER + ".toolChoice";
    
    public static final String CLICK_EVENT_ID = "toolBuilderClickEvent";

    
    
    private final TinkersSpitruct plugin;
    
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
    
    private final byte TOOL_ID_MIN; // Id of first tool
    private final byte TOOL_ID_MAX; // Id of last tool

    private final ItemStack[] toolChoiceList;



    private final InventorySequenceBuilder sequence;

    private final Map<UUID, InventorySequence> playerMap;
    private final Set<BlockPosition> activePoses;

    public InventoryToolBuilder(TinkersSpitruct plugin, String... listAviliableTools) {
        
        this.plugin = plugin;
        
        this.inventoryTitle = plugin.translate("inventory.toolBuilder.gui.title");
        
        // allocations
        int nextId = 0;

        this.builderId = (byte) nextId++;
        this.toolChoiceId = (byte) nextId++;
        
        // ================================ //
        // Inventory toggling button construct

        NBTTagCompound rootTag = new NBTTagCompound();
        rootTag.set(TinkersSpitruct.PLUGIN_ID, new NBTTagCompound());

        NBTTagCompound pluginTag = rootTag.getCompound(TinkersSpitruct.PLUGIN_ID);
        pluginTag.setString(ItemTags.OWNER.getKey(), TOOL_BUILDER);
        pluginTag.setBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey(), true);


        NBTTagCompound buttonBuilderTag = (NBTTagCompound) rootTag.clone();
        NBTTagCompound buttonToolChoiceTag = (NBTTagCompound) rootTag.clone();

        buttonBuilderTag.getCompound(TinkersSpitruct.PLUGIN_ID).setByte(this.CLICK_EVENT_ID, this.builderId);
        buttonToolChoiceTag.getCompound(TinkersSpitruct.PLUGIN_ID).setByte(this.CLICK_EVENT_ID, this.toolChoiceId);

        ItemStack buttonBuilder = TagHelper.getStackWithTag(new ItemStack(Material.FURNACE), buttonBuilderTag);
        ItemStack buttonToolChoice = TagHelper.getStackWithTag(new ItemStack(Material.DIAMOND_PICKAXE), buttonToolChoiceTag);

        // ================================ //
        // Inventory toggling button item meta

        ItemMeta metaBuilder = buttonBuilder.getItemMeta();
        ItemMeta metaToolChoice = buttonToolChoice.getItemMeta();

        metaBuilder.setDisplayName( plugin.translate("inventory.toolBuilder.gui.button.builder") );
        metaToolChoice.setDisplayName( plugin.translate("inventory.toolBuilder.gui.button.toolChoice") );
        metaBuilder.addEnchant( plugin.glow, 1, true);
        metaToolChoice.addEnchant( plugin.glow, 1, true);

        buttonBuilder.setItemMeta(metaBuilder);
        buttonToolChoice.setItemMeta(metaToolChoice);

        // ================================ //
        // Tool choices


        this.toolBuilder = TaggedItemBuilder.builderOf(null);

        this.toolBuilder.addTagCompound(TinkersSpitruct.PLUGIN_ID);
        this.toolBuilder.cd(TinkersSpitruct.PLUGIN_ID);
        
        this.toolBuilder.addDefaultBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey(), true);
        this.toolBuilder.addDefaultString(ItemTags.OWNER.getKey(), TOOL_BUILDER);
        this.toolBuilder.addDefaultByte(this.CLICK_EVENT_ID, (byte) -1);


        this.TOOL_ID_MIN = (byte) nextId;
        
        this.PICKAXE_ID = (byte) nextId++;
        this.HATCHET_ID = (byte) nextId++;
        this.SHOVEL_ID = (byte) nextId++;
        this.KAMA_ID = (byte) nextId++;
        this.SHEAR_ID = (byte) nextId++;
        this.HAMMER_ID = (byte) nextId++;
        this.LUMBERAXE_ID = (byte) nextId++;
        this.EXCAVATOR_ID = (byte) nextId++;
        this.FISHING_ROD_ID = (byte) nextId++;
        
        this.TOOL_ID_MAX = (byte) (nextId - 1);

        this.PICKAXE = this.setClickId(this.toolBuilder.buildItem(Material.IRON_PICKAXE), PICKAXE_ID);
        this.HATCHET = this.setClickId(this.toolBuilder.buildItem(Material.IRON_AXE), HATCHET_ID);
        this.SHOVEL = this.setClickId(this.toolBuilder.buildItem(Material.IRON_SPADE), SHOVEL_ID);
        this.KAMA = this.setClickId(this.toolBuilder.buildItem(Material.IRON_HOE), KAMA_ID);
        this.SHEAR = this.setClickId(this.toolBuilder.buildItem(Material.SHEARS), SHEAR_ID);
        this.HAMMER = this.setClickId(this.toolBuilder.buildItem(Material.DIAMOND_PICKAXE), HAMMER_ID);
        this.LUMBERAXE = this.setClickId(this.toolBuilder.buildItem(Material.DIAMOND_AXE), LUMBERAXE_ID);
        this.EXCAVATOR = this.setClickId(this.toolBuilder.buildItem(Material.DIAMOND_SPADE), EXCAVATOR_ID);
        this.FISHING_ROD = this.setClickId(this.toolBuilder.buildItem(Material.FISHING_ROD), FISHING_ROD_ID);

        TagHelper.setStackName(this.PICKAXE, plugin.translate("inventory.toolBuilder.gui.toolChoice.pickaxe"));
        TagHelper.setStackName(this.HATCHET, plugin.translate("inventory.toolBuilder.gui.toolChoice.hatchet"));
        TagHelper.setStackName(this.SHOVEL, plugin.translate("inventory.toolBuilder.gui.toolChoice.shovel"));
        TagHelper.setStackName(this.KAMA, plugin.translate("inventory.toolBuilder.gui.toolChoice.kama"));
        TagHelper.setStackName(this.SHEAR, plugin.translate("inventory.toolBuilder.gui.toolChoice.shears"));
        TagHelper.setStackName(this.HAMMER, plugin.translate("inventory.toolBuilder.gui.toolChoice.hammer"));
        TagHelper.setStackName(this.LUMBERAXE, plugin.translate("inventory.toolBuilder.gui.toolChoice.lumberAxe"));
        TagHelper.setStackName(this.EXCAVATOR, plugin.translate("inventory.toolBuilder.gui.toolChoice.excavator"));
        TagHelper.setStackName(this.FISHING_ROD, plugin.translate("inventory.toolBuilder.gui.toolChoice.fishingRod"));
        
        TagHelper.addEnchantment(this.PICKAXE, plugin.glow, 1);
        TagHelper.addEnchantment(this.HATCHET, plugin.glow, 1);
        TagHelper.addEnchantment(this.SHOVEL, plugin.glow, 1);
        TagHelper.addEnchantment(this.KAMA, plugin.glow, 1);
        TagHelper.addEnchantment(this.SHEAR, plugin.glow, 1);
        TagHelper.addEnchantment(this.HAMMER, plugin.glow, 1);
        TagHelper.addEnchantment(this.LUMBERAXE, plugin.glow, 1);
        TagHelper.addEnchantment(this.EXCAVATOR, plugin.glow, 1);
        TagHelper.addEnchantment(this.FISHING_ROD, plugin.glow, 1);

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
        // Inventory builder

        InventoryBuilder builder = new InventoryBuilder(PAGE_BUILDER, 6, this.inventoryTitle);
        InventoryBuilder toolChoice = new InventoryBuilder(PAGE_TOOL_CHOICE, 6, this.inventoryTitle);

        builder.addImmovableSlot(0, buttonToolChoice);
        toolChoice.addImmovableSlot(0, buttonBuilder);

        for(int i = 0; i < TOOL_ID_MAX; i++) {
            // 18 is slot of the third line
            toolChoice.addSlot(i + 18, this.toolChoiceList[i]);
        }

        builder.blockEmptySlots();
        toolChoice.blockEmptySlots();

        this.sequence = new InventorySequenceBuilder(TOOL_BUILDER, builder, toolChoice);

        // ================================ //

        this.playerMap = new HashMap<UUID, InventorySequence>();
        this.activePoses = new HashSet<BlockPosition>();
    }

    private ItemStack setClickId(ItemStack stack, byte id) {
        NBTTagCompound pluginTag = PluginTagHelper.getPluginTag(stack);
        pluginTag.setByte(this.CLICK_EVENT_ID, id);
        return PluginTagHelper.saveToStack(stack, pluginTag);
    }

    @Override
    public InventorySequence makeInventory() {
        return sequence.makeInventory();
    }

    // ======== Handles start ======== //

    @Override
    public void handleStackClicked(InventoryClickEvent event, ItemStack stack, NBTTagCompound tag) {
        if(tag != null && tag.getString(ItemTags.OWNER.getKey()).equals(TOOL_BUILDER)) {
            Player player = (Player) event.getWhoClicked();
            Inventory inventory = event.getInventory();
            byte eventId = tag.getByte(this.CLICK_EVENT_ID);

            attemptToggleInventory(player, inventory, eventId);
            appemptSelectTool(player, inventory, eventId);
        }
    }

    @Override
    public void handleOpenInventory(PlayerInteractEvent event, int x, int y, int z) {
        if(this.doesPosExists(x, y, z)) {
            Player player = event.getPlayer();
            Inventory inventory = this.getPlayerOwnedInv(player.getUniqueId()).getInventory();
            
            player.openInventory(inventory);
        }
    }
    
    @Override
    public void handleBlockPlacement(PlayerInteractEvent event, int x, int y, int z) {
        ItemStack hand = event.getPlayer().getInventory().getItemInMainHand();
        NBTTagCompound tag = PluginTagHelper.getPluginTag(hand);
        plugin.getLogger().info("place block");
        if(tag.getString(ItemTags.OWNER.getKey()).equals(TOOL_BUILDER)) {
            this.activePoses.add(new BlockPosition(x, y, z));
        }
    }

    private void attemptToggleInventory(Player player, Inventory inv, byte id) {
        if(id < 0) {
            return;
        }

        UUID uuid = player.getUniqueId();
        InventorySequence playerInv = this.getPlayerOwnedInv(uuid);

        switch(id) {
        case 0:
            playerInv.setCurrentInventory(PAGE_BUILDER);
            break;

        case 1:
            playerInv.setCurrentInventory(PAGE_TOOL_CHOICE);
            break;
        }

        player.openInventory(playerInv.getInventory());
    }

    private void appemptSelectTool(Player player, Inventory inventory, byte id) {
        if(id < this.TOOL_ID_MIN || id > this.TOOL_ID_MAX) {
            return;
        }
        
        // 2 is the the third slot, left an empty one besides the button
        inventory.setItem(2, this.toolChoiceList[id - this.TOOL_ID_MIN]);
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
