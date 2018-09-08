package powerlessri.bukkit.library;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import net.minecraft.server.v1_12_R1.BlockPosition;

public class PosHelper {
    
    private PosHelper() {
    }
    
    
    public static BlockPosition getBlockOnFace(BlockPosition original, BlockFace face) {
        return getBlockOnFace(original, face, 1);
    }
    
    // Not sure if there's better way to do it
    public static BlockPosition getBlockOnFace(BlockPosition original, BlockFace face, int offset) {
        switch(face) {
        case UP: return original.up(offset);
        case DOWN: return original.down(offset);
            
        case NORTH: return original.north(offset);
        case SOUTH: return original.south(offset);

        case EAST: return original.east(offset);
        case WEST: return original.west(offset);
        
        default: return original;
        }
    }
    
    public static BlockPosition fromLoc(Location loc) {
        return new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
    }
    
}
