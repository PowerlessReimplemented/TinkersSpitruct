package powerlessri.bukkit.library.effects;

import java.lang.reflect.Field;

import org.bukkit.enchantments.Enchantment;

public class Effects {

    public static final EnchantGlowing GLOW;

    static {

        if(!Enchantment.isAcceptingRegistrations()) {
            try {
                Field field = Enchantment.class.getDeclaredField("acceptingNew");
                field.setAccessible(true);
                field.set(null, true);
            } catch (Exception e) {
                System.out.println("[ERROR] Error when getting field from Enchentment class");
                e.printStackTrace();
            }
        }


        int enchantId = 100;
        EnchantGlowing glow = null;

        try {
            glow = new EnchantGlowing(enchantId++);

            Enchantment.registerEnchantment(glow);
        } catch(RuntimeException e){
            System.out.println("[ERROR] Error when registering enchantment GlowingEffect");
            e.printStackTrace();
        }

        GLOW = glow;
    }

}
