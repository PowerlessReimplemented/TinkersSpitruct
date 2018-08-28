package powerlessri.bukkit.tinkersspitruct;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginReference {
    
    private PluginReference() {
    }
    
    public static List<JavaPlugin> plugins;
    
    public static void clearPlugins() {
        plugins = new ArrayList<JavaPlugin>();
    }
    
    private static void nullSafety() {
        if(plugins == null) {
            clearPlugins();
        }
    }
    
    
    public static void addPlugin(JavaPlugin plg) {
        nullSafety();
        
        if(plg != null) {
            plugins.add(plg);
        }
    }
    
    @Nullable
    public static JavaPlugin getPlugin() {
        nullSafety();
        
        return plugins.size() == 0 ? null : plugins.get(0);
    }
    
}
