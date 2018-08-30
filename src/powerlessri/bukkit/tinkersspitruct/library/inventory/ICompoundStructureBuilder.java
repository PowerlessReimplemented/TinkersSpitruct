package powerlessri.bukkit.tinkersspitruct.library.inventory;

public interface ICompoundStructureBuilder<D,T> {
    
    void add(D data);
    
    T build();
    
}
