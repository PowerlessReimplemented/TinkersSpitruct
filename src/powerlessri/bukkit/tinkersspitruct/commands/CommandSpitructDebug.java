package powerlessri.bukkit.tinkersspitruct.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.tinkersspitruct.Reference;
import powerlessri.bukkit.tinkersspitruct.library.helpers.tags.PluginTagHelper;
import powerlessri.bukkit.tinkersspitruct.library.helpers.tags.TagHelper;

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
                
                if(PluginTagHelper.hasPluginTag(hand)) {
                    NBTTagCompound tag = PluginTagHelper.getPluginTag(hand);
                    
//                    ToolDataHandler.updateToolData(tag);
                }
            }
        });
        
        this.addOption("handNbt", (sender, args) -> {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                ItemStack hand = player.getInventory().getItemInMainHand();
                player.sendMessage(TagHelper.getStackTag(hand).toString());
            }
        });
        this.addOption("inventoryTest1", (sender, args) -> {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("trying to open invenotry...");
                player.openInventory(Reference.getPlugin().testInventory);
            }
        });
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!label.equals("spitruct")) {
            return false;
        }
        
        if(this.hasOptionExcutor(args[0])) {
            this.getOptionExcutor(args[0]).accept(sender, args);
        }
        
        return true;
    }

}
