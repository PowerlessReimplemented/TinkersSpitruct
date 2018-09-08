package powerlessri.bukkit.library.files;

import java.io.File;

import powerlessri.bukkit.library.string.StringAnalyzer;

public class FileUtils {

    private FileUtils() {
    }

    /** Go through the given path, make sure every path is created, and return the combined path from root. */
    public static String initalize(String... path) {
        String result = "";

        for(int i = 0; i < path.length; i++) {
            result += path[i];

            if(StringAnalyzer.lastChar(path[i]) != PathHelper.dirDivider()) {
                result += PathHelper.dirSeparator();
            }


            File dir = new File(result);

            if(!dir.exists()) {
                dir.mkdir();
            }
        }

        return result;
    }

}
