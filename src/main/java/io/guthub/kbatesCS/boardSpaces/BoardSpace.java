package io.guthub.kbatesCS.boardSpaces;

import io.guthub.kbatesCS.board.Board;
import io.guthub.kbatesCS.board.Direction;
import io.guthub.kbatesCS.board.Piece;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.ArrayList;

public abstract class BoardSpace {

    private String displayName;
    private int buyCost;
    private int houseCost;
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private Direction direction;
    private int locationOnRow;
    private int locationOnBoard;
    private Location originalLocation;
    private World world;
    private int cornerX;
    private int cornerZ;

    public BoardSpace(String name, Direction direction, int locationOnRow, Location originalLocation) {
        this.displayName = name;
        this.direction = direction;
        this.locationOnRow = locationOnRow;
        this.locationOnBoard = locationOnRow;
        this.originalLocation = originalLocation;
        this.world = originalLocation.getWorld();
        switch (direction) {
            case SOUTH:
                locationOnBoard += 20;
                cornerX = originalLocation.getBlockX();
                cornerZ = originalLocation.getBlockZ();
                if (locationOnRow != 0) {
                    cornerX += 13 + (9 * (locationOnRow - 1));
                }
                break;
            case WEST:
                locationOnBoard += 30;
                cornerX = originalLocation.getBlockX() + 107;
                cornerZ = originalLocation.getBlockZ();
                if (locationOnRow != 0) {
                    cornerZ += 13 + (9 * (locationOnRow - 1));
                }
                break;
            case NORTH:
                cornerX = originalLocation.getBlockX() + 107;
                cornerZ = originalLocation.getBlockZ() + 107;
                if (locationOnRow != 0) {
                    cornerX -= 13 + (9 * (locationOnRow - 1));
                }
                break;
            case EAST:
                locationOnBoard += 10;
                cornerX = originalLocation.getBlockX();
                cornerZ = originalLocation.getBlockZ() + 107;
                if (locationOnRow != 0) {
                    cornerZ -= 13 + (9 * (locationOnRow - 1));
                }
                break;
        }
    }

    public void updateOwnershipDisplay(Piece owner) {
        if (locationOnRow == 0) {
            return;
        }
        int xBottom = cornerX;
        int zBottom = cornerZ;
        Material mainMaterial = Material.WHITE_CONCRETE;
        Material edgeMaterial = Material.WHITE_CONCRETE;
        if (owner != null) {
            mainMaterial = owner.getMaterial();
            edgeMaterial = Material.BLACK_CONCRETE;
        }
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

    public void addToSpace(Piece piece) {
        pieces.add(piece);
        updateSpace();
        //add code to perform new space action??
    }

    public boolean removeFromSpace(Piece piece) {
        boolean success = pieces.remove(piece);
        updateSpace();
        return success;
    }

    private void updateSpace() {
        System.out.println(pieces);
        for (int i = 0; i < 6; i++) {
            if (i < pieces.size()) {
                buildPiece(i, pieces.get(i).getMaterial());
            } else {
                buildPiece(i, Material.AIR);
            }
        }
    }

    public String getName() {
        return displayName;
    }

    public int getLocationOnBoard() {
        return locationOnBoard;
    }

    private void buildPiece(int pieceNum, Material material) {

        int xBottom = cornerX;
        int zBottom = cornerZ;
        if (locationOnRow == 0) {
            xBottom += direction.xDir * 2;
            zBottom += direction.zDir * 2;
            if ((direction == Direction.NORTH) || (direction == Direction.SOUTH)) {
                if (pieceNum < 3) {
                    xBottom += (3 * pieceNum + 2) * direction.xDir;
                } else {
                    zBottom += (3 * (pieceNum - 3) + 2) * direction.zDir;
                }
            } else {
                if (pieceNum < 3) {
                    zBottom += (3 * pieceNum + 2) * direction.zDir;
                } else {
                    xBottom += (3 * (pieceNum - 3) + 2) * direction.xDir;
                }
            }
        } else {
            if ((direction == Direction.NORTH) || (direction == Direction.SOUTH)) {
                xBottom += 4 * (pieceNum % 2) * direction.xDir;
                zBottom += (2 - (pieceNum / 2)) * 3 * direction.zDir;
                xBottom += direction.xDir * 2;
                zBottom += direction.zDir * 1;
            } else {
                xBottom += (2 - (pieceNum / 2)) * 3 * direction.xDir;
                zBottom += 4 * (pieceNum % 2) * direction.zDir;
                xBottom += direction.xDir * 1;
                zBottom += direction.zDir * 2;
            }
        }
        System.out.println("building on space: " + locationOnRow + ", piece #" + pieceNum + ", x:" + xBottom + ", z:" + zBottom);
        world.getBlockAt(xBottom, originalLocation.getBlockY() + 1, zBottom).setType(material);
        world.getBlockAt(xBottom + direction.xDir, originalLocation.getBlockY() + 1, zBottom).setType(material);
        world.getBlockAt(xBottom, originalLocation.getBlockY() + 1, zBottom + direction.zDir).setType(material);
        world.getBlockAt(xBottom + direction.xDir, originalLocation.getBlockY() + 1,
                zBottom + direction.zDir).setType(material);
        world.getBlockAt(xBottom, originalLocation.getBlockY() + 2, zBottom).setType(material);
        world.getBlockAt(xBottom + direction.xDir, originalLocation.getBlockY() + 2,
                zBottom + direction.zDir).setType(material);
    }

    public abstract void performSpaceAction(Piece piece);

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BoardSpace)) {
            return false;
        }
        BoardSpace space = (BoardSpace) o;
        return ((space.getName().equalsIgnoreCase(this.getName())));
    }
}