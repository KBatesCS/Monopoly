package io.guthub.kbates23.board;

public enum Direction {
    NORTH(-1, -1),
    SOUTH(1, 1),
    EAST(1, -1),
    WEST(-1, 1);

    public final int xDir;
    public final int zDir;
    Direction(int xDir, int zDir) {
        this.xDir = xDir;
        this.zDir = zDir;
    }
}
