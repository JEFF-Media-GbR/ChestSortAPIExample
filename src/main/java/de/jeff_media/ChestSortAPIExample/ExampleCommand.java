package de.jeff_media.ChestSortAPIExample;

import de.jeff_media.ChestSortAPI.Sortable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ExampleCommand implements CommandExecutor {

    private final ExamplePlugin main;

    public ExampleCommand(ExamplePlugin main) {
        this.main=main;
    }

    /**
     * Displays a custom inventory when running /chestsorttest that can be sorted by using an instance of the Sortable class as InventoryHolder
     * @param sender CommandSender
     * @param command Command
     * @param label Command label
     * @param args Command arguments
     * @return
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("You must be player to run this command.");
            return true;
        }

        Player p = (Player) sender;

        // Use instance of Sortable as InventoryHolder
        Inventory sortableInventory = Bukkit.createInventory(new Sortable(), 9,"Sort me!");
        sortableInventory.setItem(3, new ItemStack(Material.DIRT,2));
        sortableInventory.setItem(6, new ItemStack(Material.DIRT,1));
        sortableInventory.setItem(8, new ItemStack(Material.BEDROCK,1));
        p.sendMessage("If you have ChestSort hotkeys enabled, you can use them now in this custom inventory.");
        p.openInventory(sortableInventory);

        return true;
    }
}
