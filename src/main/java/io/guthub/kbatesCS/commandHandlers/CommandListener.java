package io.guthub.kbatesCS.commandHandlers;

import io.guthub.kbatesCS.monopoly.Game;
import io.guthub.kbatesCS.monopoly.GameManager;
import io.guthub.kbatesCS.monopoly.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListener implements CommandExecutor {

    private final Main plugin;
    private int gameNum;

    public CommandListener(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if ((cmd.getName().equalsIgnoreCase("createGame")) && (sender instanceof Player)) {
            if (args.length != 0) {
                return false;
            }
            if (!GameManager.createGame(((Player) sender).getLocation())) {
                plugin.getServer().broadcastMessage("Game of Monopoly has already been created, join with /join <block>");
                return false;
            }
            plugin.getServer().broadcastMessage("Game of Monopoly has been created");
            return true;
        } else if ((cmd.getName().equalsIgnoreCase("join")) && (sender instanceof Player)) {
            if (args.length != 1) {
                return false;
            }

            if (GameManager.playerInGame((Player) sender)) {
                ((Player) sender).sendMessage("already in game");
                return false;
            }

            Material material = Material.getMaterial(args[0]);
            if ((material == null) || (!material.isSolid())) {
                ((Player) sender).sendMessage(args[0] + " is not a valid block");
                return false;
            }

            if (GameManager.getGame().addPiece((Player) sender, material)) {
                ((Player) sender).sendMessage("successfully joined the game");
                return true;
            }
            return false;
        } else if (cmd.getName().equalsIgnoreCase("startGame")) {
            if (args.length != 0) {
                return false;
            }
            if (!GameManager.startGame()) {
                ((Player) sender).sendMessage("Game has already started");
                return false;
            }
            return true;
        } else if (cmd.getName().equalsIgnoreCase("endGame")) {
            if (args.length != 0) {
                return false;
            }
            if (GameManager.endGame()) {
                plugin.getServer().broadcastMessage("Game of Monopoly has been ended");
                return true;
            }
            return false;
        }
        return false;

    }

}
