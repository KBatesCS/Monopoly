package io.guthub.kbates23.boardSpaces;

import io.guthub.kbates23.board.Direction;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class HousingSpace extends BoardSpace{

    public HousingSpace (String name, Direction direction, int locationOnRow, Location originalLocation) {
        super(name, direction, locationOnRow, originalLocation);
    }

    public void performSpaceAction(Player player) {

    }
}
