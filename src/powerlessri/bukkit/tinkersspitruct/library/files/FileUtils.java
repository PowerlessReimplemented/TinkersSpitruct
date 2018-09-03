package powerlessri.bukkit.tinkersspitruct.library.files;

import java.io.File;

import powerlessri.bukkit.tinkersspitruct.library.string.Analyzer;

public class FileUtils {

    private FileUtils() {
    }

    /** Go through the given path, make sure every path is created, and return the combined path from root. */
    public static String initalize(String... path) {
        String result = "";

        for(int i = 0; i < path.length; i++) {
            result += path[i];

            if(Analyzer.lastChar(path[i]) != PathHelper.dirDivider()) {
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
