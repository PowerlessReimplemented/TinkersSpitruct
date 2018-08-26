package powerlessri.bukkit.tinkersspitruct;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import powerlessri.bukkit.tinkersspitruct.library.helpers.file.FilePathHelper;

public class Reference {

    private Reference() {
    }

    public static List<TinkersSpitruct> plugins;
    
    public static void setPlugin(TinkersSpitruct plg) {
        if(plugins == null) {
            plugins = new ArrayList<TinkersSpitruct>();
        }
        
        if(plg != null) {
            plugins.add(plg);
        }
    }
    
    @Nullable
    public static TinkersSpitruct getPlugin() {
        if(plugins == null) {
            plugins = new ArrayList<TinkersSpitruct>();
        }
        
        return plugins.size() == 0 ? null : plugins.get(0);
    }

    /** ID for internal usage */
    public static final String PLUGIN_ID = "tinkersspitruct";
    /** A human-readable name for the plugin */
    public static final String PLUGIN_NAME = "Tinker's Spitruct";
    
    public static final String SERVER_FOLDER = System.getProperty("user.dir") + FilePathHelper.dirSeparator();
    public static final String PLUGIN_FOLDER = FilePathHelper.pathDir("plugins");
    
    public static final String PLUGIN_CONFIG_FOLDER = FilePathHelper.pathDir("pluginConfig");

}
