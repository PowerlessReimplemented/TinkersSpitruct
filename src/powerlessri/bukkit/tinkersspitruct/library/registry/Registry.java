package powerlessri.bukkit.tinkersspitruct.library.registry;

import java.util.HashMap;
import java.util.Map;

public class Registry<T extends IRegistryItem> {
    
    protected Map<String, T> registryNamespace;
    
    public Registry() {
        this.registryNamespace = new HashMap<>();
    }
    
    
    
    public void registerItem(T item) {
        if(this.registryNamespace.containsKey(item.getRegistryName())) {
            return;
        }
        
        this.registryNamespace.put(item.getRegistryName(), item);
    }
    
    public T getRegistryItem(String id) {
        return this.registryNamespace.get(id);
    }
    
    public boolean isRegistered(T item) {
        return this.isRegistered(item.getRegistryName());
    }
    
    public boolean isRegistered(String id) {
        return this.registryNamespace.containsKey(id);
    }
    
}
