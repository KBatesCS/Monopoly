package io.guthub.kbates23.listeners;

import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import io.guthub.kbates23.monopoly.Main;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

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

    private List<String> getAllMaterials() {
        List<String> list = new ArrayList<String>();
        for(Material mat : Material.values()) {
            list.add(mat.name());
        }

        return list;
    }

    private List<String> getContainedMaterials(String material) {
        List<String> list = new ArrayList<String>();
        for(String mat : getAllMaterials()) {
            if(mat.contains(material.toUpperCase())) {
                list.add(mat);
            }
        }
        return list;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String[] args) {
        if(cmd.getName().equalsIgnoreCase("join")) {
            if(args.length == 2) {
                List<String> list = getContainedMaterials(args[1]);
                return list;
            }
        }
        return null;
    }

}
