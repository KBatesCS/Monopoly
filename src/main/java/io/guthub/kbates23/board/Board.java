package io.guthub.kbates23.board;

import io.guthub.kbates23.board.BoardSpace;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board {

    private World world;
    private BoardSpace[] spaces;
    private Location originalLocation;

    /**
     * creates a full board
     * @param playerLocation the location where the board will be created
     */
    public Board(Location playerLocation) {
        originalLocation = playerLocation;
        spaces = new BoardSpace[40];
        initializeSpaces();
        world = playerLocation.getWorld();
        buildGame();
    }

    public void testSpaces() {
        Piece piece = new Piece(Material.PINK_CONCRETE, null);
        spaces[5].addToSpace(new Piece(Material.BLACK_CONCRETE, null));
        spaces[5].addToSpace(new Piece(Material.RED_CONCRETE, null));
        spaces[5].addToSpace(piece);
        spaces[5].addToSpace(new Piece(Material.GREEN_CONCRETE, null));
        spaces[5].removeFromSpace(piece);
        spaces[14].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[14].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[14].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[14].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[14].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[24].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[24].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[24].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[34].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[34].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[34].addToSpace(new Piece(Material.BROWN_CONCRETE, null));


    }

    /**
     * as the name says, this is the general function to create the entire game board, map dices and everything.
     */
    private void buildGame() {
        createMap(originalLocation);
    }



    private void initializeSpaces() {
        spaces[0] = new BoardSpace("Go", Direction.NORTH, 0, originalLocation);
        spaces[1] = new BoardSpace("Mediterranean Avenue", Direction.NORTH, 1, originalLocation);
        spaces[2] = new BoardSpace("Community Chest", Direction.NORTH, 2, originalLocation);
        spaces[3] = new BoardSpace("Baltic Avenue", Direction.NORTH, 3, originalLocation);
        spaces[4] = new BoardSpace("Income Tax", Direction.NORTH, 4, originalLocation);
        spaces[5] = new BoardSpace("Reading Railroad", Direction.NORTH, 5, originalLocation);
        spaces[6] = new BoardSpace("Oriental Avenue", Direction.NORTH, 6, originalLocation);
        spaces[7] = new BoardSpace("Chance", Direction.NORTH, 7, originalLocation);
        spaces[8] = new BoardSpace("Vermont Avenue", Direction.NORTH, 8, originalLocation);
        spaces[9] = new BoardSpace("Connecticut Avenue", Direction.NORTH, 9, originalLocation);
        spaces[10] = new BoardSpace("Jail", Direction.EAST, 0, originalLocation);
        spaces[11] = new BoardSpace("St. Charles Place", Direction.EAST, 1, originalLocation);
        spaces[12] = new BoardSpace("Electric Company", Direction.EAST, 2, originalLocation);
        spaces[13] = new BoardSpace("States Avenue", Direction.EAST, 3, originalLocation);
        spaces[14] = new BoardSpace("Virginia Avenue", Direction.EAST, 4, originalLocation);
        spaces[15] = new BoardSpace("Pennsylvania RailRoad", Direction.EAST, 5, originalLocation);
        spaces[16] = new BoardSpace("St. James Place", Direction.EAST, 6, originalLocation);
        spaces[17] = new BoardSpace("Community Chest", Direction.EAST, 7, originalLocation);
        spaces[18] = new BoardSpace("Tennessee Avenue", Direction.EAST, 8, originalLocation);
        spaces[19] = new BoardSpace("New York Avenue", Direction.EAST, 9, originalLocation);
        spaces[20] = new BoardSpace("Free Parking", Direction.SOUTH, 0, originalLocation);
        spaces[21] = new BoardSpace("Kentucky Avenue", Direction.SOUTH, 1, originalLocation);
        spaces[22] = new BoardSpace("Chance", Direction.SOUTH, 2, originalLocation);
        spaces[23] = new BoardSpace("Indiana Avenue", Direction.SOUTH, 3, originalLocation);
        spaces[24] = new BoardSpace("Illinois Avenue", Direction.SOUTH, 4, originalLocation);
        spaces[25] = new BoardSpace("B. & O. Railroad", Direction.SOUTH, 5, originalLocation);
        spaces[26] = new BoardSpace("Atlantic Avenue", Direction.SOUTH, 6, originalLocation);
        spaces[27] = new BoardSpace("Ventnor Avenue", Direction.SOUTH, 7, originalLocation);
        spaces[28] = new BoardSpace("Water Works", Direction.SOUTH, 8, originalLocation);
        spaces[29] = new BoardSpace("Marvin Gardens", Direction.SOUTH, 9, originalLocation);
        spaces[30] = new BoardSpace("Go to Jail", Direction.WEST, 0, originalLocation);
        spaces[31] = new BoardSpace("Pacific Avenue", Direction.WEST, 1, originalLocation);
        spaces[32] = new BoardSpace("North Carolina Avenue", Direction.WEST, 2, originalLocation);
        spaces[33] = new BoardSpace("Community Chest", Direction.WEST, 3, originalLocation);
        spaces[34] = new BoardSpace("Pennsylvania Avenue", Direction.WEST, 4, originalLocation);
        spaces[35] = new BoardSpace("Short Line", Direction.WEST, 5, originalLocation);
        spaces[36] = new BoardSpace("Chance", Direction.WEST, 6, originalLocation);
        spaces[37] = new BoardSpace("Park Place", Direction.WEST, 7, originalLocation);
        spaces[38] = new BoardSpace("Luxury Tax", Direction.WEST, 8, originalLocation);
        spaces[39] = new BoardSpace("Boardwalk", Direction.WEST, 9, originalLocation);
    }

    /**
     * gets the material that corresponds with a color
     * @param c the color that will be used to determine the block type
     * @return a material (block only) based on the color parameter
     */
    private Material getMaterial(Color c) {
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        if ((r == 0) && (g == 0) && (b == 0)) {
            return Material.BLACK_CONCRETE;
        } else if ((r == 162) && (g == 13) && (b == 106)) {
            return Material.MAGENTA_CONCRETE;
        } else if ((r == 230) && (g == 134) && (b == 17)) {
            return Material.ORANGE_CONCRETE;
        } else if ((r == 237) && (g == 28) && (b == 36)) {
            return Material.RED_CONCRETE;
        } else if ((r == 255) && (g == 242) && (b == 0)) {
            return Material.YELLOW_CONCRETE;
        } else if ((r == 34) && (g == 177) && (b == 76)) {
            return Material.GREEN_CONCRETE;
        } else if ((r == 63) && (g == 72) && (b == 204)) {
            return Material.BLUE_CONCRETE;
        } else if ((r == 121) && (g == 96) && (b == 53)) {
            return Material.BROWN_CONCRETE;
        } else if ((r == 153) && (g == 217) && (b == 234)) {
            return Material.LIGHT_BLUE_CONCRETE;
        } else {
            return Material.WHITE_CONCRETE;
        }
    }

    /**
     * this creates just the board (or the map) for the game.
     * @param playerLocation The location to build the map at.
     */
    private void createMap(Location playerLocation) {
        int x = (int) playerLocation.getX();
        int y = (int) playerLocation.getY();
        int z = (int) playerLocation.getZ();

        try {
            BufferedImage monopolyImage = ImageIO.read(new File("C:\\Users\\kevin\\OneDrive\\Documents\\GitHub repos\\Monopoly\\src\\main\\java\\io\\guthub\\kbates23\\board\\MonopolyMap.png"));
            for (int xCord = 0; xCord < 108; xCord++) {
                for (int yCord = 0; yCord < 108; yCord++) {
                    Color pixel = new Color(monopolyImage.getRGB(xCord, yCord));
                    world.getBlockAt(x + xCord, y, z + yCord).setType(getMaterial(pixel));
                }
            }
        } catch (IOException e) {
            System.out.println("error in drawing board (probably issue with image path)");
        }
    }

}
