package io.guthub.kbates23.boardSpaces;

import io.guthub.kbates23.board.Direction;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FreeParkingSpace extends BoardSpace {

    public FreeParkingSpace (String name, Direction direction, int locationOnRow, Location originalLocation) {
        super(name, direction, locationOnRow, originalLocation);
    }

    @Override
    public void performSpaceAction(Player player) {

    }

}
