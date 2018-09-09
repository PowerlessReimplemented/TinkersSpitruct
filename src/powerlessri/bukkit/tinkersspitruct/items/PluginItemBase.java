package powerlessri.bukkit.tinkersspitruct.items;

import powerlessri.bukkit.library.registry.ItemBase;
import powerlessri.bukkit.library.tags.TagHelper;
import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;

public abstract class PluginItemBase extends ItemBase {
    
    public PluginItemBase() {
        super();
        
        this.registryData.setTagCompound(TinkersSpitruct.PLUGIN_ID);
        this.registryData.cd(TinkersSpitruct.PLUGIN_ID);
    }

}
