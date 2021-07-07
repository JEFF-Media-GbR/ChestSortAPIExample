package de.jeff_media.chestsortapiexample;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class gets instantiated whether ChestSort is installed or not, which means:
 * We must not import classes from ChestSortAPI, instead we use qualified method
 * calls inside if statements.
 */
public class ChestSortMightBeInstalledListener implements Listener {

    private final boolean isChestSortInstalled;
    private final Random random = new Random();

    public ChestSortMightBeInstalledListener() {
        isChestSortInstalled = Bukkit.getPluginManager().getPlugin("ChestSort") != null;
    }

    /**
     * Don't ask me why, but: When the player rightclicks a grass block, let's reward them
     * by opening an Inventory that's filled with random stuff. We also tell ChestSort
     * that this custom inventory should be sortable - but only if ChestSort is installed.
     */
    @EventHandler
    public void giveRewardForRightClickGrass(PlayerInteractEvent event) {
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if(event.getClickedBlock().getType() != Material.GRASS_BLOCK) return;
        Inventory inventory = Bukkit.createInventory(null, 54, "§6Sort me!");
        for(int i = 0; i < 20; i++) {
            Material randomMaterial = Material.values()[random.nextInt(Material.values().length)];
            int randomAmount = random.nextInt(randomMaterial.getMaxStackSize())+1;
            int randomSlot = random.nextInt(54);
            inventory.setItem(randomSlot, new ItemStack(randomMaterial, randomAmount));
        }
        /* Mark the Inventory as sortable. We are using qualified method names inside an if statement
         * to avoid Exceptions being thrown when ChestSort is not installed
         */
        if(isChestSortInstalled) {
            event.getPlayer().sendMessage("This is kinda messy - just use ChestSort's hotkeys to sort your rewards.");
            de.jeff_media.chestsort.api.ChestSortAPI.setSortable(inventory);
        }

        event.getPlayer().openInventory(inventory);
    }
}
