package io.guthub.kbatesCS.boardSpaces;

import io.guthub.kbatesCS.board.Direction;
import io.guthub.kbatesCS.board.Piece;
import org.bukkit.Location;

public class GoSpace extends BoardSpace {

    public GoSpace (String name, Direction direction, int locationOnRow, Location originalLocation) {
        super(name, direction, locationOnRow, originalLocation);
    }

    @Override
    public void performSpaceAction(Piece piece) {

    }

}
