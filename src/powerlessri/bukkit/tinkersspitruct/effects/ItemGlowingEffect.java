package powerlessri.bukkit.tinkersspitruct.effects;

import java.lang.reflect.Field;
import java.util.logging.Level;

import javax.annotation.Nullable;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;

public class ItemGlowingEffect extends Enchantment {
    
    @Nullable
    public static ItemGlowingEffect registerGlow(TinkersSpitruct plugin) {
        ItemGlowingEffect glow = null;
        
        try {
            Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);
        } catch (Exception e) {
            plugin.getLogger().log(Level.WARNING, "Error when getting field from Enchentment glass", e);
        }
        
        try {
            glow = new ItemGlowingEffect(100);
            Enchantment.registerEnchantment(glow);
        } catch (IllegalArgumentException e){
        } catch(Exception e){
            plugin.getLogger().log(Level.WARNING, "Error when registering enchantment GlowingEffect", e);
        }
        
        return glow;
    }
    
    
    public ItemGlowingEffect(int id) {
        super(id);
    }

    @Override
    public boolean canEnchantItem(ItemStack arg0) {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment arg0) {
        return false;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public int getMaxLevel() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }
    
}
