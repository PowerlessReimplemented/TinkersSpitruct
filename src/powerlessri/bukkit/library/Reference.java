package powerlessri.bukkit.library;

import powerlessri.bukkit.library.files.PathHelper;

public class Reference {

    private Reference() {
    }
    
    public static final Object PREVIOUS_DIR = "..";
    public static final Object CURRNET_DIR = ".";
    public static final String SERVER_FOLDER = System.getProperty("user.dir") + PathHelper.dirSeparator();
    public static final String PLUGIN_FOLDER = PathHelper.pathDir("plugins");
    
    public static final String PLUGIN_CONFIG_FOLDER = PathHelper.pathDir("pluginConfig");
    public static final String PLUGIN_DATA_FOLDER = PathHelper.pathDir("pluginData");

}
