package powerlessri.bukkit.tinkersspitruct.library.items;

import powerlessri.bukkit.tinkersspitruct.library.registry.IRegistryItem;

public class ItemBase implements IRegistryItem {
    
    public String id;

    @Override
    public String getRegistryName() {
        return this.id;
    }

}
