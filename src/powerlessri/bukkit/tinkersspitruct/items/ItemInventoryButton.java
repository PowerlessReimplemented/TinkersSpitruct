package powerlessri.bukkit.tinkersspitruct.items;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.library.effects.Effects;
import powerlessri.bukkit.library.events.IInventoryEventHandler;
import powerlessri.bukkit.library.tags.CommonTags.ItemTags;
import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;

public class ItemInventoryButton extends PluginItemBase implements IInventoryEventHandler {
    
    protected final List<Consumer<InventoryClickEvent>> clickEvents;
    
    public ItemInventoryButton(TinkersSpitruct plugin, String id, Material type,
            Consumer<InventoryClickEvent> defaultEvent, boolean isShiny) {
        super(plugin, type);
        
        this.setRegistryName("inventory_button." + id);
        
        this.clickEvents = new ArrayList<>();
        this.clickEvents.add(defaultEvent);
        
        
        this.registryDataRoot.setBoolean(ItemTags.IS_STACK_IMMOVABLE.getKey(), true);
        this.registryDataRoot.setInt("buttonClicked", this.getLastClickEventId());
        
        
        ItemMeta meta = this.registryDataRoot.getProperMeta();
        
        if(isShiny) meta.addEnchant(Effects.GLOW, 0, true);
        
        this.registryDataRoot.setProperMeta(meta);
        
        
        this.setLocalizedName();
    }

    
    
    @Override
    public void onItemClicked(ItemStack stack, NBTTagCompound tag, InventoryClickEvent event) {
        plugin.getLogger().info("item clicked");
    }
    
    @Override
    public ItemStack createItemStack() {
        return super.createItemStack();
    }
    
    
    
    private int getLastClickEventId() {
        return this.clickEvents.size() - 1;
    }
    
}
