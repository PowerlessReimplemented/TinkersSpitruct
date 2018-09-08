package powerlessri.bukkit.tinkersspitruct.tools.parts;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import powerlessri.bukkit.library.registry.VanillaRegistryHelper;

public class PickaxeHead implements IPartHead {
    
    private final float costMultiplyer;
    
    public PickaxeHead(float costMultiplyer) {
        this.costMultiplyer = costMultiplyer;
    }

    @Override
    public String getIdName() {
        return "pickaxe_head";
    }

    @Override
    public boolean isAllowed(Material material) {
        return EnumPartMaterials.isPartMaterial(material);
    }

    @Override
    public boolean isCraftable(ItemStack ingredient) {
        if(this.isAllowed(ingredient.getType())) {
            return ingredient.getAmount() >= (int) (EnumPartMaterials.getCost(ingredient.getType()) * this.costMultiplyer);
        }
        
        return false;
    }

    @Override
    public ItemStack getCraftResult(Material type) {
        String typeName = VanillaRegistryHelper.getRegistryName(type);
        return null;
    }

}
