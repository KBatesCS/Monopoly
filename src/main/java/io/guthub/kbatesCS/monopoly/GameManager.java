package io.guthub.kbatesCS.monopoly;

import io.guthub.kbatesCS.board.Piece;
import io.guthub.kbatesCS.boardSpaces.BoardSpace;
import io.guthub.kbatesCS.inventoryHandlers.ScoreboardHandler;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {

    private static Game game;

    static {
        game = null;
    }

    public static boolean createGame(Location location) {
        if (game != null) {
            return false;
        }
        game = new Game(location);
        return true;
    }

    public static boolean startGame() {
        if (game.hasStarted()) {
            return false;
        }
        game.startGame();
        ScoreboardHandler.updateScoreboard();

        return true;
    }

    public static HashMap<String, ArrayList<BoardSpace>> getBoardHash() {
        return game.getBoardHash();
    }

    public static boolean endGame() {
        if (game == null) {
            return false;
        }
        game.endGame();
        game = null;
        return true;
    }

    public static Game getGame() {
        return game;
    }

    public static boolean playerInGame(Player player) {
        if (game == null) {
            return false;
        }
        return game.playerInGame(player);
    }

    public static ArrayList<Piece> getPieces() {
        return game.getPieces();
    }

    public static boolean gameStarted() {
        return game.hasStarted();
    }

}
