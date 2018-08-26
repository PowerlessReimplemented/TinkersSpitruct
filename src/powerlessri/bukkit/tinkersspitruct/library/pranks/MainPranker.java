package powerlessri.bukkit.tinkersspitruct.library.pranks;

import java.util.logging.Logger;

public class MainPranker {
    
    private final Logger pluginLogger;
    
    public MainPranker(Logger pluginLogger) {
        this.pluginLogger = pluginLogger;
    }

    public void consolePrank(String prank) {
        this.pluginLogger.info(prank);
    }
    
}
