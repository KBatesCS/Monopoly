package io.guthub.kbates23.listeners;

import io.guthub.kbates23.monopoly.Game;
import io.guthub.kbates23.monopoly.GameManager;
import io.guthub.kbates23.monopoly.Main;
import org.bukkit.ChatColor;
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
            plugin.getServer().broadcastMessage("Game number <" + gameNum + "> has started");
            gameNum++;
            return true;
        } else if ((cmd.getName().equalsIgnoreCase("join")) && (sender instanceof Player)) {
            try {
                Game game = GameManager.getGame(Integer.parseInt(args[0]));
                if (game == null) {
                    ((Player) sender).sendMessage("Game Number <" + args[0] + "> does not exist");
                }
                Material material = Material.getMaterial(args[1]);
                return game.addPiece((Player) sender, material);
            } catch (Exception e) {
                ((Player) sender).sendMessage("invalid format (join <game number> <material type>)");
                return false;
            }
        }
        return false;
    }

}
