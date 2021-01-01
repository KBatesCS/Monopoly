package io.guthub.kbatesCS.boardSpaces;

import io.guthub.kbatesCS.board.Direction;
import io.guthub.kbatesCS.board.Piece;
import org.bukkit.Location;

import java.util.ArrayList;

public class HousingSpace extends BoardSpace{

    private int[] landOnPrices;
    private int numHouses;
    private Piece owner;
    private int buildingCost;
    private int buyCost;
    private int mortgageValue;

    public HousingSpace (String name, Direction direction, int locationOnRow, Location originalLocation, int[] costs) {
        super(name, direction, locationOnRow, originalLocation);
        landOnPrices = new int[6];
        for (int i = 0; i < 6; i++) {
            this.landOnPrices[i] = costs[i];
        }
        buildingCost = costs[6];
        buyCost = costs[7];
        mortgageValue = costs[8];
        owner = null;
    }

    public void performSpaceAction(Piece piece) {
        if (owner != null) {
            piece.charge(landOnPrices[numHouses]);
        }
    }

    public boolean buyProperty(Piece piece) {
        if ((piece.getMoney() < buyCost) || (owner != null)) {
            return false;
        }
        owner = piece;
        piece.charge(buyCost);
        this.updateOwnershipDisplay(owner);
        return true;
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

    public int getCost() {
        return buyCost;
    }

    public ArrayList<String> getLore() {
        ArrayList<String> lore = new ArrayList<String>();

        String temp;

        temp = "Owner: ";
        if (owner == null) {
            temp += "none";
        } else {
            temp += owner.getPlayer().getDisplayName();
        }

        lore.add(temp);

        return lore;
    }
}
