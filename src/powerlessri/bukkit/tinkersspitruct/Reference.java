package powerlessri.bukkit.tinkersspitruct;

import powerlessri.bukkit.tinkersspitruct.library.helpers.file.FilePathHelper;

public class Reference {

    private Reference() {
    }

    public static TinkersSpitruct plugin;
    public static void setPluginInstance(TinkersSpitruct plg) {
        if(plg != null) {
            plugin = plg;
        } else {
            plugin = new TinkersSpitruct();
        }
    }

    /** ID for internal usage */
    public static final String PLUGIN_ID = "tinkersspitruct";
    /** A human-readable name for the plugin */
    public static final String PLUGIN_NAME = "Tinker's Spitruct";
    
    public static final String SERVER_FOLDER = System.getProperty("user.dir") + FilePathHelper.dirSeparator();
    public static final String PLUGIN_FOLDER = FilePathHelper.pathDir("plugins");
    
    public static final String PLUGIN_CONFIG_FOLDER = FilePathHelper.pathDir("pluginConfig");

}
