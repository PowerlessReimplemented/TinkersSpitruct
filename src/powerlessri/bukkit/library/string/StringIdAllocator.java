package powerlessri.bukkit.library.string;

import powerlessri.bukkit.library.Allocator;

public class StringIdAllocator extends Allocator {

    public static final char FIRST_PRINTABLE_ASCII = ' ';
    public static final char LAST_PRINTABLE_ASCII = '~';

    // The changing part
    private char suffix;
    // Previously generated id, append suffix on generation
    private String previousIdPart;

    public StringIdAllocator() {
        // Stands for 'Auto Generated ID'
        this.previousIdPart = "agid:";
        this.suffix = FIRST_PRINTABLE_ASCII + 1;
    }

    public String next() {
        this.suffix++;

        if(this.suffix == LAST_PRINTABLE_ASCII) {
            this.previousIdPart += suffix;
            // +1 because we don't want ' ' be included in the id generated
            this.suffix = FIRST_PRINTABLE_ASCII + 1;
        }

        return this.previousIdPart + this.suffix;
    }

}
