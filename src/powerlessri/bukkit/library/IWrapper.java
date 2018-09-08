package powerlessri.bukkit.library;


// W stands for Wrapper, O stands for Original
public interface IWrapper<W, O> {
    
    O unwrap();
    
}
