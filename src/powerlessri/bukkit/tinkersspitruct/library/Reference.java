package powerlessri.bukkit.tinkersspitruct.library;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;
import powerlessri.bukkit.tinkersspitruct.library.files.helpers.FilePathHelper;

public class Reference {

    private Reference() {
    }
    
    public static final Object PREVIOUS_DIR = "..";
    public static final Object CURRNET_DIR = ".";
    public static final String SERVER_FOLDER = System.getProperty("user.dir") + FilePathHelper.dirSeparator();
    public static final String PLUGIN_FOLDER = FilePathHelper.pathDir("plugins");
    
    public static final String PLUGIN_CONFIG_FOLDER = FilePathHelper.pathDir("pluginConfig");

}
