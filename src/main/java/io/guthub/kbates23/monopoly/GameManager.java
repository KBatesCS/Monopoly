package io.guthub.kbates23.monopoly;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class GameManager {

    private static ArrayList<Game> games;

    static {
        games = new ArrayList<>();
    }

    public static void startNewGame(Location location, int gameNum, Main plugin) {
        Game newGame = new Game(location, gameNum);
        newGame.runTaskTimer(plugin, 0L, 20L);
        games.add(newGame);
    }

    public static boolean endGame(int gameNum) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getGameNum() == gameNum) {
                games.get(i).cancel();
                games.remove(i);
                return true;
            }
        }
        return false;
    }

    public static Game getGame(int gameNum) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getGameNum() == gameNum) {
                return games.get(i);
            }
        }
        return null;
    }

    public static void endAllGames() {
        for (Game game: games) {
            game.cancel();
        }
        games = null;
    }

}
