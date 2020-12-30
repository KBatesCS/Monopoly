package io.guthub.kbates23.commandHandlers;

import io.guthub.kbates23.monopoly.Game;
import io.guthub.kbates23.monopoly.GameManager;
import io.guthub.kbates23.monopoly.Main;
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
        gameNum = 0;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if ((cmd.getName().equalsIgnoreCase("createGame")) && (sender instanceof Player)) {
            GameManager.startNewGame(((Player) sender).getLocation(), gameNum, plugin);
            plugin.getServer().broadcastMessage("Game number <" + gameNum + "> has been created");
            gameNum++;
            return true;
        } else if ((cmd.getName().equalsIgnoreCase("join")) && (sender instanceof Player)) {
            if (args.length != 2) {
                return false;
            }
            Game game;
            try {
                game = GameManager.getGame(Integer.parseInt(args[0]));
            } catch (NumberFormatException e) {
                ((Player) sender).sendMessage(args[0] + " is not a valid number");
                return false;
            }
            if (game == null) {
                ((Player) sender).sendMessage("Game Number <" + args[0] + "> does not exist");
                return false;
            }

            Material material = Material.getMaterial(args[1]);
            if ((material == null) || (!material.isSolid())) {
                ((Player) sender).sendMessage(args[1] + " is not a valid block");
                return false;
            }
            System.out.println(material.toString());
            if (game.addPiece((Player) sender, material)) {
                ((Player) sender).sendMessage("successfully joined game " + args[0]);
                return true;
            }
            return false;
        } else if (cmd.getName().equalsIgnoreCase("startGame")) {
            if (args.length != 1) {
                return false;
            }
            try {
                Game game = GameManager.getGame(Integer.parseInt(args[0]));
                if (game == null) {
                    ((Player) sender).sendMessage(args[0] + " is not a valid game");
                    return false;
                }
                game.startGame();
                plugin.getServer().broadcastMessage("Game number <" + args[0] + "> has started");
                return true;
            } catch (NumberFormatException e) {
                ((Player) sender).sendMessage(args[0] + " is not a valid number");
                return false;
            }
        } else if (cmd.getName().equalsIgnoreCase("endGame")) {
            if (args.length != 1) {
                return false;
            }
            try {
                Game game = GameManager.getGame(Integer.parseInt(args[0]));
                if (game == null) {
                    ((Player) sender).sendMessage(args[0] + " is not a valid game");
                    return false;
                }
                game.endGame();
                plugin.getServer().broadcastMessage("Game number <" + args[0] + "> has ended");
                return true;
            } catch (NumberFormatException e) {
                ((Player) sender).sendMessage(args[0] + " is not a valid number");
                return false;
            }
        }
        return false;

    }

}
