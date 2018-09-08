package powerlessri.bukkit.tinkersspitruct.tools.parts;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface IPart {
    
    /** Original id */
    String getIdName();
    
//    Material getMaterial();
//    
//    // Implementation Note:
//    //     This method should be design so that it can only be called once.
//    void setMaterial(Material material);
    
    
    
    boolean isAllowed(Material material);
    boolean isCraftable(ItemStack ingredient);
    ItemStack getCraftResult(Material material);
    
}
