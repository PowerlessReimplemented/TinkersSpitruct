package powerlessri.bukkit.tinkersspitruct.items;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import powerlessri.bukkit.library.Reference;
import powerlessri.bukkit.library.registry.ItemBase;
import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;

public abstract class PluginItemBase extends ItemBase {

    protected final TinkersSpitruct plugin;

    public PluginItemBase(TinkersSpitruct plugin, Material type) {
        super(type);

        this.plugin = plugin;

        this.registryDataRoot.setTagCompound(TinkersSpitruct.PLUGIN_ID);
        this.registryDataRoot.cd(TinkersSpitruct.PLUGIN_ID);
    }



    /**
     * Get the localized name in .lang file specified with registry name. <br />
     * Key should looks like {@code this: item.tinkersspitruct.SomeItemName.name}
     */
    public void setLocalizedName() {
        this.setLocalizedName(
                Reference.CATEGORY_ITEM + 
                TinkersSpitruct.PLUGIN_ID + ":" + this.getRegistryName() +
                Reference.ATTRIBUTE_NAME);
    }

    public void setLocalizedName(String langKey) {
        this.setDisplayName(plugin.translate(langKey));
    }

    public void setDisplayName(String name) {
        ItemMeta meta = this.registryDataRoot.getProperMeta();
        meta.setDisplayName(name);
        this.registryDataRoot.setProperMeta(meta);
    }
    
    
    @Override
    public void setRegistryName(String newName) {
        super.setRegistryName("item." + newName);
    }

}
