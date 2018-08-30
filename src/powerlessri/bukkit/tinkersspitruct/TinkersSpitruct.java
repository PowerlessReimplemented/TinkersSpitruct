package powerlessri.bukkit.tinkersspitruct;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import powerlessri.bukkit.tinkersspitruct.commands.CommandBase;
import powerlessri.bukkit.tinkersspitruct.commands.CommandSpitructDebug;
import powerlessri.bukkit.tinkersspitruct.eastereggs.MainPranker;
import powerlessri.bukkit.tinkersspitruct.effects.ItemGlowingEffect;
import powerlessri.bukkit.tinkersspitruct.events.EventCalls;
import powerlessri.bukkit.tinkersspitruct.events.InventoryEventHandler;
import powerlessri.bukkit.tinkersspitruct.inventory.PluginInventories;
import powerlessri.bukkit.tinkersspitruct.inventory.machines.InventoryToolBuilder;
import powerlessri.bukkit.tinkersspitruct.library.annotations.FinalField;
import powerlessri.bukkit.tinkersspitruct.library.inventory.InventorySequence;
import powerlessri.bukkit.tinkersspitruct.library.lang.LangMap;
import powerlessri.bukkit.tinkersspitruct.library.tags.CommonTags;

public class TinkersSpitruct extends JavaPlugin { 
    
    /** Reference to the current plugin instance */
    public static TinkersSpitruct plugin;
    
    /** ID for internal usage */
    public static final String PLUGIN_ID = "tinkersspitruct";
    /** A human-readable name for the plugin */
    public static final String PLUGIN_NAME = "Tinker's Spitruct";
    
    
    @FinalField
    public MainPranker pranker;
    
    @FinalField
    @Deprecated
    public Map<String, EventCalls> eventCalls;
    
    public PluginInventories toolBuilders;
    public PluginInventories partBuilders;
    public PluginInventories stencilTables;
    
    public PluginInventories partternChests;
    public PluginInventories partChests;
    
    @FinalField
    public ItemGlowingEffect glow;
    
    public LangMap lang;
    
    @Override
    public void onEnable() {
        CommonTags.resetItemTagFixers(PLUGIN_ID);
        
        plugin = this;
        PluginReference.clearPlugins();
        PluginReference.addPlugin(this);
        
        this.pranker = new MainPranker();
        this.eventCalls = new HashMap<String, EventCalls>();
        
        this.glow = ItemGlowingEffect.registerGlow();
        
        this.reloadLang("en_US");
        
        registerCommand(new CommandSpitructDebug());
        
        registerEvent(new InventoryEventHandler());
        
        this.toolBuilders = new PluginInventories(new InventoryToolBuilder());
        
        
        getLogger().info(PLUGIN_ID + " loaded.");
        
        this.pranker.doConsolePranks();
        this.pranker.haha();
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
    
    
    private void registerEvent(Listener event) {
        Bukkit.getPluginManager().registerEvents(event, this);
    }
    
    private void registerCommand(CommandBase command) {
        this.getCommand(command.getName()).setExecutor(command);
    }
    
    
    private void reloadLang(String file) {
        this.lang = new LangMap(getLogger(), PLUGIN_ID);
        this.loadLang(file);
    }
    
    private void loadLang(String file) {
        this.lang.load(file);
    }
    
    
    // Test //
    
    public InventorySequence toolBuilder;
//    public Inventory testInventory;
//    public Inventory testInventory2;
    
}
