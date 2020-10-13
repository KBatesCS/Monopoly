package io.guthub.kbates23.listeners;

import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import io.guthub.kbates23.monopoly.Main;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventListener implements Listener {

    public EventListener(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

//    simple example plugin
//    @EventHandler
//    public void onBlockTouch(PlayerInteractEvent event) {
//        Block block = event.getClickedBlock();
//        if (block != null) {
//            block.setType(Material.DIAMOND_BLOCK);
//        }
//    }

}
