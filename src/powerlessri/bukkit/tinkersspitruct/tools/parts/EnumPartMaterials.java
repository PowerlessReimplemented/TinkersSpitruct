package powerlessri.bukkit.tinkersspitruct.tools.parts;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;

import powerlessri.bukkit.library.registry.VanillaRegistryHelper;

public enum EnumPartMaterials {
    
    LEAVES(Material.LEAVES, 16),
    STONE(Material.COBBLESTONE, 2),
    BONE(Material.BONE, 4),
    CACTUS(Material.CACTUS, 2),
    PAPER(Material.PAPER, 8),
    NETHERRACK(Material.NETHERRACK, 2),
    NETHER_BRICK(Material.NETHER_BRICK, 4),
    IRON(Material.IRON_INGOT, 1),
    GOLD(Material.GOLD_INGOT, 1),
    SPONGE(Material.SPONGE, 1),
    PRISMARINE(Material.PRISMARINE, 4);
    
    public final int baseCost;
    public final Material type;
    
    public final String registryName;
    
    private EnumPartMaterials(Material type, int baseCost) {
        this.baseCost = baseCost;
        this.type = type;
        
        this.registryName = VanillaRegistryHelper.getRegistryName(type);
        
        addAllowedPartMaterial(type);
    }
    
    
    
    private static final Set<Material> materialSet = new HashSet<>();
    
    private static void addAllowedPartMaterial(Material type) {
        materialSet.add(type);
    }
    
    
    public static boolean isPartMaterial(Material type) {
        return materialSet.contains(type);
    }
    
    public static EnumPartMaterials getEnumMaterial(Material type) {
       for(int i = 0; i < values().length; i++) {
           EnumPartMaterials current = values()[i];
           
           if(current.type == type) {
               return current;
           }
       }
       
       return values()[0];
    }

    public static int getCost(Material type) {
        return getEnumMaterial(type).baseCost;
    }
    
}
