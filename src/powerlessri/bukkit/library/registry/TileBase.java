package powerlessri.bukkit.library.registry;

import powerlessri.bukkit.library.string.StringIdAllocator;

public abstract class TileBase implements IRegistryItem {

    protected static final StringIdAllocator idAllocator = new StringIdAllocator();

    private String registryName;

    public TileBase() {
        this.setRegistryName(idAllocator.next());
    }

    @Override
    public String getRegistryName() {
        return this.registryName;
    }

    protected void setRegistryName(String newName) {
        this.registryName = "tile." + newName;
    }
    
    

}
