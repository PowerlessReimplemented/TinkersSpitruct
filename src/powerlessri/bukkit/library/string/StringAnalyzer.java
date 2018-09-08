package powerlessri.bukkit.library.string;

public class StringAnalyzer {
    
    private StringAnalyzer() {
    }
    
    public static char lastChar(String str) {
        return str.charAt( str.length() - 1 );
    }
    
    public static boolean equal(String str, char c) {
        return str.charAt(0) == c;
    }
    
    public static boolean equal(char c, String str) {
        return equal(str, c);
    }
    
}
