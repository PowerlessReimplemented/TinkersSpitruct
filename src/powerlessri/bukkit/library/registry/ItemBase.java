package powerlessri.bukkit.library.registry;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import powerlessri.bukkit.library.events.IInventoryEventHandler;
import powerlessri.bukkit.library.string.StringIdAllocator;
import powerlessri.bukkit.library.tags.wrapper.ItemMetaWrapper;

public abstract class ItemBase implements IRegistryItem, IInventoryEventHandler {
    
    public static final String TAG_REGISTRY_NAME = "id";

    protected static final StringIdAllocator idAllocator = new StringIdAllocator();



    protected String registryName;
    protected ItemMetaWrapper registryData;
    
    protected ItemStack baseStack;

    public ItemBase() {
        this.registryData = ItemMetaWrapper.wrap();
        
        this.setRegistryName(idAllocator.next());
        
        this.baseStack = new ItemStack(Material.AIR);
        this.registryData.applyTraits(this.baseStack);
    }

    @Override
    public String getRegistryName() {
        return this.registryName;
    }

    protected void setRegistryName(String newName) {
        this.registryName = "item." + newName;
        this.registryData.setString(TAG_REGISTRY_NAME, this.getRegistryName());
    }


    public ItemStack createItemStack() {
        return new ItemStack(baseStack);
    }

}
