package powerlessri.bukkit.tinkersspitruct.library.files;

import powerlessri.bukkit.tinkersspitruct.library.string.Analyzer;

public class PathHelper {
    
    private PathHelper() {
    }
    
    private static String dirSeparator;
    
    /** Get the divider (e.g. '\' for Windows) based on user's system */
    public static String dirSeparator() {
        if(dirSeparator == null) {
            dirSeparator = System.getProperty("os.name").equalsIgnoreCase("windows") ? "\\" : "/";
        }
        
        return dirSeparator;
    }
    
    /** Exact result like {@code dirSeparator}, but char instead of String */
    public static char dirDivider() {
        return dirSeparator().charAt(0);
    }
    
    /** Add a divider at the end of {@code folderName} */
    public static String pathDir(String folderName) {
        if(Analyzer.lastChar(folderName) != dirDivider()) {
            return folderName + dirSeparator();
        }
        return folderName;
    }
    
}
