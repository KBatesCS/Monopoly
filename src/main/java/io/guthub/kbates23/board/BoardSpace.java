package io.guthub.kbates23.board;

import java.util.ArrayList;

public class BoardSpace {

    private String displayName;
    private int buyCost;
    private int houseCost;
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private Direction direction;
    private int locationOnRow;

    public BoardSpace(String name, Direction direction, int locationOnRow) {
        this.displayName = name;
        this.direction = direction;
        this.locationOnRow = locationOnRow;
    }

    public void addToSpace(Piece piece) {
        pieces.add(piece);

        //add code to perform new space action??
    }

    public boolean removeFromSpace(Piece piece) {
        return pieces.remove(piece);
    }

    private void updateBoard() {

    }

}
