package io.guthub.kbatesCS.boardSpaces;

import io.guthub.kbatesCS.board.Direction;
import io.guthub.kbatesCS.board.Piece;
import io.guthub.kbatesCS.monopoly.GameManager;
import org.bukkit.Location;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RailRoadSpace extends BoardSpace {

    private int buyCost;
    private int rent[];
    private Piece owner;

    public RailRoadSpace (String name, Direction direction, int locationOnRow, Location originalLocation) {
        super(name, direction, locationOnRow, originalLocation);
        buyCost = 200;
        rent = new int[4];
        rent[0] = 25;
        rent[1] = 50;
        rent[2] = 100;
        rent[3] = 200;
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

    public int numOwned() {
        ArrayList<BoardSpace> railroads = GameManager.getBoardHash().get("railroad");
        int numOwned = 0;
        for (BoardSpace railroad: railroads) {
            if ((((RailRoadSpace) railroad).getOwner() != null) && ((RailRoadSpace) railroad).getOwner().equals(owner)) {
                numOwned++;
            }
        }
        return numOwned;
    }

    public Piece getOwner() {
        return owner;
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

        lore.add("temp: " + numOwned());

        return lore;
    }

    @Override
    public void performSpaceAction(Piece piece) {
        if (owner != null) {
            piece.charge(rent[numOwned() - 1]);
        }
    }

}
