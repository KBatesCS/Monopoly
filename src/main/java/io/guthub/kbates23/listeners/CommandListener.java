package io.guthub.kbates23.listeners;

import io.guthub.kbates23.monopoly.Game;
import io.guthub.kbates23.monopoly.GameManager;
import io.guthub.kbates23.monopoly.Main;
import org.bukkit.ChatColor;
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
        }
        return false;
    }

}
