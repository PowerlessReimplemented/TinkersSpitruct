package powerlessri.bukkit.tinkersspitruct.events;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_12_R1.BlockPosition;
import powerlessri.bukkit.tinkersspitruct.TinkersSpitruct;
import powerlessri.bukkit.tinkersspitruct.library.inventory.InventoryHelper;
import powerlessri.bukkit.tinkersspitruct.library.pos.PosHelper;

public class WorldInteractionHandler implements Listener {

    private final TinkersSpitruct plugin;

    public WorldInteractionHandler(TinkersSpitruct plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteraction(PlayerInteractEvent event) {
        Action action = event.getAction();

        switch(action) {
        case LEFT_CLICK_AIR:
            onPlayerHit(event);
            break;
        case LEFT_CLICK_BLOCK:
            onPlayerInteraction(event);
            break;
        case RIGHT_CLICK_AIR:
            onPlayerUseItem(event);
            break;
        case RIGHT_CLICK_BLOCK:
            onPlayerRightClick(event);
            break;

        default:
            break; 
        }
    }

    /** Right click in air */
    public void onPlayerUseItem(PlayerInteractEvent event) {

    }

    /** Right click on a block */
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();

        if(hand.isSimilar(InventoryHelper.EMPTY_STACK)) {
            this.onBlockInteraction(event, player, hand);
        } else {
            this.onBlockPlacement(event, player, hand);
        }
    }

    /** Left click in air */
    public void onPlayerSwing(PlayerInteractEvent event) {

    }

    /** Left click on a block */
    public void onPlayerHit(PlayerInteractEvent event) {

    }



    public void onBlockInteraction(PlayerInteractEvent event, Player player, ItemStack hand) {
        Block clicked = event.getClickedBlock();
        BlockFace faceClicked = event.getBlockFace();
        
        BlockPosition relative = PosHelper.getBlockOnFace(PosHelper.fromLoc(clicked.getLocation()), faceClicked);
        int x = relative.getX();
        int y = relative.getY();
        int z = relative.getZ();
        
        plugin.toolBuilders.handleBlockPlacement(event, x, y, z);
    }

    public void onBlockPlacement(PlayerInteractEvent event, Player player, ItemStack hand) {
    }

}
