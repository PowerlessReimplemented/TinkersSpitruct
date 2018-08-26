package powerlessri.bukkit.tinkersspitruct.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.tinkersspitruct.library.helpers.tags.PluginTagHelper;

public class CommandSpitructDebug extends CommandBranchedBase {
    
    public CommandSpitructDebug() {
        super();
        
        this.addOption("updateHand", (sender, args) -> {
            if(args.length > 0) {
                sender.sendMessage("Too much arguments! (expected 0, got " + args.length + ")");
                return;
            }
            
            if(sender instanceof Player) {
                Player player = (Player) sender;
                ItemStack hand = player.getInventory().getItemInMainHand();
                NBTTagCompound tag = PluginTagHelper.getPluginTag(hand);
            }
        });
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return false;
    }

}
