package powerlessri.bukkit.tinkersspitruct.library.lang;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

import org.apache.commons.io.IOUtils;

import com.google.common.base.Splitter;

import powerlessri.bukkit.tinkersspitruct.Reference;
import powerlessri.bukkit.tinkersspitruct.library.helpers.file.FilePathHelper;

public class LangMap {

    public static final Splitter equalSignSpliter = Splitter.on("=").limit(2);

    private final Map<String, String> map;
    private final Map<String, ArrayList<String>> mapLists;
    
    public LangMap() {
        this.map = new HashMap<String, String>();
        this.mapLists = new HashMap<String, ArrayList<String>>();
    }
    
    public void load(String fileName) {
        FileInputStream langReader;
        
        try {
            langReader = new FileInputStream(
                    Reference.SERVER_FOLDER +
                    Reference.PLUGIN_FOLDER +
                    Reference.PLUGIN_CONFIG_FOLDER +
                    FilePathHelper.pathDir(Reference.PLUGIN_ID) +
                    fileName + ".lang");
            
            for(String line : IOUtils.readLines(langReader, StandardCharsets.UTF_8)) {
                
                // Comment start with '#'
                if (!line.isEmpty() && line.charAt(0) != '#') {
                    Iterator<String> data = equalSignSpliter.split(line).iterator();
                    
                    if(!data.hasNext()) continue;
                    String key = data.next();
                    
                    if(!data.hasNext()) continue;
                    String value = data.next();
                    
                    this.map.put(key, value);
                }
            }
        } catch(FileNotFoundException e) {
            Reference.getPlugin().getLogger().log(Level.WARNING, "The specified file path does not exist!", e);
        } catch(Throwable e) {
            Reference.getPlugin().getLogger().log(Level.WARNING, "Unkown error occured during loading .lang file.", e);
        }
    }
    
    public String translate(String key) {
        return this.map.containsKey(key) ? this.map.get(key) : key;
    }
    
    public ArrayList<String> tranlationList(String key) {
        if(this.mapLists.containsKey(key)) {
            return this.mapLists.get(key);
        }
        
        int index = 1;
        ArrayList<String> result = new ArrayList<String>();
        
        while(true) {
            String indexedKey = key + "@" + index;
            if(!this.map.containsKey(indexedKey)) {
                break;
            }
            
            result.add(this.map.get(indexedKey));
        }
        
        this.mapLists.put(key, result);
        return result;
    }
    
}
