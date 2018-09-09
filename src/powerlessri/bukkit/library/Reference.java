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
    
    // Source: https://minecraft.gamepedia.com/NBT_format#TAG_definition
    public static final int TYPE_TAG_BYTE = 1;
    public static final int TYPE_TAG_SHORT = 2;
    public static final int TYPE_TAG_INT = 3;
    public static final int TYPE_TAG_LONG = 4;
    public static final int TYPE_TAG_FLOAT = 5;
    public static final int TYPE_TAG_DOUBLE = 6;
    public static final int TYPE_TAG_BYTE_ARRAY = 7;
    public static final int TYPE_TAG_INT_ARRAY = 11; 
    public static final int TYPE_TAG_LONG_ARRAY = 12;
    public static final int TYPE_TAG_STRNG = 8;
    public static final int TYPE_TAG_LIST = 9;
    public static final int TYPE_TAG_COMPOUND = 10; 

}