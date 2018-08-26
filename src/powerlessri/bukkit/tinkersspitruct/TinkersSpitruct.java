package powerlessri.bukkit.tinkersspitruct;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import powerlessri.bukkit.tinkersspitruct.library.annotations.FinalField;
import powerlessri.bukkit.tinkersspitruct.library.lang.LangMap;

public class TinkersSpitruct extends JavaPlugin { 
    
    @FinalField
    public ConfigurationSection config;
    
    public LangMap lang;
    
    public void onEnable() {
        this.config = getConfig();
        this.lang = new LangMap();
        
        Reference.setPluginInstance(this);
        this.lang.load("en_US");
        
        getLogger().info("Plugin " + Reference.PLUGIN_ID + " loaded.");
        getLogger().info(this.lang.translate("prank.startUp.tic&natura"));
    }
    
    public void onDisable() {
    }
    
}
