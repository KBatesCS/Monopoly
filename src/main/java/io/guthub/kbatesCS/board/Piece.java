package io.guthub.kbatesCS.board;

import io.guthub.kbatesCS.boardSpaces.BoardSpace;
import io.guthub.kbatesCS.inventoryHandlers.ScoreboardHandler;
import io.guthub.kbatesCS.monopoly.GameManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Piece {
    private Material material;
    private Player player;
    private int money;
    private int currentLocation;
    private BoardSpace currentSpace;
    private boolean inJail;
    private int turnsInJail;

    public Piece(Material material, Player player) {
        this.material = material;
        this.player = player;
        money = 9999;
        currentLocation = 0;
        inJail = false;
        turnsInJail = 0;
    }

    public void charge(int charge) {
        money -= charge;
        ScoreboardHandler.updateScoreboard();
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public void sendToJail() {
        inJail = true;
        turnsInJail = 0;
        moveToSpace(10);
    }

    public boolean isInJail() {
        return inJail;
    }

    public int getTurnsInJail() {
        return turnsInJail;
    }

    public void addTurnInJail() {
        turnsInJail++;
    }

    public boolean getOutOfJail(int price) {
        if (!inJail) {
            return false;
        }
        this.charge(price);
        inJail = false;
        return true;
    }

    public void moveToSpace(int spaceLocation) {
        currentLocation = spaceLocation;
        if (currentSpace != null) {
            currentSpace.removeFromSpace(this);
        }
        BoardSpace newSpace = GameManager.getGame().getSpace(spaceLocation);
        newSpace.addToSpace(this);
        currentSpace = newSpace;
        newSpace.performSpaceAction(this);
    }

    public Material getMaterial() {
        return this.material;
    }

    public int getMoney() {
        return money;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Piece)) {
            return false;
        }
        Piece piece = (Piece) obj;
        //System.out.println("remember to add the piece.players equals"); //had to be taken out for testing
        return piece.getPlayer().equals(this.getPlayer());
    }

    @Override
    public String toString() {
        return "material: " + material.toString();
    }
}
