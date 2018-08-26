package powerlessri.bukkit.tinkersspitruct;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import powerlessri.bukkit.tinkersspitruct.library.annotations.FinalField;
import powerlessri.bukkit.tinkersspitruct.library.lang.LangMap;
import powerlessri.bukkit.tinkersspitruct.library.pranks.MainPranker;

public class TinkersSpitruct extends JavaPlugin { 
    
    @FinalField
    public ConfigurationSection config;
    @FinalField
    public MainPranker pranker;
    
    public LangMap lang;
    
    public void onEnable() {
        Reference.setPlugin(this);
        
        this.config = getConfig();
        this.pranker = new MainPranker(getLogger());
        
        this.reloadLang("en_US");
        
        getLogger().info("Plugin " + Reference.PLUGIN_ID + " loaded.");
    }
    
    public void onDisable() {
    }
    
    
    public void reloadLang(String file) {
        this.lang = new LangMap();
        this.loadExtra(file);
    }
    
    public void loadExtra(String file) {
        this.lang.load(file);
    }
    
}
