package io.guthub.kbates23.monopoly;

import io.guthub.kbates23.board.Board;
import io.guthub.kbates23.board.Piece;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    private Board gameBoard;
    private ArrayList<Piece> pieces;
    private int gameNum;
    private boolean gameStarted;
    private HashMap<Player, ItemStack[]> playerInventories;

    public Game(Location playerLocation, int gameNum) {
        gameBoard = new Board(playerLocation);
        this.gameNum = gameNum;
        this.pieces = new ArrayList<>();
        gameStarted = false;
    }

    public void startGame() {
        gameStarted = true;
        playerInventories = new HashMap<Player, ItemStack[]>();
        for (Piece piece: pieces) {
            piece.moveToSpace(0, gameBoard.getSpace(0));
            playerInventories.put(piece.getPlayer(), piece.getPlayer().getInventory().getContents());
            piece.getPlayer().getInventory().clear();
        }

    }

    public boolean addPiece(Player player, Material material) {
        if (gameStarted) {
            player.sendMessage("Game had already started");
            return false;
        }
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

    public void endGame() {
        for(Piece piece: pieces) {
            piece.getPlayer().getInventory().setContents(playerInventories.get(piece.getPlayer()));
        }
        gameStarted = false;
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
