package de.jeff_media.chestsortapiexample;

import de.jeff_media.chestsort.api.ChestSortAPI;
import de.jeff_media.chestsort.api.ChestSortEvent;
import org.bukkit.Material;
import org.bukkit.block.Barrel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

/**
 * This class only gets instantiated when ChestSort is installed, which means:
 * We can happily import classes from ChestSortAPI!
 */
public class ChestSortIsDefinitelyInstalledListener implements Listener {

    /**
     * Let's sort the inventory of every player who joins.
     */
    @EventHandler
    public void sortInventoryOnJoin(PlayerJoinEvent event) {
        ChestSortAPI.sortInventory(event.getPlayer().getInventory());
        event.getPlayer().sendMessage("§aWelcome to our server. Your inventory has been sorted.");
    }

    /**
     * Pirates store their stuff in barrels. Pirates are messy. Let's prevent barrels from being sorted.
     */
    @EventHandler
    public void onBarrelSort(ChestSortEvent event) {
        if(event.getInventory().getHolder() instanceof Barrel) {
            System.out.println("Barrels are supposed to be messy!");
            event.setCancelled(true);
        }
    }

    /**
     * Prismarine Shards are very fragile. I don't want ChestSort to touch them.
     */
    @EventHandler
    public void onPrismarineSort(ChestSortEvent event) {
        for(ItemStack item : event.getInventory().getContents()) {
            if(item == null) continue;
            if(item.getType() == Material.PRISMARINE_SHARD) {
                System.out.println("Don't you dare to touch my prismarine shard!");
                event.setUnmovable(item);
            }
        }
    }
}
