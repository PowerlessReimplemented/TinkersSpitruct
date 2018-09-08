package powerlessri.bukkit.library.registry;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class VanillaRegistryHelper {
    
    private VanillaRegistryHelper() {        
    }
    
    
    
    public static String getRegistryName(Material material) {
        return CraftItemStack.asNMSCopy(new ItemStack(material)).getName();
    }
    
}
