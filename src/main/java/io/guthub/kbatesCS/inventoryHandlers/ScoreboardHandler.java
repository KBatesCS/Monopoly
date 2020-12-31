package io.guthub.kbatesCS.inventoryHandlers;

import io.guthub.kbatesCS.board.Piece;
import io.guthub.kbatesCS.monopoly.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;

public class ScoreboardHandler {

    private static ArrayList<Piece> pieces;

    static {

    }

    public static void updatePieces() {
        pieces = GameManager.getPieces();
    }

    public static void updateScoreboard() {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = board.registerNewObjective("Money", "", "");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.GREEN + "Money");

        for (Piece piece: pieces) {
            Score score = objective.getScore(piece.getPlayer().getDisplayName() + ": " +  piece.getMoney());
            score.setScore(1);
        }

        for (Piece piece: pieces) {
            piece.getPlayer().setScoreboard(board);
        }
    }
}
