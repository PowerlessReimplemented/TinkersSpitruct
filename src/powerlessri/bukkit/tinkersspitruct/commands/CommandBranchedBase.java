package powerlessri.bukkit.tinkersspitruct.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.annotation.Nullable;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class CommandBranchedBase extends CommandBase {
    
    private final String name;
    private final Map<String, BiConsumer<CommandSender, String[]>> options;
    
    public CommandBranchedBase() {
        this("");
    }
    
    public CommandBranchedBase(String name) {
        this.name = name;
        this.options = new HashMap<String, BiConsumer<CommandSender, String[]>>();
    }

    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return false;
    }
    
    
    
    protected void addOption(String label, BiConsumer<CommandSender, String[]> lambd) {
        if(!this.options.containsKey(label)) {
            this.options.put(label, lambd);
        }
    }
    
    @Nullable
    protected BiConsumer<CommandSender, String[]> getOptionExcutor(String label) {
        if(this.hasOptionExcutor(label)) {
            return this.options.get(label);
        }
        return null;
    }
    
    protected boolean hasOptionExcutor(String label) {
        return this.options.containsKey(label);
    }

}
