package powerlessri.bukkit.library.string;

import powerlessri.bukkit.library.Allocator;

public class StringIdAllocator extends Allocator {
    
    public static final char FIRST_PRINTABLE_ASCII = ' ';
    public static final char LAST_PRINTABLE_ASCII = '~';
    
    // The changing part
    private int suffix;
    // Previously generated id
    private String prefix;
    
    public StringIdAllocator() {
        
    }
    
    public String next() {
        this.suffix++;
        
        if(this.suffix == LAST_PRINTABLE_ASCII) {
            // +1 because we don't want ' ' be included in the id generated
            this.prefix += suffix;
            this.suffix = FIRST_PRINTABLE_ASCII + 1;
        }
        
        return this.prefix + (char) this.suffix;
    }
    
}
