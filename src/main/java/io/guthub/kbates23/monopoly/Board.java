package io.guthub.kbates23.monopoly;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class Board {

    World world = Bukkit.getWorld("world");

    public Board(Location playerLocation) {
        createMap(playerLocation);
    }

    private void createBoard() {

    }

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

    private void createMap(Location playerLocation) {
        int x = (int) playerLocation.getX();
        int y = (int) playerLocation.getY();
        int z = (int) playerLocation.getZ();

        try {
            BufferedImage monopolyImage = ImageIO.read(new File("C:\\Users\\kevin\\OneDrive\\Documents\\GitHub repos\\Monopoly\\src\\main\\java\\io\\guthub\\kbates23\\monopoly\\MonopolyMap.png"));
            for (int xCord = 0; xCord < 108; xCord++) {
                for (int yCord = 0; yCord < 108; yCord++) {
                    Color pixel = new Color(monopolyImage.getRGB(xCord, yCord));
//                    if (pixel.getRed() + pixel.getBlue() + pixel.getGreen() == 0) {
                        world.getBlockAt(x + xCord, y, z + yCord).setType(getMaterial(pixel));
//                    }
                }
            }
        } catch (Exception e) {
            System.out.println("error in drawing board");
        }
    }

}
