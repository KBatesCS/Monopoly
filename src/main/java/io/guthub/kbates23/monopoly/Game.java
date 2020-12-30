package io.guthub.kbates23.monopoly;

import io.guthub.kbates23.board.Board;
import io.guthub.kbates23.board.Piece;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Game extends BukkitRunnable {

    private Board gameBoard;
    private ArrayList<Piece> pieces;
    private int gameNum;

    public Game(Location playerLocation, int gameNum) {
        gameBoard = new Board(playerLocation);
        this.gameNum = gameNum;
        gameBoard.testSpaces();
        this.pieces = new ArrayList<>();
    }

    public boolean addPiece(Player player, Material material) {
        for (Piece piece: pieces) {
            if (piece.getMaterial().equals(material)) {
                player.sendMessage("Material is already in use");
                return false;
            } else if (piece.getPlayer().equals(player)) {
                player.sendMessage("You already are in this game");
                return false;
            }
        }
        pieces.add(new Piece(material, player));
        return true;
    }

    @Override
    public void run() {

    }

    private void endGame() {
        GameManager.endGame(this.gameNum);
    }

    public int getGameNum() {
        return gameNum;
    }

    @Override public boolean equals(Object o) {
        if (o instanceof Game) {
            return (((Game) o).gameNum == this.gameNum);
        }
        return false;
    }
}
