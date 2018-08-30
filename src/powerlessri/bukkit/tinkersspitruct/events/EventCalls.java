package powerlessri.bukkit.tinkersspitruct.events;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class EventCalls {
    
    private List<Runnable> calls;
    
    public EventCalls() {
        this.calls = new ArrayList<Runnable>();
    }
    
    public int registerCall(Runnable call) {
        this.calls.add(call);
        return calls.size() - 1;
    }
    
    public void call(int id) {
        this.calls.get(id).run();
    }
    
}
