package io.guthub.kbatesCS.boardSpaces;

import io.guthub.kbatesCS.board.Direction;
import io.guthub.kbatesCS.board.Piece;
import io.guthub.kbatesCS.monopoly.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class RailRoadSpace extends BuyableBoardSpace {

    private int buyCost;
    private int rent[];
    private int mortgageValue;

    public RailRoadSpace (String name, Direction direction, int locationOnRow, Location originalLocation) {
        super(name, direction, locationOnRow, originalLocation, 200, 100);
        buyCost = 200;
        rent = new int[4];
        rent[0] = 25;
        rent[1] = 50;
        rent[2] = 100;
        rent[3] = 200;
        mortgageValue = 100;
    }

    public int numOwned() {
        ArrayList<BoardSpace> railroads = GameManager.getBoardHash().get("railroad");
        int numOwned = 0;
        Piece owner = getOwner();
        if (owner == null) {
            return 0;
        }
        for (BoardSpace railroad: railroads) {
            if ((((RailRoadSpace) railroad).getOwner() != null) && ((RailRoadSpace) railroad).getOwner().equals(owner)) {
                numOwned++;
            }
        }
        return numOwned;
    }

    @Override
    public ArrayList<String> getLore(Player player) {
        Piece owner = getOwner();
        ArrayList<String> lore = new ArrayList<String>();

        String temp;

        temp = "Owner: ";
        if (owner == null) {
            temp += "none";
        } else {
            temp += owner.getPlayer().getDisplayName();
        }
        lore.add(temp);

        temp = "";
        if (owner == null) {
            temp += ChatColor.GREEN;
        } else {
            temp += ChatColor.RED;
        }
        temp += "Buy cost: " + buyCost;
        lore.add(temp);

        int rentNum = numOwned() - 1;

        for (int i = 0; i < 4; i++) {
            temp = "";
            if (rentNum == i) {
                temp += ChatColor.GREEN;
            } else {
                temp += ChatColor.RED;
            }

            temp += "rent: " + rent[i];
            lore.add(temp);
        }
        lore.add(ChatColor.BLUE + "Mortgage price: " + mortgageValue);

        return lore;
    }

    @Override
    public void performSpaceAction(Piece piece) {
        Piece owner = getOwner();
        if ((!isMortgaged()) && (owner != null) && (!owner.getPlayer().equals(piece.getPlayer()))) {
            piece.charge(rent[numOwned() - 1]);
        }
    }

}
