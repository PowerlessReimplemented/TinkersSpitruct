package powerlessri.bukkit.tinkersspitruct.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;

public abstract class CommandBranchedBase extends CommandBase {
    
    protected final TinkersSpitruct plugin;
    
    protected final String name;
    protected final Map<String, BiConsumer<CommandSender, String[]>> options;
    
    public CommandBranchedBase(TinkersSpitruct plugin) {
        this(plugin, "");
    }
    
    public CommandBranchedBase(TinkersSpitruct plugin, String name) {
        this.plugin = plugin;
        
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

}
