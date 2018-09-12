package powerlessri.bukkit.library.registry;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import powerlessri.bukkit.library.events.IInventoryEventHandler;
import powerlessri.bukkit.library.string.StringIdAllocator;
import powerlessri.bukkit.library.tags.wrapper.ItemMetaWrapper;

public abstract class ItemBase implements IRegistryItem {
    
    public static final String TAG_REGISTRY_NAME = "id";

    protected static final StringIdAllocator idAllocator = new StringIdAllocator();



    protected Material type;
    protected String registryName;
    
    protected ItemMetaWrapper registryDataRoot;
    
    
    public ItemBase(Material type) {
        this.type = type;
        this.registryDataRoot = ItemMetaWrapper.wrap();
        
        this.setRegistryName(idAllocator.next());
    }

    @Override
    public String getRegistryName() {
        return this.registryName;
    }

    protected void setRegistryName(String newName) {
        this.registryName = newName;
        this.registryDataRoot.setString(TAG_REGISTRY_NAME, this.getRegistryName());
    }


    
    public ItemStack createItemStack() {
        ItemStack resultStack = new ItemStack(this.type);
        this.registryDataRoot.applyTraits(resultStack);
        
        return resultStack;
    }

}
