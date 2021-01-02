package io.guthub.kbatesCS.board;

import io.guthub.kbatesCS.boardSpaces.BoardSpace;
import io.guthub.kbatesCS.inventoryHandlers.ScoreboardHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Piece {
    private Material material;
    Player player;
    private int money;
    private int currentLocation;
    private BoardSpace currentSpace;

    public Piece(Material material, Player player) {
        this.material = material;
        this.player = player;
        currentLocation = 0;
        money = 9999;
        currentLocation = 0;
    }

    public void charge(int charge) {
        money -= charge;
        ScoreboardHandler.updateScoreboard();
    }

    public int getCurrentLocation() {
        return currentLocation;
    }

    public void moveToSpace(int spaceLocation, BoardSpace newSpace) {
        currentLocation = spaceLocation;
        if (currentSpace != null) {
            currentSpace.removeFromSpace(this);
        }
        newSpace.addToSpace(this);
        newSpace.performSpaceAction(this);
        currentSpace = newSpace;
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
