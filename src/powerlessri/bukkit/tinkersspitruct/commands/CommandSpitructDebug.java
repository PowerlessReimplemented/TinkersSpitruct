package powerlessri.bukkit.tinkersspitruct.commands;

import java.util.function.BiConsumer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import powerlessri.bukkit.library.inventory.InventorySequence;
import powerlessri.bukkit.library.tags.TagHelper;
import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;
import powerlessri.bukkit.tinkersspitruct.tags.PluginTagHelper;

public class CommandSpitructDebug extends CommandBranchedBase {
    
    public CommandSpitructDebug(TinkersSpitruct plugin) {
        super(plugin, "spitruct");
        
        this.addOption("updateHand", (sender, args) -> {
            if(args.length > 0) {
                sender.sendMessage("Too much arguments! (expected 0, got " + args.length + ")");
                return;
            }
            
            if(sender instanceof Player) {
                Player player = (Player) sender;
                ItemStack hand = player.getInventory().getItemInMainHand();
                
                //TODO complete tool system
                if(PluginTagHelper.hasPluginTag(hand)) {
                    NBTTagCompound tag = PluginTagHelper.getPluginTag(hand);
                }
            }
        });
        
        this.addOption("handNbt", (sender, args) -> {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                ItemStack hand = player.getInventory().getItemInMainHand();
                NBTTagCompound tag = TagHelper.getStackTag(hand);
                
                player.sendMessage(
                        tag == null ? plugin.translate("command.spitruct.handNbt.errorNoTag") :
                                      tag.toString());
            }
        });
        
        this.addOption("inventoryTest1", (sender, args) -> {
            this.inventoryTest(sender, "toolBuilder.builder");
        });
        
        this.addOption("meta1", (sender, args) -> {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                
                player.getInventory().addItem(plugin.metaTest1);
            }
        });
        
    }
    
    private void inventoryTest(CommandSender sender, String key) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("trying to open inventory...");
            InventorySequence compound = plugin.toolBuilders.getPlayerOwnedInv(player.getUniqueId());
            
            compound.setCurrentInventory(key);
            Inventory inventory = compound.getInventory();
            
            if(inventory == null) {
                player.sendMessage("failed to open inventory");
                return;
            }
            
            player.openInventory(inventory);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 0) {
            return false;
        }
        
        String option = args[0];
        BiConsumer<CommandSender, String[]> excutor = this.options.get(option);
        
        if(excutor == null) {
            sender.sendMessage(
                    plugin.translate("command.error.optionDoesNotExist.front") +
                    option +
                    plugin.translate("command.error.optionDoesNotExist.back"));
        } else {
            excutor.accept(sender, args);
        }
        
        return true;
    }

}
