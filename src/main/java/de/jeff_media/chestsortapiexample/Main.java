package de.jeff_media.chestsortapiexample;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Demonstrates on how to use the ChestSortAPI.
 *
 * If you depend on ChestSort, you can directly use the methods provided by ChestSortAPI.
 *
 * If you only softdepend on ChestSort, you must check whether ChestSort is installed
 * before using the methods or importing classes. See ChestSortSoftDependExample for
 * an example.
 */
public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        if(Bukkit.getPluginManager().getPlugin("ChestSort") != null) {
            getLogger().info("Yay, ChestSort is installed.");

            // This listener gets only registered when ChestSort is definitely installed
            Bukkit.getPluginManager().registerEvents(new ChestSortIsDefinitelyInstalledListener(), this);
        }

        // This listener gets registered regardless of whether ChestSort is installed or not
        Bukkit.getPluginManager().registerEvents(new ChestSortMightBeInstalledListener(), this);
    }

}
