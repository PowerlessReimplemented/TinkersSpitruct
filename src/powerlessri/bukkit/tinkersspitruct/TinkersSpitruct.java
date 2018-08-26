package powerlessri.bukkit.tinkersspitruct;

import org.bukkit.plugin.java.JavaPlugin;

public class TinkersSpitruct extends JavaPlugin { 
    
    public void onEnable() {
        getLogger().info("Plugin " + Reference.PLUGIN_ID + " loaded.");
    }
    
    public void onDisable() {
    }
    
}
