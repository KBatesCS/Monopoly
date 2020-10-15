package io.guthub.kbates23.listeners;

import io.guthub.kbates23.monopoly.Game;
import io.guthub.kbates23.monopoly.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListener implements CommandExecutor {

    private final Main plugin;

    public CommandListener(Main plugin) {
        this.plugin = plugin;
    }

    /**
     *
     * @param sender
     * @param cmd
     * @param label
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if ((cmd.getName().equalsIgnoreCase("createGame")) && (sender instanceof Player)) {
            if ((args.length < 2) || (args.length > 6)) {
                System.out.println(ChatColor.RED + "ERROR: player count must be between 2 and 6");
                return false;
            }
            Game game = new Game(((Player) sender).getLocation());
            //check if all members in command are actually in server - not written for testing purposes

            return true;
        }
        return false;
    }

}
