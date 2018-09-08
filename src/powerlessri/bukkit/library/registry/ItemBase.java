package powerlessri.bukkit.library.registry;

import org.bukkit.inventory.ItemStack;

import powerlessri.bukkit.library.events.IInventoryEventHandler;
import powerlessri.bukkit.library.string.StringIdAllocator;

public abstract class ItemBase
        implements IRegistryItem, IInventoryEventHandler {

    protected static final StringIdAllocator idAllocator = new StringIdAllocator();

    private String registryName;

    public ItemBase() {
        this.setRegistryName(idAllocator.next());
    }

    @Override
    public String getRegistryName() {
        return this.registryName;
    }

    protected void setRegistryName(String newName) {
        this.registryName = "item." + newName;
    }
    
    
    public ItemStack createItemStack() {
        return null;
    }

}
