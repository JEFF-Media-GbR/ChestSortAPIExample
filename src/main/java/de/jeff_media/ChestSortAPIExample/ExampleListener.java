package de.jeff_media.ChestSortAPIExample;

import de.jeff_media.ChestSortAPI.ChestSortEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Listens to the ChestSortEvent to manipulate sorting attempts
 */
public class ExampleListener implements Listener {

    private final ExamplePlugin main;

    ExampleListener(ExamplePlugin main) {
        this.main=main;
    }

    /**
     * Display Debug information everytime a ChestSortEvent is called
     * @param event ChestSortEvent
     */
    @EventHandler
    public void onChestSortEvent(ChestSortEvent event) {
        main.getServer().broadcastMessage("\nCancellable ChestSortEvent invoked!");
        main.getServer().broadcastMessage("- Inventory: " + event.getInventory());
        if(event.getPlayer()!=null) main.getServer().broadcastMessage("- Player: " + event.getPlayer().getName());
        if(event.getLocation()!=null) main.getServer().broadcastMessage("- Location: " + event.getLocation());
        main.getServer().broadcastMessage("To avoid having this inventory sorted, simply cancel this event.");
    }

    /**
     * Prevents carrots from being sorted, but allows all other items to be sorted
     * @param event ChestSortEvent
     */
    @EventHandler
    public void preventCarrotsFromBeingMoved(ChestSortEvent event) {
        Inventory inventory = event.getInventory();
        for(ItemStack item : inventory.getContents()) {
            if(item != null && item.getType() == Material.CARROT) {
                event.setUnmovable(item);
                if(event.getPlayer() instanceof Player) {
                    ((Player) event.getPlayer()).sendMessage("We love Carrots so we refuse to move them.");
                }
            }
        }
    }

    /**
     * Sorts the player's inventory everytime he/she moves while sprinting
     * @param event ChestSortEvent
     */
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if(event.getPlayer().isSprinting()) {
            main.chestSortAPI.sortInventory(event.getPlayer().getInventory());
        }
    }
}
