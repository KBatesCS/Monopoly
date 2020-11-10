package io.guthub.kbates23.boardSpaces;

import io.guthub.kbates23.board.Direction;
import io.guthub.kbates23.board.Piece;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class RailRoadSpace extends BoardSpace {

    public RailRoadSpace (String name, Direction direction, int locationOnRow, Location originalLocation) {
        super(name, direction, locationOnRow, originalLocation);
    }

    @Override
    public void performSpaceAction(Piece piece) {

    }

}
