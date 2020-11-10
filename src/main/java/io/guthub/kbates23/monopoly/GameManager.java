package io.guthub.kbates23.monopoly;

import org.bukkit.Location;

import java.util.ArrayList;

public class GameManager {

    private static ArrayList<Game> games;

    static {
        games = new ArrayList<>();
    }

    public static Game startNewGame(Location location, int gameNum) {
        games.add(new Game(location, gameNum));
        return games.get(games.size() - 1);
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

    public static void endAllGames() {
        for (Game game: games) {
            game.cancel();
        }
        games = null;
    }

}
