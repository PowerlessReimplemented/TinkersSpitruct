package powerlessri.bukkit.tinkersspitruct.eastereggs;

import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;

public class MainPranker {
    
    public MainPranker() {
    }

    public void consolePrank(String prank) {
        TinkersSpitruct.plugin.getLogger().info(prank);
    }
    
    
    
    public void doConsolePranks() {
        this.consolePrank(TinkersSpitruct.plugin.lang.translate("prank.startUp.tic&natura"));
    }
    
    public void haha() {
        this.consolePrank(TinkersSpitruct.plugin.lang.translate("prank.messages.haha"));
    }
    
}
