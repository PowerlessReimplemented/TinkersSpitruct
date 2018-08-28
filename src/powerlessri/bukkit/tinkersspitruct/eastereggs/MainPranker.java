package powerlessri.bukkit.tinkersspitruct.eastereggs;

import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;

public class MainPranker {
    
    private final TinkersSpitruct plugin;
    
    
    public MainPranker(TinkersSpitruct plugin) {
        this.plugin = plugin;
    }

    public void consolePrank(String prank) {
        plugin.getLogger().info(prank);
    }
    
    
    
    public void doConsolePranks() {
        this.consolePrank(plugin.lang.translate("prank.startUp.tic&natura"));
    }
    
}
