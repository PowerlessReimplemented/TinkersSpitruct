package powerlessri.bukkit.library.string;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;

import com.google.common.base.Splitter;

import powerlessri.bukkit.library.Reference;
import powerlessri.bukkit.library.files.FileUtils;
import powerlessri.bukkit.library.files.PathHelper;

public class LangMap {

    public static final String ext = "lang";
    public static final Splitter equalSignSpliter = Splitter.on("=").limit(2);

    private final String pluginId;
    private final String configPath;
    private final Logger logger;
    private final Map<String, String> lang;
    private final Map<String, ArrayList<String>> mapLists;
    
    public LangMap(Logger logger, String pluginId) {
        this.pluginId = pluginId;
        
        this.configPath = FileUtils.initalize(
                Reference.SERVER_FOLDER,
                Reference.PLUGIN_FOLDER,
                Reference.PLUGIN_CONFIG_FOLDER,
                PathHelper.pathDir(pluginId));
        
        this.logger = logger;
        
        this.lang = new HashMap<String, String>();
        this.mapLists = new HashMap<String, ArrayList<String>>();
    }
    
    public void load(String fileName) {
        FileInputStream langReader;
        
        try {
            //TODO support place holders
            langReader = new FileInputStream(this.configPath + fileName + "." + ext);
            
            for(String line : IOUtils.readLines(langReader, StandardCharsets.UTF_8)) {
                
//                line = line.trim();
                
                // Comment start with '#'
                if (!line.isEmpty() && line.charAt(0) != '#') {
                    Iterator<String> data = equalSignSpliter.split(line).iterator();
                    
                    if(!data.hasNext()) continue;
                    String key = data.next();
                    
                    if(!data.hasNext()) continue;
                    String value = data.next();
                    
                    this.lang.put(key, value);
                }
            }
        } catch(FileNotFoundException e) {
            this.logger.log(Level.WARNING, "The specified file path does not exist!", e);
        } catch(Throwable e) {
            this.logger.log(Level.WARNING, "Unkown error occured during loading lang.", e);
        }
    }
    
    public String get(String key) {
        return this.lang.containsKey(key) ? this.lang.get(key) : key;
    }
    
    public ArrayList<String> getList(String key) {
        if(this.mapLists.containsKey(key)) {
            return this.mapLists.get(key);
        }
        
        int index = 1;
        ArrayList<String> result = new ArrayList<String>();
        
        while(true) {
            String indexedKey = key + "@" + index;
            if(!this.lang.containsKey(indexedKey)) {
                break;
            }
            
            result.add(this.lang.get(indexedKey));
        }
        
        this.mapLists.put(key, result);
        return result;
    }
    
}
