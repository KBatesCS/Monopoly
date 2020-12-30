package io.guthub.kbates23.commandHandlers;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import io.guthub.kbates23.monopoly.Main;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleter implements Listener, TabCompleter {

    public AutoCompleter(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private List<String> getAllMaterials() {
        List<String> list = new ArrayList<String>();
        for(Material mat : Material.values()) {
            if (mat.isSolid()) {
                list.add(mat.name());
            }
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

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command cmd, String s, String[] args) {
        if(cmd.getName().equalsIgnoreCase("join")) {
            if(args.length == 2) {
                List<String> list = getContainedMaterials(args[1]);
                return list;
            }
        }
        return null;
    }
}
