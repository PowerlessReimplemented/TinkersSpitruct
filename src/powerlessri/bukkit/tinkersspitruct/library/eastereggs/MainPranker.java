package powerlessri.bukkit.tinkersspitruct.library.eastereggs;

import java.util.logging.Logger;

import powerlessri.bukkit.tinkersspitruct.Reference;
import powerlessri.bukkit.tinkersspitruct.library.lang.LangMap;

public class MainPranker {
    
    private final Logger pluginLogger;
    
    public MainPranker() {
        this.pluginLogger = Reference.getPlugin().getLogger();
    }

    public void consolePrank(String prank) {
        this.pluginLogger.info(prank);
    }
    
    
    
    public void doConsolePranks() {
        LangMap lang = Reference.getPlugin().lang;
        
        this.consolePrank(lang.translate("prank.startUp.tic&natura"));
    }
    
}
