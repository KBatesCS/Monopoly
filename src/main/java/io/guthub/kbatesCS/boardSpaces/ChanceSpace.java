package io.guthub.kbatesCS.boardSpaces;

import io.guthub.kbatesCS.board.Direction;
import io.guthub.kbatesCS.board.Piece;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Collections;

public class ChanceSpace extends BoardSpace {

    private int numCards;
    private ArrayList<String> cards;

    public ChanceSpace (String name, Direction direction, int locationOnRow, Location originalLocation) {
        super(name, direction, locationOnRow, originalLocation);
        initializeDeck();
        numCards = 52;
    }

    private void initializeDeck() {
        cards = new ArrayList<>();
        //add cards
        Collections.shuffle(cards);
    }

    @Override
    public void performSpaceAction(Piece piece) {

    }

}
