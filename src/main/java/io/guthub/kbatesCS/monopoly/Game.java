package io.guthub.kbatesCS.monopoly;

import io.guthub.kbatesCS.board.Board;
import io.guthub.kbatesCS.board.Piece;
import io.guthub.kbatesCS.inventoryHandlers.GameHotBarHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    private Board gameBoard;
    private ArrayList<Piece> pieces;
    private boolean gameStarted;
    private HashMap<Player, ItemStack[]> playerInventories;

    public Game(Location playerLocation) {
        gameBoard = new Board(playerLocation);
        this.pieces = new ArrayList<>();
        gameStarted = false;
    }

    public void startGame() {
        gameStarted = true;
        playerInventories = new HashMap<Player, ItemStack[]>();
        GameHotBarHandler hotBarHandler = new GameHotBarHandler();
        for (Piece piece: pieces) {
            piece.moveToSpace(0, gameBoard.getSpace(0));
            playerInventories.put(piece.getPlayer(), piece.getPlayer().getInventory().getContents());
            piece.getPlayer().getInventory().clear();
            hotBarHandler.setupHotBar(piece.getPlayer());
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

    public boolean hasStarted() {
        return gameStarted;
    }

    public boolean playerInGame(Player player) {
        for (Piece piece: pieces) {
            if (piece.getPlayer().equals(player)) {
                return true;
            }
        }
        return false;
    }
}
