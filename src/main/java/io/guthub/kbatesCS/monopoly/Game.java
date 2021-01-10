package io.guthub.kbatesCS.monopoly;

import io.guthub.kbatesCS.board.Board;
import io.guthub.kbatesCS.board.Piece;
import io.guthub.kbatesCS.boardSpaces.BoardSpace;
import io.guthub.kbatesCS.boardSpaces.UtilitySpace;
import io.guthub.kbatesCS.boardSpaces.HousingSpace;
import io.guthub.kbatesCS.boardSpaces.RailRoadSpace;
import io.guthub.kbatesCS.inventoryHandlers.GameHotBarHandler;
import io.guthub.kbatesCS.inventoryHandlers.ScoreboardHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Game {

    private Board gameBoard;
    private ArrayList<Piece> pieces;
    private boolean gameStarted;
    private HashMap<Player, ItemStack[]> playerInventories;
    GameHotBarHandler hotBarHandler;

    //game running code
    private boolean diceRolled;
    private int numRolls;
    private int lastRoll;


    public Game(Location playerLocation) {
        gameBoard = new Board(playerLocation);
        this.pieces = new ArrayList<>();
        gameStarted = false;
        diceRolled = false;
        numRolls = 0;
    }

    public boolean startNewTurn() {
        if (!diceRolled) {
            return false;
        }
        diceRolled = false;
        numRolls = 0;
        pieces.add(pieces.remove(0));
        Bukkit.getServer().broadcastMessage(pieces.get(0).getPlayer().getDisplayName() + "'s turn");
        return true;
    }

    public int getLastRoll() {
        return lastRoll;
    }

    public boolean rollDice() {
        if (diceRolled) {
            return false;
        }
        numRolls++;
        Piece currentPiece = pieces.get(0);
        int roll1 = (int) (Math.random() * 6 + 1);
        int roll2 = (int) (Math.random() * 6 + 1);
        lastRoll = roll1 + roll2;
        Bukkit.getServer().broadcastMessage(" Rolled <" + roll1 + "> and <" + roll2 + ">, moving "
                + (roll1 + roll2) + " spaces");
        if (currentPiece.isInJail()) {
            if (currentPiece.getTurnsInJail() == 2) {
                currentPiece.getOutOfJail(50);
            } else {
                if (roll1 == roll2) {
                    currentPiece.moveToSpace(10 + lastRoll);
                    currentPiece.getOutOfJail(0);
                } else {
                    currentPiece.addTurnInJail();
                }
                diceRolled = true;
                return true;
            }
        }
        if ((roll1 == roll2) && (numRolls == 3)) {
            currentPiece.sendToJail();
            return true;
        } else if (roll1 != roll2) {
            diceRolled = true;
        }

        int currentLocation = currentPiece.getCurrentLocation();
        if ((currentLocation + roll1 + roll2) >= 40) {
            currentPiece.charge(-200);
        }
        currentLocation = (currentLocation + roll1 + roll2) % 40;

        currentPiece.moveToSpace(currentLocation);
        if (currentPiece.isInJail()) {
            diceRolled = true;
        }
        return true;
    }

    public Player currentPlayer() {
        return pieces.get(0).getPlayer();
    }

    public boolean getCurrentPlayerOutOfJail(int price) {
        return pieces.get(0).getOutOfJail(price);
    }

    public boolean buySpace() {
        if (numRolls == 0) {
            return false;
        }
        BoardSpace currentSpace = gameBoard.getSpace(pieces.get(0).getCurrentLocation());
        if (currentSpace instanceof HousingSpace) {
            return ((HousingSpace) currentSpace).buyProperty(pieces.get(0));
        } else if (currentSpace instanceof RailRoadSpace) {
            return ((RailRoadSpace) currentSpace).buyProperty(pieces.get(0));
        } else if (currentSpace instanceof UtilitySpace) {
            return ((UtilitySpace) currentSpace).buyProperty(pieces.get(0));
        }
        return false;

    }

    public void startGame() {
        gameStarted = true;
        playerInventories = new HashMap<Player, ItemStack[]>();
        hotBarHandler = new GameHotBarHandler();
        for (Piece piece: pieces) {
            piece.moveToSpace(0);
            playerInventories.put(piece.getPlayer(), piece.getPlayer().getInventory().getContents());
            piece.getPlayer().getInventory().clear();
            hotBarHandler.setupHotBar(piece.getPlayer());
        }

        Collections.shuffle(pieces);
        ScoreboardHandler.updatePieces();
    }

    public HashMap<String, ArrayList<BoardSpace>> getBoardHash() {
        return gameBoard.getPropertyDivision();
    }

    public GameHotBarHandler getHotBarHandler() {
        return hotBarHandler;
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

    public void leaveGame(Piece piece) {
        piece.getPlayer().getInventory().setContents(playerInventories.get(piece.getPlayer()));
        BoardSpace space = gameBoard.getSpace(piece.getCurrentLocation());
        space.removeFromSpace(piece);
        pieces.remove(piece);
    }

    public void endGame() {
        for (Piece piece: pieces) {
            piece.getPlayer().getInventory().setContents(playerInventories.get(piece.getPlayer()));
        }
        for (int i = 0; i < 40; i++) {
            BoardSpace space = gameBoard.getSpace(i);
            if (space instanceof HousingSpace) {
                ((HousingSpace) space).clearHouseSpace();
            }
            space.clearPieces();
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

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public BoardSpace getSpace(int space) {
        return gameBoard.getSpace(space);
    }
}
