package powerlessri.bukkit.tinkersspitruct.library.files.helpers;

public class FilePathHelper {
    
    private FilePathHelper() {
    }
    
    private static String dirSeparator;
    
    public static String dirSeparator() {
        if(dirSeparator == null) {
            dirSeparator = System.getProperty("os.name").equalsIgnoreCase("windows") ? "\\" : "/";
        }
        
        return dirSeparator;
    }
     
    public static String pathDir(String folderName) {
        return folderName + dirSeparator();
    }
    
}
