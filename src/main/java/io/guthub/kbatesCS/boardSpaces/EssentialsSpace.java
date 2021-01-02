package io.guthub.kbatesCS.boardSpaces;

import io.guthub.kbatesCS.board.Direction;
import io.guthub.kbatesCS.board.Piece;
import io.guthub.kbatesCS.monopoly.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.ArrayList;

public class EssentialsSpace extends BoardSpace {

    private Piece owner;
    private int buyCost;
    private int mortgageValue;

    public EssentialsSpace (String name, Direction direction, int locationOnRow, Location originalLocation) {
        super(name, direction, locationOnRow, originalLocation);
        owner = null;
        buyCost = 150;
        mortgageValue = 75;
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
        ArrayList<BoardSpace> essentials = GameManager.getBoardHash().get("essentials");
        int numOwned = 0;
        if (owner == null) {
            return 0;
        }
        for (BoardSpace utility: essentials) {
            if ((((EssentialsSpace) utility).getOwner() != null) && ((EssentialsSpace) utility).getOwner().equals(owner)) {
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
        if ((owner != null) && (!owner.getPlayer().equals(piece.getPlayer()))) {
            if (numOwned() == 1) {
                piece.charge(GameManager.getGame().getLastRoll() * 4);
            } else {
                piece.charge(GameManager.getGame().getLastRoll() * 10);
            }
        }
    }

}
