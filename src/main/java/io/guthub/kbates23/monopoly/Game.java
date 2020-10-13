package io.guthub.kbates23.monopoly;

import org.bukkit.Location;

public class Game {

    private Board gameBoard;

    public Game(Location playerLocation) {
        gameBoard = new Board(playerLocation);
    }
}
