package io.guthub.kbatesCS.boardSpaces;

import io.guthub.kbatesCS.board.Direction;
import io.guthub.kbatesCS.board.Piece;
import io.guthub.kbatesCS.monopoly.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class HousingSpace extends BuyableBoardSpace {

    private int[] rent;
    private int numHouses;
    private int houseCost;
    private int buyCost;
    private int mortgageValue;

    public HousingSpace (String name, Direction direction, int locationOnRow, Location originalLocation, int[] costs) {
        super(name, direction, locationOnRow, originalLocation, costs[7], costs[8]);
        rent = new int[6];
        for (int i = 0; i < 6; i++) {
            this.rent[i] = costs[i];
        }
        houseCost = costs[6];
        buyCost = costs[7];
        mortgageValue = costs[8];
    }

    public boolean addHouse(Player player) {
        System.out.println("adding house");
        if ((numHouses + 1) > 5) {
            return false;
        }
        Piece owner = getOwner();
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

        if (numHouses == 5) {
            return false;
        }
        for (HashMap.Entry mapElement : boardSpaces.entrySet()) {
            ArrayList<BoardSpace> value = ((ArrayList<BoardSpace>) mapElement.getValue());
            if ((value.get(0) instanceof HousingSpace) && (value.contains(this))) {
                for (BoardSpace space: value) {
                    if (((HousingSpace) space).getNumHouses() < numHouses) {
                        return false;
                    }
                    if (((HousingSpace) space).isMortgaged()) {
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

    private void updateHouses() {
        int cornerX = this.getCornerX();
        int cornerZ = this.getCornerZ();
        int xDir = getDirection().xDir;
        int zDir = getDirection().zDir;
        if (numHouses == 5) {
            clearHouseSpace();
            if ((this.getDirection() == Direction.NORTH) || (this.getDirection() == Direction.SOUTH)) {
                cornerZ += 11 * zDir;
                cornerX += 2 * xDir;
                for (int x = 0; x < 6; x++) {
                    for (int y = 1; y < 4; y++) {
                        for (int z = 0; z < 2; z++) {
                            if (!(((x == 0) || (x == 5)) && (y == 3))) {
                                getWorld().getBlockAt(cornerX + x * xDir, getOriginalLocation().getBlockY() + y, cornerZ + z * zDir).setType(Material.RED_CONCRETE);
                            }
                        }
                    }
                }
            } else {
                cornerZ += 2 * zDir;
                cornerX += 11 * xDir;
                for (int x = 0; x < 2; x++) {
                    for (int y = 1; y < 4; y++) {
                        for (int z = 0; z < 6; z++) {
                            if (!(((z == 0) || (z == 5)) && (y == 3))) {
                                getWorld().getBlockAt(cornerX + x * xDir, getOriginalLocation().getBlockY() + y, cornerZ + z * zDir).setType(Material.RED_CONCRETE);
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("adding house?");
            if ((this.getDirection() == Direction.NORTH) || (this.getDirection() == Direction.SOUTH)) {
                cornerZ += 11 * zDir;
                cornerX += 1 * xDir;
                for (int i = 3; i > (3 - numHouses); i--) {
                    System.out.println("adding blocks?");
                    getWorld().getBlockAt(cornerX + (2 * i * xDir), getOriginalLocation().getBlockY() + 1, cornerZ).setType(Material.GREEN_CONCRETE);
                    getWorld().getBlockAt(cornerX + (2 * i * xDir), getOriginalLocation().getBlockY() + 1, cornerZ + (1 * zDir)).setType(Material.GREEN_CONCRETE);
                    getWorld().getBlockAt((cornerX + (2 * i * xDir)) + (1 * xDir), getOriginalLocation().getBlockY() + 1, cornerZ).setType(Material.GREEN_CONCRETE);
                    getWorld().getBlockAt(cornerX + (2 * i * xDir) + (1 * xDir), getOriginalLocation().getBlockY() + 1, cornerZ + (1 * zDir)).setType(Material.GREEN_CONCRETE);
                    getWorld().getBlockAt(cornerX + (2 * i * xDir), getOriginalLocation().getBlockY() + 2, cornerZ + (1 * zDir)).setType(Material.GREEN_CONCRETE);
                    getWorld().getBlockAt(cornerX + (2 * i * xDir) + (1 * xDir), getOriginalLocation().getBlockY() + 2, cornerZ).setType(Material.GREEN_CONCRETE);
                }
            } else {
                cornerZ += 1 * zDir;
                cornerX += 11 * xDir;
                for (int i = 3; i > (3 - numHouses); i--) {
                    System.out.println("adding blocks?");
                    getWorld().getBlockAt(cornerX, getOriginalLocation().getBlockY() + 1, cornerZ + (2 * i * zDir)).setType(Material.GREEN_CONCRETE);
                    getWorld().getBlockAt(cornerX + (1 * xDir), getOriginalLocation().getBlockY() + 1, cornerZ + (2 * i * zDir)).setType(Material.GREEN_CONCRETE);
                    getWorld().getBlockAt(cornerX, getOriginalLocation().getBlockY() + 1, cornerZ + (2 * i * zDir) + (1 * zDir)).setType(Material.GREEN_CONCRETE);
                    getWorld().getBlockAt(cornerX + (1 * xDir), getOriginalLocation().getBlockY() + 1, cornerZ + (2 * i * zDir) + (1 * zDir)).setType(Material.GREEN_CONCRETE);
                    getWorld().getBlockAt(cornerX + (1 * xDir), getOriginalLocation().getBlockY() + 2, cornerZ + (2 * i * zDir)).setType(Material.GREEN_CONCRETE);
                    getWorld().getBlockAt(cornerX, getOriginalLocation().getBlockY() + 2, cornerZ + (2 * i * zDir) + (1 * zDir)).setType(Material.GREEN_CONCRETE);
                }
            }
        }
    }

    public void clearHouseSpace() {
        int cornerX = this.getCornerX();
        int cornerZ = this.getCornerZ();
        int xDir = getDirection().xDir;
        int zDir = getDirection().zDir;
        if ((this.getDirection() == Direction.NORTH) || (this.getDirection() == Direction.SOUTH)) {
            cornerZ += 11 * zDir;
            cornerX += 1 * xDir;
            for (int x = 0; x < 8; x++) {
                for (int y = 1; y < 4; y++) {
                    for (int z = 0; z < 2; z++) {
                        getWorld().getBlockAt(cornerX + x * xDir, getOriginalLocation().getBlockY() + y, cornerZ + z * zDir).setType(Material.AIR);
                    }
                }
            }
        } else {
            cornerZ += 1 * zDir;
            cornerX += 11 * xDir;
            for (int x = 0; x < 2; x++) {
                for (int y = 1; y < 4; y++) {
                    for (int z = 0; z < 8; z++) {
                        getWorld().getBlockAt(cornerX + x * xDir, getOriginalLocation().getBlockY() + y, cornerZ + z * zDir).setType(Material.AIR);
                    }
                }
            }
        }
    }

    public boolean allColorsOwned() {
        HashMap<String, ArrayList<BoardSpace>> boardSpaces = GameManager.getBoardHash();

        for (HashMap.Entry mapElement : boardSpaces.entrySet()) {
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

    public boolean canMortgageProperty() {
        if (this.isMortgaged()) {
            return false;
        }
        HashMap<String, ArrayList<BoardSpace>> boardSpaces = GameManager.getBoardHash();

        for (HashMap.Entry mapElement : boardSpaces.entrySet()) {
            ArrayList<BoardSpace> value = ((ArrayList<BoardSpace>) mapElement.getValue());
            if ((value.get(0) instanceof HousingSpace) && (value.contains(this))) {
                for (BoardSpace space: value) {
                    if (((HousingSpace) space).getNumHouses() != 0) {
                        return false;
                    }
                }
                return true;
            }

        }
        return false;
    }

    public boolean canSellHouse() {
        if (numHouses == 0) {
            return false;
        }
        HashMap<String, ArrayList<BoardSpace>> boardSpaces = GameManager.getBoardHash();

        for (HashMap.Entry mapElement : boardSpaces.entrySet()) {
            ArrayList<BoardSpace> value = ((ArrayList<BoardSpace>) mapElement.getValue());
            if ((value.get(0) instanceof HousingSpace) && (value.contains(this))) {
                for (BoardSpace space: value) {
                    if (((HousingSpace) space).getNumHouses() > numHouses) {
                        return false;
                    }
                }
                return true;
            }

        }
        return false;
    }

    public void sellHouse() {
        getOwner().charge((houseCost)/2 * -1);
        numHouses -= 1;
        clearHouseSpace();
        updateHouses();
    }

    @Override
    public boolean mortgageProperty() {
        if (getOwner() == null) {
            return false;
        }
        if (canMortgageProperty()) {
            super.mortgageProperty();
        } else if (canSellHouse()) {
            sellHouse();
        } else {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<String> getLore(Player player) {
        ArrayList<String> lore = new ArrayList<String>();

        lore.add("location: " + this.getLocationOnBoard());

        String temp;
        Piece owner = getOwner();

        temp = "Owner: ";
        if (owner == null) {
            temp += "none";
        } else {
            temp += owner.getPlayer().getDisplayName();
        }
        lore.add(temp);

        if ((allColorsOwned()) && (canEvenlyAddHouse()) && (player.equals(owner.getPlayer()))) {
            lore.add(ChatColor.DARK_AQUA + "Left click to buy house");
        } else if (isMortgaged()) {
            lore.add(ChatColor.DARK_AQUA + "Left click to un-mortgage property");
        }

        if ((owner != null) && (owner.getPlayer().equals(player))) {
            if (numHouses == 0) {
                if (canMortgageProperty()) {
                    lore.add(ChatColor.DARK_AQUA + "Right click to mortgage property");
                }
            } else {
                lore.add(ChatColor.DARK_AQUA + "Right click to sell house");
            }
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

    @Override
    public void performSpaceAction(Piece piece) {
        if ((!isMortgaged()) && (this.getOwner() != null) && (!this.getOwner().getPlayer().equals(piece.getPlayer()))) {
            piece.charge(rent[numHouses]);
        }
    }
}
