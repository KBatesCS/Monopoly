package io.guthub.kbatesCS.boardSpaces;

import io.guthub.kbatesCS.board.Direction;
import io.guthub.kbatesCS.board.Piece;
import io.guthub.kbatesCS.monopoly.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class UtilitySpace extends BuyableBoardSpace {

    private int buyCost;
    private int mortgageValue;

    public UtilitySpace(String name, Direction direction, int locationOnRow, Location originalLocation) {
        super(name, direction, locationOnRow, originalLocation, 150, 75);
        buyCost = 150;
        mortgageValue = 75;
    }

    public int numOwned() {
        ArrayList<BoardSpace> essentials = GameManager.getBoardHash().get("essentials");
        int numOwned = 0;
        if (this.getOwner() == null) {
            return 0;
        }
        for (BoardSpace utility: essentials) {
            if ((((UtilitySpace) utility).getOwner() != null) && ((UtilitySpace) utility).getOwner().equals(this.getOwner())) {
                numOwned++;
            }
        }

        return numOwned;
    }

    @Override
    public ArrayList<String> getLore(Player player) {
        ArrayList<String> lore = new ArrayList<String>();

        String temp;
        Piece owner = getOwner();

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

        temp = "";
        if (owner == null) {
            temp += ChatColor.RED;
        } else if (numOwned() == 1) {
            temp += ChatColor.GREEN;
        } else {
            temp += ChatColor.RED;
        }
        temp += "rent without other utility: roll * 4";
        lore.add(temp);

        temp = "";
        if (owner == null) {
            temp += ChatColor.RED;
        } else if (numOwned() == 1) {
            temp += ChatColor.RED;
        } else {
            temp += ChatColor.GREEN;
        }
        temp += "rent with other utility: roll * 10";
        lore.add(temp);

        lore.add(ChatColor.BLUE + "Mortgage price: " + mortgageValue);


        return lore;
    }

    @Override
    public void performSpaceAction(Piece piece) {
        if ((this.getOwner() != null) && (!this.getOwner().getPlayer().equals(piece.getPlayer()))) {
            if (numOwned() == 1) {
                piece.charge(GameManager.getGame().getLastRoll() * 4);
            } else {
                piece.charge(GameManager.getGame().getLastRoll() * 10);
            }
        }
    }

}
