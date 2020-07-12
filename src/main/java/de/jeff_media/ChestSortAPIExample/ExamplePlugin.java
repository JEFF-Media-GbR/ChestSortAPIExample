package de.jeff_media.ChestSortAPIExample;

import de.jeff_media.ChestSortAPI.ChestSort;
import de.jeff_media.ChestSortAPI.ChestSortAPI;
import de.jeff_media.ChestSortAPI.ChestSortEvent;
import de.jeff_media.ChestSortAPI.Sortable;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin implements Listener, CommandExecutor {
	
	ChestSortAPI chestSortAPI;
	
	public void onEnable() {
		
		ChestSort chestSort = (ChestSort) getServer().getPluginManager().getPlugin("ChestSort");
		if(chestSort==null) {
			getLogger().severe("Error: ChestSort is not installed.");
			return;
		}
		
		chestSortAPI = chestSort.getAPI();
		
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("chestsorttest").setExecutor(this);
	}
	
	@EventHandler
	public void onChestSortEvent(ChestSortEvent event) {
		getServer().broadcastMessage("\nCancellable ChestSortEvent invoked!");
		getServer().broadcastMessage("- Inventory: " + event.getInventory());
		if(event.getPlayer()!=null) getServer().broadcastMessage("- Player: " + event.getPlayer().getName());
		if(event.getLocation()!=null) getServer().broadcastMessage("- Location: " + event.getLocation());
		getServer().broadcastMessage("To avoid having this inventory sorted, simply cancel this event.");
	}
	
	// Sort player inventory every time he moves.
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		chestSortAPI.sortInventory(event.getPlayer().getInventory());
	}

	// Displays a custom inventory on command "/chestsorttest" to the player that can be sorted with hotkeys
	public boolean onCommand​(CommandSender sender, Command command, String label, String[] args) {

		if(!(sender instanceof Player)) {
			sender.sendMessage("You must be player to run this command.");
			return true;
		}

		Player p = (Player) sender;

		Inventory sortableInventory = Bukkit.createInventory(new Sortable(), 9,"Sort me!");
		p.sendMessage("If you have ChestSort hotkeys enabled, you can use them now in this custom inventory.");
		p.openInventory(sortableInventory);

		return true;
	}

}
