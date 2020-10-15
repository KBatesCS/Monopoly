package io.guthub.kbates23.board;

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

    public void moveToSpace(int spaceLocation, BoardSpace newSpace) {
        currentLocation = spaceLocation;
        currentSpace.removeFromSpace(this);
        newSpace.addToSpace(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Piece)) {
            return false;
        }
        Piece piece = (Piece) obj;
        return ((piece.material.equals(this.material)) && (piece.player.equals(this.player))
                && (piece.money == this.money) && (piece.currentSpace == this.currentSpace));
    }
}
