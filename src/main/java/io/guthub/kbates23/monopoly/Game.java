package io.guthub.kbates23.monopoly;

import io.guthub.kbates23.board.Board;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public class Game extends BukkitRunnable {

    private Board gameBoard;
    private int gameNum;

    public Game(Location playerLocation, int gameNum) {
        gameBoard = new Board(playerLocation);
        this.gameNum = gameNum;
        gameBoard.testSpaces();
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
