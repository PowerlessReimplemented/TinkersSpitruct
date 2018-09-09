package powerlessri.bukkit.library.tags.wrapper;


// W stands for Wrapper, O stands for Original
public interface IWrapper<W, O> {
    
    O unwrap();
    
}
