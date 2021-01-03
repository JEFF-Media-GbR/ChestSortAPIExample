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

/**
 * Example plugin to show how to use the ChestSortAPI.
 */
public class ExamplePlugin extends JavaPlugin implements Listener, CommandExecutor {

	// We will set this in onEnable(). You could also call ChestSort#getAPI() everytime
	ChestSortAPI chestSortAPI;
	
	public void onEnable() {

		// Get the ChestSort plugin instance
		ChestSort chestSort = (ChestSort) getServer().getPluginManager().getPlugin("ChestSort");

		// ChestSort is not installed
		if(chestSort==null) {
			getLogger().severe("Error: ChestSort is not installed.");
			return;
		}

		// Get the ChestSortAPI
		chestSortAPI = chestSort.getAPI();
		
		getServer().getPluginManager().registerEvents(new ExampleListener(this), this);
		getCommand("chestsorttest").setExecutor(new ExampleCommand(this));
	}

}
