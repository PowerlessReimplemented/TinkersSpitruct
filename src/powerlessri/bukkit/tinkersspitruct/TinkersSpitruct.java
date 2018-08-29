package powerlessri.bukkit.tinkersspitruct;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import powerlessri.bukkit.tinkersspitruct.commands.CommandSpitructDebug;
import powerlessri.bukkit.tinkersspitruct.eastereggs.MainPranker;
import powerlessri.bukkit.tinkersspitruct.events.InventoryEventHandler;
import powerlessri.bukkit.tinkersspitruct.events.calls.EventCalls;
import powerlessri.bukkit.tinkersspitruct.library.annotations.FinalField;
import powerlessri.bukkit.tinkersspitruct.library.inventory.InventoryBuilder;
import powerlessri.bukkit.tinkersspitruct.library.lang.LangMap;
import powerlessri.bukkit.tinkersspitruct.library.tags.CommonTags;
import powerlessri.bukkit.tinkersspitruct.library.tags.CommonTags.ItemTags;
import powerlessri.bukkit.tinkersspitruct.library.tags.TaggedItemBuilder;

public class TinkersSpitruct extends JavaPlugin { 
    
    public static TinkersSpitruct plugin;
    
    /** ID for internal usage */
    public static final String PLUGIN_ID = "tinkersspitruct";
    /** A human-readable name for the plugin */
    public static final String PLUGIN_NAME = "Tinker's Spitruct";
    
    
    @FinalField
    public MainPranker pranker;
    @FinalField
    public Map<String, EventCalls> eventCalls;
    
    public LangMap lang;
    
    @Override
    public void onEnable() {
        plugin = this;
        
        PluginReference.clearPlugins();
        PluginReference.addPlugin(this);
        
        this.pranker = new MainPranker(this);
        this.eventCalls = new HashMap<String, EventCalls>();
        
        CommonTags.resetItemTagFixers(PLUGIN_ID);
        
        this.reloadLang("en_US");
        
        this.getCommand("spitruct").setExecutor(new CommandSpitructDebug());
        
        Bukkit.getPluginManager().registerEvents(new InventoryEventHandler(), this);
        
        Runnable testCall = () -> {
            getLogger().info("item got clicked!");
        };
        int callId = addEventCall("test", testCall);
        
        InventoryBuilder builder = InventoryBuilder.createBuilder(3, "test inventory");
        
        TaggedItemBuilder taggedItems = TaggedItemBuilder.builderOf(null);
        
        taggedItems.addTagCompound(PLUGIN_ID);
        taggedItems.cd(PLUGIN_ID);
        
        taggedItems.addDefaultString(ItemTags.CLICK_EVENT_CATEGORY.getKey(), "test");
        taggedItems.addDefaultInt(ItemTags.CLICK_EVENT_ID.getKey(), callId);
        
        ItemStack stackHello = taggedItems.buildItem(Material.DIAMOND);
        ItemMeta metaHello = stackHello.getItemMeta();
        metaHello.setDisplayName("Hello, world!");
        stackHello.setItemMeta(metaHello);
        
        ItemStack stackTicspi = taggedItems.buildItem(Material.IRON_PICKAXE);
        ItemMeta metaTicspi = stackTicspi.getItemMeta();
        metaTicspi.setDisplayName(PLUGIN_NAME);
        stackTicspi.setItemMeta(metaTicspi);
        
        ItemStack stackGiveDiamonds = taggedItems.buildItem(Material.EMERALD);
        
        builder.addImmovableSlot(12, stackHello);
        builder.addImmovableSlot(13, stackTicspi);
        builder.addImmovableSlot(14, stackGiveDiamonds);
        builder.addBlankSlot(0);
        builder.addBlankSlot(9);
        builder.addBlankSlot(18);
        builder.blockEmptySlots();
        
        testInventory = builder.makeInventory();
        testInventory2 = builder.makeInventory();
        
        
        getLogger().info(PLUGIN_ID + " loaded.");
        
        this.pranker.doConsolePranks();
    }
    
    @Override
    public void onDisable() {
    }
    
    
    
    public int addEventCall(String category, Runnable call) {
        if(!this.eventCalls.containsKey(category)) {
            this.eventCalls.put(category, new EventCalls());
        }
        
        return this.eventCalls.get(category).registerCall(call);
    }
    
    public EventCalls getEventCalls(String category) {
        if(this.eventCalls.containsKey(category)) {
            return this.eventCalls.get(category);
        }
        
        return this.eventCalls.entrySet().iterator().next().getValue();
    }
    
    public void reloadLang(String file) {
        this.lang = new LangMap(getLogger(), PLUGIN_ID);
        this.loadLang(file);
    }
    
    public void loadLang(String file) {
        this.lang.load(file);
    }
    
    
    // Test //
    
    public Inventory testInventory;
    public Inventory testInventory2;
    
}
