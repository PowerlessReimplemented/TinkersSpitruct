package powerlessri.bukkit.tinkersspitruct.tools;

import powerlessri.bukkit.tinkersspitruct.tools.parts.IPart;

public interface ITool {
    
    String getName();
    byte getId();
    
    IPart[] getParts();
    
}
