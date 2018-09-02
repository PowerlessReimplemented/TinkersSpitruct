package powerlessri.bukkit.tinkersspitruct.eastereggs;

import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;

public class MainPranker {
    
    private final TinkersSpitruct plugin;
    
    public MainPranker(TinkersSpitruct plugin) {
        this.plugin = plugin;
    }

    public void consolePrank(String prank) {
        TinkersSpitruct.plugin.getLogger().info(prank);
    }
    
    
    
    public void doConsolePranks() {
        this.consolePrank(plugin.translate("prank.startUp.tic&natura"));
        this.consolePrank(plugin.translate("prank.startUp.silentGems"));
    }
    
    public void haha() {
        this.consolePrank(plugin.translate("prank.messages.haha"));
    }
    
}
