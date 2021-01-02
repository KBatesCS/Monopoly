package io.guthub.kbatesCS.boardSpaces;

import io.guthub.kbatesCS.board.Direction;
import io.guthub.kbatesCS.board.Piece;
import io.guthub.kbatesCS.monopoly.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class HousingSpace extends BoardSpace{

    private int[] rent;
    private int numHouses;
    private Piece owner;
    private int houseCost;
    private int buyCost;
    private int mortgageValue;

    public HousingSpace (String name, Direction direction, int locationOnRow, Location originalLocation, int[] costs) {
        super(name, direction, locationOnRow, originalLocation);
        rent = new int[6];
        for (int i = 0; i < 6; i++) {
            this.rent[i] = costs[i];
        }
        houseCost = costs[6];
        buyCost = costs[7];
        mortgageValue = costs[8];
        owner = null;
    }

    public void performSpaceAction(Piece piece) {
        if ((owner != null) && (!owner.getPlayer().equals(piece.getPlayer()))) {
            piece.charge(rent[numHouses]);
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

    public boolean addHouse(Player player) {
        System.out.println("adding house");
        if ((numHouses + 1) > 5) {
            return false;
        }
        if (owner == null) {
            return false;
        }
        if (owner.getMoney() < houseCost) {
            return false;
        }
        if (!owner.getPlayer().equals(player)) {
            return false;
        }

        if (!allColorsOwned()) {
            return false;
        }
        if (!canEvenlyAddHouse()) {
            return false;
        }
        owner.charge(houseCost);
        numHouses++;
        updateHouses();
        return true;
    }

    public boolean canEvenlyAddHouse() {
        HashMap<String, ArrayList<BoardSpace>> boardSpaces = GameManager.getBoardHash();

        for (HashMap.Entry mapElement : boardSpaces.entrySet()) {
            String key = (String)mapElement.getKey();

            ArrayList<BoardSpace> value = ((ArrayList<BoardSpace>) mapElement.getValue());
            if ((value.get(0) instanceof HousingSpace) && (value.contains(this))) {
                for (BoardSpace space: value) {
                    if (((HousingSpace) space).getNumHouses() < numHouses) {
                        return false;
                    }
                }
                return true;
            }

        }
        return false;
    }

    public int getNumHouses() {
        return numHouses;
    }

    public int mortgageProperty(int houses) {
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

    public ArrayList<String> getLore(Player player) {
        ArrayList<String> lore = new ArrayList<String>();

        lore.add("location: " + this.getLocationOnBoard());

        String temp;

        temp = "Owner: ";
        if (owner == null) {
            temp += "none";
        } else {
            temp += owner.getPlayer().getDisplayName();
        }
        lore.add(temp);

        if ((allColorsOwned()) && (player.equals(owner.getPlayer()))) {
            lore.add(ChatColor.DARK_AQUA + "Click to buy house");
        }

        temp = "";
        if (owner == null) {
            temp += ChatColor.GREEN;
        } else {
            temp += ChatColor.RED;
        }
        temp += "Buy cost: " + buyCost;
        lore.add(temp);

        for (int i = 0; i < 6; i++) {
            temp = "";
            if ((numHouses == i) && (owner != null)) {
                temp += ChatColor.GREEN;
            } else {
                temp += ChatColor.RED;
            }
            if (i < 5) {
                temp += "rent with " + i + " houses: " + rent[i];
            } else {
                temp += "rent with a hotel: " + rent[i];
            }
            lore.add(temp);
        }

        lore.add(ChatColor.BLUE + "House cost: " + houseCost);
        lore.add(ChatColor.BLUE + "Mortgage price: " + mortgageValue);

        return lore;
    }

    public Piece getOwner() {
        return owner;
    }

    public boolean allColorsOwned() {
        HashMap<String, ArrayList<BoardSpace>> boardSpaces = GameManager.getBoardHash();

        for (HashMap.Entry mapElement : boardSpaces.entrySet()) {
            String key = (String)mapElement.getKey();

            ArrayList<BoardSpace> value = ((ArrayList<BoardSpace>) mapElement.getValue());
            if ((value.get(0) instanceof HousingSpace) && (value.contains(this))) {
                if (((HousingSpace) value.get(0)).getOwner() == null) {
                    return false;
                }
                Player owner1 = ((HousingSpace) value.get(0)).getOwner().getPlayer();
                for (int i = 1; i < value.size(); i++) {
                    if (((HousingSpace) value.get(i)).getOwner() == null) {
                        return false;
                    }
                    if (!((HousingSpace) value.get(i)).getOwner().getPlayer().equals(owner1)) {
                        return false;
                    }
                }
                return true;
            }

        }
        return false;
    }
}
