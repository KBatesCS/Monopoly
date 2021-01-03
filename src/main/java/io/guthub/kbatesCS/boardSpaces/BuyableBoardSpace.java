package io.guthub.kbatesCS.boardSpaces;

import io.guthub.kbatesCS.board.Direction;
import io.guthub.kbatesCS.board.Piece;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public abstract class BuyableBoardSpace extends BoardSpace{

    private Piece owner;
    private int buyCost;
    private boolean mortgaged;
    private int mortgagePrice;

    public BuyableBoardSpace(String name, Direction direction, int locationOnRow, Location originalLocation, int buyCost, int mortgagePrice) {
        super(name, direction, locationOnRow, originalLocation);
        this.buyCost = buyCost;
    }

    public abstract ArrayList<String> getLore(Player player);

    public Piece getOwner() {
        return owner;
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

    public void updateOwnershipDisplay(Piece owner) {
        int locationOnRow = this.getLocationOnRow();
        if (locationOnRow == 0) {
            return;
        }
        int xBottom = this.getCornerX();
        int zBottom = this.getCornerZ();
        Material mainMaterial = Material.WHITE_CONCRETE;
        Material edgeMaterial = Material.WHITE_CONCRETE;
        if (owner != null) {
            mainMaterial = owner.getMaterial();
            edgeMaterial = Material.BLACK_CONCRETE;
        }
        Direction direction = this.getDirection();
        Location originalLocation = this.getOriginalLocation();
        World world = this.getWorld();
        if ((direction == Direction.NORTH) || (direction == Direction.SOUTH)) {
            xBottom += 2 * direction.xDir;
            zBottom += 14 * direction.zDir;
            world.getBlockAt(xBottom, originalLocation.getBlockY(), zBottom).setType(edgeMaterial);
            world.getBlockAt(xBottom + (1 * direction.xDir), originalLocation.getBlockY(), zBottom).setType(mainMaterial);
            world.getBlockAt(xBottom + (2 * direction.xDir), originalLocation.getBlockY(), zBottom).setType(mainMaterial);
            world.getBlockAt(xBottom + (3 * direction.xDir), originalLocation.getBlockY(), zBottom).setType(mainMaterial);
            world.getBlockAt(xBottom + (4 * direction.xDir), originalLocation.getBlockY(), zBottom).setType(mainMaterial);
            world.getBlockAt(xBottom + (5 * direction.xDir), originalLocation.getBlockY(), zBottom).setType(edgeMaterial);
            world.getBlockAt(xBottom + (1 * direction.xDir), originalLocation.getBlockY(), zBottom + (1 * direction.zDir)).setType(edgeMaterial);
            world.getBlockAt(xBottom + (2 * direction.xDir), originalLocation.getBlockY(), zBottom + (1 * direction.zDir)).setType(mainMaterial);
            world.getBlockAt(xBottom + (3 * direction.xDir), originalLocation.getBlockY(), zBottom + (1 * direction.zDir)).setType(mainMaterial);
            world.getBlockAt(xBottom + (4 * direction.xDir), originalLocation.getBlockY(), zBottom + (1 * direction.zDir)).setType(edgeMaterial);
            world.getBlockAt(xBottom + (2 * direction.xDir), originalLocation.getBlockY(), zBottom + (2 * direction.zDir)).setType(edgeMaterial);
            world.getBlockAt(xBottom + (3 * direction.xDir), originalLocation.getBlockY(), zBottom + (2 * direction.zDir)).setType(edgeMaterial);
        } else {
            xBottom += 14 * direction.xDir;
            zBottom += 2 * direction.zDir;
            world.getBlockAt(xBottom, originalLocation.getBlockY(), zBottom).setType(edgeMaterial);
            world.getBlockAt(xBottom, originalLocation.getBlockY(), zBottom + (1 * direction.zDir)).setType(mainMaterial);
            world.getBlockAt(xBottom, originalLocation.getBlockY(), zBottom + (2 * direction.zDir)).setType(mainMaterial);
            world.getBlockAt(xBottom, originalLocation.getBlockY(), zBottom + (3 * direction.zDir)).setType(mainMaterial);
            world.getBlockAt(xBottom, originalLocation.getBlockY(), zBottom + (4 * direction.zDir)).setType(mainMaterial);
            world.getBlockAt(xBottom, originalLocation.getBlockY(), zBottom + (5 * direction.zDir)).setType(edgeMaterial);
            world.getBlockAt(xBottom + (1 * direction.xDir), originalLocation.getBlockY(), zBottom + (1 * direction.zDir)).setType(edgeMaterial);
            world.getBlockAt(xBottom + (1 * direction.xDir), originalLocation.getBlockY(), zBottom + (2 * direction.zDir)).setType(mainMaterial);
            world.getBlockAt(xBottom + (1 * direction.xDir), originalLocation.getBlockY(), zBottom + (3 * direction.zDir)).setType(mainMaterial);
            world.getBlockAt(xBottom + (1 * direction.xDir), originalLocation.getBlockY(), zBottom + (4 * direction.zDir)).setType(edgeMaterial);
            world.getBlockAt(xBottom + (2 * direction.xDir), originalLocation.getBlockY(), zBottom + (2 * direction.zDir)).setType(edgeMaterial);
            world.getBlockAt(xBottom + (2 * direction.xDir), originalLocation.getBlockY(), zBottom + (3 * direction.zDir)).setType(edgeMaterial);
        }
    }
}
