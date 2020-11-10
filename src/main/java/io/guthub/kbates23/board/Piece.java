package io.guthub.kbates23.board;

import io.guthub.kbates23.boardSpaces.BoardSpace;
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
        money = 1500;
    }

    public void charge(int charge) {
        money -= charge;
    }

    public void moveToSpace(int spaceLocation, BoardSpace newSpace) {
        currentLocation = spaceLocation;
        currentSpace.removeFromSpace(this);
        newSpace.addToSpace(this);
    }

    public Material getMaterial() {
        return this.material;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Piece)) {
            return false;
        }
        Piece piece = (Piece) obj;
        System.out.println("remember to add the piece.players equals"); //had to be taken out for testing
        return ((piece.material.equals(this.material))
                && (piece.money == this.money) && (piece.currentSpace == this.currentSpace));
    }

    @Override
    public String toString() {
        return "material: " + material.toString();
    }
}
