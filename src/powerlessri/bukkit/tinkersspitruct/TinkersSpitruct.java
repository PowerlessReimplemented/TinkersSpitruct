package powerlessri.bukkit.tinkersspitruct;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import powerlessri.bukkit.library.registry.ItemBase;
import powerlessri.bukkit.library.registry.Registry;
import powerlessri.bukkit.library.registry.TileBase;
import powerlessri.bukkit.library.string.LangMap;
import powerlessri.bukkit.library.tags.CommonTags;
import powerlessri.bukkit.tinkersspitruct.commands.CommandBase;
import powerlessri.bukkit.tinkersspitruct.commands.CommandSpitructDebug;
import powerlessri.bukkit.tinkersspitruct.eastereggs.MainPranker;
import powerlessri.bukkit.tinkersspitruct.events.InventoryClickHandler;
import powerlessri.bukkit.tinkersspitruct.events.WorldInteractionHandler;
import powerlessri.bukkit.tinkersspitruct.inventory.machines.InventoryToolBuilder;
import powerlessri.bukkit.tinkersspitruct.items.ItemInventoryButton;

public class TinkersSpitruct extends JavaPlugin { 
    
    /** Reference to the current plugin instance */
    public static TinkersSpitruct plugin;
    
    /** ID for internal usage */
    public static final String PLUGIN_ID = "tinkersspitruct";
    /** A human-readable name for the plugin */
    public static final String PLUGIN_NAME = "Tinker's Spitruct";
    
    
    public MainPranker pranker;
    
    public Registry<ItemBase> itemRegistry;
    public Registry<TileBase> tileRegistry;
    
    public InventoryClickHandler clickHandler;
    public WorldInteractionHandler interactionHandler;
    
    public InventoryToolBuilder toolBuilders;
//    public PositionalInventoryStorage partBuilders;
//    public PositionalInventoryStorage stencilTables;
//    
//    public PositionalInventoryStorage partternChests;
//    public PositionalInventoryStorage partChests;
    
    public LangMap lang;
    
    @Override
    public void onEnable() {
        CommonTags.resetItemTagFixers(PLUGIN_ID);
        
        plugin = this;
        PluginReference.clearPlugins();
        PluginReference.addPlugin(this);
        
        this.pranker = new MainPranker(this);
        
        this.itemRegistry = new Registry<>();
        this.tileRegistry = new Registry<>();
        
        this.reloadLang("en_US");
        
        registerCommand(new CommandSpitructDebug(this));
        
        this.clickHandler = new InventoryClickHandler(this);
        this.interactionHandler = new WorldInteractionHandler(this);
        registerEvent(this.clickHandler);
        registerEvent(this.interactionHandler);
        
        this.toolBuilders = new InventoryToolBuilder(this);
        
        // Test //
        
        itemRegistry.registerItem(new ItemInventoryButton(
                this, "test", Material.STICK, (event) -> {}, true));
        
        // Test //
        
        this.pranker.doConsolePranks();
        this.pranker.haha();
    }
    
    @Override
    public void onDisable() {
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
    
    
    public String translate(String key) {
        return this.lang.get(key);
    }
    
    public String translate(String key, int index) {
        return this.lang.getList(key).get(index);
    }
    
}
