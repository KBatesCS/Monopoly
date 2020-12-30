package io.guthub.kbates23.boardSpaces;

import io.guthub.kbates23.board.Direction;
import io.guthub.kbates23.board.Piece;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public abstract class BoardSpace {

    private String displayName;
    private int buyCost;
    private int houseCost;
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private Direction direction;
    private int locationOnRow;
    private Location originalLocation;
    private World world;
    private int cornerX;
    private int cornerZ;

    public BoardSpace(String name, Direction direction, int locationOnRow, Location originalLocation) {
        this.displayName = name;
        this.direction = direction;
        this.locationOnRow = locationOnRow;
        this.originalLocation = originalLocation;
        this.world = originalLocation.getWorld();
        switch (direction) {
            case SOUTH:
                cornerX = originalLocation.getBlockX();
                cornerZ = originalLocation.getBlockZ();
                if (locationOnRow != 0) {
                    cornerX += 13 + (9 * (locationOnRow - 1));
                }
                break;
            case WEST:
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
                cornerX = originalLocation.getBlockX();
                cornerZ = originalLocation.getBlockZ() + 107;
                if (locationOnRow != 0) {
                    cornerZ -= 13 + (9 * (locationOnRow - 1));
                }
                break;
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
}