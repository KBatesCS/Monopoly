package io.guthub.kbatesCS.board;

import io.guthub.kbatesCS.boardSpaces.*;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.zip.ZipInputStream;

public class Board {

    private World world;
    private BoardSpace[] spaces;
    private Location originalLocation;
    private HashMap<String, ArrayList<BoardSpace>> propertyDivision;

    /**
     * creates a full board
     * @param playerLocation the location where the board will be created
     */
    public Board(Location playerLocation) {
        originalLocation = playerLocation;
        spaces = new BoardSpace[40];
        propertyDivision = new HashMap<>();
        initializeSpaces();
        initializeSpaceHashmap();
        world = playerLocation.getWorld();
        buildGame();
    }

    public void testSpaces() {
        spaces[0].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[0].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[0].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[0].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[0].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[10].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[10].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[10].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[10].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[10].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[10].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[20].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[20].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[20].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[20].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[20].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[20].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[20].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[30].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[30].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[30].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[30].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[30].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
        spaces[30].addToSpace(new Piece(Material.BROWN_CONCRETE, null));
    }

    /**
     * as the name says, this is the general function to create the entire game board, map dices and everything.
     */
    private void buildGame() {
        createMap(originalLocation);
    }

    private void initializeSpaces() {
        spaces[0] = new GoSpace("Go", Direction.NORTH, 0, originalLocation);
        spaces[1] = new HousingSpace("Mediterranean Avenue", Direction.NORTH, 1, originalLocation, new int[]{2, 10, 30, 90, 160, 250, 50, 60, 30});
        spaces[2] = new CommunitySpace("Community Chest", Direction.NORTH, 2, originalLocation);
        spaces[3] = new HousingSpace("Baltic Avenue", Direction.NORTH, 3, originalLocation, new int[]{4, 20, 60, 180, 320, 450, 50, 60, 30});
        spaces[4] = new TaxSpace("Income Tax", Direction.NORTH, 4, originalLocation);
        spaces[5] = new RailRoadSpace("Reading Railroad", Direction.NORTH, 5, originalLocation);
        spaces[6] = new HousingSpace("Oriental Avenue", Direction.NORTH, 6, originalLocation, new int[]{6, 30, 90, 270, 400, 550, 50, 100, 50});
        spaces[7] = new ChanceSpace("Chance", Direction.NORTH, 7, originalLocation);
        spaces[8] = new HousingSpace("Vermont Avenue", Direction.NORTH, 8, originalLocation, new int[]{6, 30, 90, 270, 400, 550, 50, 100, 50});
        spaces[9] = new HousingSpace("Connecticut Avenue", Direction.NORTH, 9, originalLocation, new int[]{8, 40, 100, 300, 450, 600, 50, 120, 60});
        spaces[10] = new JailSpace("Jail", Direction.EAST, 0, originalLocation);
        spaces[11] = new HousingSpace("St. Charles Place", Direction.EAST, 1, originalLocation, new int[]{10, 50, 150, 450, 625, 750, 100, 140, 70});
        spaces[12] = new EssentialsSpace("Electric Company", Direction.EAST, 2, originalLocation);
        spaces[13] = new HousingSpace("States Avenue", Direction.EAST, 3, originalLocation, new int[]{10, 50, 150, 450, 625, 750, 100, 140, 70});
        spaces[14] = new HousingSpace("Virginia Avenue", Direction.EAST, 4, originalLocation, new int[]{12, 60, 180, 500, 700, 900, 100, 160, 80});
        spaces[15] = new RailRoadSpace("Pennsylvania RailRoad", Direction.EAST, 5, originalLocation);
        spaces[16] = new HousingSpace("St. James Place", Direction.EAST, 6, originalLocation, new int[]{14, 70, 200, 550, 750, 950, 100, 180, 90});
        spaces[17] = new CommunitySpace("Community Chest", Direction.EAST, 7, originalLocation);
        spaces[18] = new HousingSpace("Tennessee Avenue", Direction.EAST, 8, originalLocation, new int[]{14, 70, 200, 550, 750, 950, 100, 180, 90});
        spaces[19] = new HousingSpace("New York Avenue", Direction.EAST, 9, originalLocation, new int[]{16, 80, 220, 600, 800, 1000, 100, 200, 100});
        spaces[20] = new FreeParkingSpace("Free Parking", Direction.SOUTH, 0, originalLocation);
        spaces[21] = new HousingSpace("Kentucky Avenue", Direction.SOUTH, 1, originalLocation, new int[]{18, 90, 250, 700, 875, 1050, 150, 220, 110});
        spaces[22] = new ChanceSpace("Chance", Direction.SOUTH, 2, originalLocation);
        spaces[23] = new HousingSpace("Indiana Avenue", Direction.SOUTH, 3, originalLocation, new int[]{18, 90, 250, 700, 875, 1050, 150, 220, 110});
        spaces[24] = new HousingSpace("Illinois Avenue", Direction.SOUTH, 4, originalLocation, new int[]{20, 100, 300, 750, 925, 1100, 150, 240, 120});
        spaces[25] = new RailRoadSpace("B. & O. Railroad", Direction.SOUTH, 5, originalLocation);
        spaces[26] = new HousingSpace("Atlantic Avenue", Direction.SOUTH, 6, originalLocation, new int[]{22, 110, 330, 800, 975, 1150, 150, 260, 130});
        spaces[27] = new HousingSpace("Ventnor Avenue", Direction.SOUTH, 7, originalLocation, new int[]{22, 110, 330, 800, 975, 1150, 150, 260, 130});
        spaces[28] = new EssentialsSpace("Water Works", Direction.SOUTH, 8, originalLocation);
        spaces[29] = new HousingSpace("Marvin Gardens", Direction.SOUTH, 9, originalLocation, new int[]{24, 120, 360, 850, 1025, 1200, 150, 280, 140});
        spaces[30] = new ToJailSpace("Go to Jail", Direction.WEST, 0, originalLocation);
        spaces[31] = new HousingSpace("Pacific Avenue", Direction.WEST, 1, originalLocation, new int[]{26, 130, 390, 900, 1100, 1275, 200, 300, 150});
        spaces[32] = new HousingSpace("North Carolina Avenue", Direction.WEST, 2, originalLocation, new int[]{26, 130, 390, 900, 1100, 1275, 200, 300, 150});
        spaces[33] = new CommunitySpace("Community Chest", Direction.WEST, 3, originalLocation);
        spaces[34] = new HousingSpace("Pennsylvania Avenue", Direction.WEST, 4, originalLocation, new int[]{28, 150, 450, 1000, 1200, 1400, 200, 320, 160});
        spaces[35] = new RailRoadSpace("Short Line", Direction.WEST, 5, originalLocation);
        spaces[36] = new ChanceSpace("Chance", Direction.WEST, 6, originalLocation);
        spaces[37] = new HousingSpace("Park Place", Direction.WEST, 7, originalLocation, new int[]{35, 175, 500, 1100, 1300, 1500, 200, 350, 175});
        spaces[38] = new TaxSpace("Luxury Tax", Direction.WEST, 8, originalLocation);
        spaces[39] = new HousingSpace("Boardwalk", Direction.WEST, 9, originalLocation, new int[]{50, 200, 600, 1400, 1700, 2000, 200, 400, 200});
    }

    private void initializeSpaceHashmap() {
        propertyDivision.put("brown", (new ArrayList<BoardSpace>(Arrays.asList(spaces[1], spaces[3]))));
        propertyDivision.put("light blue", (new ArrayList<BoardSpace>(Arrays.asList(spaces[6], spaces[8], spaces[9]))));
        propertyDivision.put("purple", (new ArrayList<BoardSpace>(Arrays.asList(spaces[11], spaces[13], spaces[14]))));
        propertyDivision.put("orange", (new ArrayList<BoardSpace>(Arrays.asList(spaces[16], spaces[18], spaces[19]))));
        propertyDivision.put("red", (new ArrayList<BoardSpace>(Arrays.asList(spaces[21], spaces[23], spaces[24]))));
        propertyDivision.put("yellow", (new ArrayList<BoardSpace>(Arrays.asList(spaces[26], spaces[27], spaces[28]))));
        propertyDivision.put("green", (new ArrayList<BoardSpace>(Arrays.asList(spaces[31], spaces[32], spaces[34]))));
        propertyDivision.put("dark blue", (new ArrayList<BoardSpace>(Arrays.asList(spaces[37], spaces[39]))));
        propertyDivision.put("railroad", (new ArrayList<BoardSpace>(Arrays.asList(spaces[5], spaces[15], spaces[25], spaces[35]))));
        propertyDivision.put("essentials", (new ArrayList<BoardSpace>(Arrays.asList(spaces[12], spaces[28]))));
    }

    public HashMap<String, ArrayList<BoardSpace>> getPropertyDivision() {
        return propertyDivision;
    }

    public BoardSpace getSpace(int space) {
        return spaces[space];
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
            BufferedImage monopolyImage = ImageIO.read(new File("C:\\Users\\kevin\\Documents\\monopoly\\src\\main\\java\\io\\guthub\\kbatesCS\\board\\image\\MonopolyMap.png"));
            //BufferedImage monopolyImage = ImageIO.read(Board.class.getResourceAsStream("image\\MonopolyMap.png"));
            for (int xCord = 0; xCord < 108; xCord++) {
                for (int yCord = 0; yCord < 108; yCord++) {
                    Color pixel = new Color(monopolyImage.getRGB(xCord, yCord));
                    world.getBlockAt(x + xCord, y, z + yCord).setType(getMaterial(pixel));
                }
            }
        } catch (Exception e) {
            System.out.println("error in drawing board (probably issue with image path)");
        }
    }

    public void movePieceForward(Piece piece, int numSpaces) {

    }

}
