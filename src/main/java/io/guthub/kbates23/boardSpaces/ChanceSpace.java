package io.guthub.kbates23.boardSpaces;

import io.guthub.kbates23.board.Direction;
import io.guthub.kbates23.board.Piece;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
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
