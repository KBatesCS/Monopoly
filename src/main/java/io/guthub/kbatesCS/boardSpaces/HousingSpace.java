package io.guthub.kbatesCS.boardSpaces;

import io.guthub.kbatesCS.board.Direction;
import io.guthub.kbatesCS.board.Piece;
import org.bukkit.Location;

public class HousingSpace extends BoardSpace{

    private int[] costs;
    private int numHouses;
    private Piece owner;

    public HousingSpace (String name, Direction direction, int locationOnRow, Location originalLocation, int[] costs) {
        super(name, direction, locationOnRow, originalLocation);
        this.costs = costs.clone();
        owner = null;
    }

    public void performSpaceAction(Piece piece) {
        if (owner == null) {
            //buy?
        } else {
            piece.charge(costs[numHouses]);
        }
    }

    public boolean addHouse(int newHouses) {
        if ((numHouses + newHouses) > 5) {
            return false;
        }
        numHouses += newHouses;
        updateHouses();
        return true;
    }

    public int mortgageHouse(int houses) {
        if ((numHouses - houses) < 0) {
            numHouses -= houses;

        }
        return 0;
    }

    private void updateHouses() {
        if (numHouses == 5) {
            //show hotel
        } else {
            //show houses
        }
    }

}
