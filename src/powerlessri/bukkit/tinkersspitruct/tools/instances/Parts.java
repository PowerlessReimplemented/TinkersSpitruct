package powerlessri.bukkit.tinkersspitruct.tools.instances;

import java.util.HashMap;
import java.util.Map;

import powerlessri.bukkit.tinkersspitruct.tools.parts.EnumPartMaterials;
import powerlessri.bukkit.tinkersspitruct.tools.parts.IPart;

public class Parts {
    
    public final Map<String, IPart> parts;
    
    public Parts() {
        this.parts = new HashMap<>();
    }
    
//    public <T extends IPart> void addPartType(Class<T> part) {
//        EnumPartMaterials[] materials = EnumPartMaterials.values();
//        for(int i = 0; i < materials.length; i++) {
//            EnumPartMaterials material = materials[i];
//            T materialedPart;
//            
//            try {
//                materialedPart = part.newInstance();
//            } catch(InstantiationException | IllegalAccessException e) {
//                e.printStackTrace();
//                return;
//            }
//            
//            materialedPart.setMaterial(material.material);
//            
//            this.parts.put(material.registryName + "_" + materialedPart.getIdName(), materialedPart);
//        }
//    }
    
    public void addPart(IPart part) {
        
    }
    
}
