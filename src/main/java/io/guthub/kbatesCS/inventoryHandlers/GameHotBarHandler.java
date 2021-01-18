package io.guthub.kbatesCS.inventoryHandlers;

import io.guthub.kbatesCS.boardSpaces.*;
import io.guthub.kbatesCS.monopoly.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class GameHotBarHandler {

    public GameHotBarHandler() {
    }

    public Inventory getTradeViewInventory(Player player) {
        Inventory propertyViewInventory = Bukkit.createInventory(null, 45, "properties");

        HashMap<String, ArrayList<BoardSpace>> properties = GameManager.getBoardHash();

        for (int i = 0; i < 2; i++) {
            propertyViewInventory.setItem(0 + (i * 9), createPropertyItem(properties.get("brown").get(i), Material.BROWN_DYE, Material.COAL, player));
        }
        for (int i = 0; i < 3; i++) {
            propertyViewInventory.setItem(1 + (i * 9), createPropertyItem(properties.get("light blue").get(i), Material.LIGHT_BLUE_DYE, Material.PRISMARINE_SHARD, player));
        }
        for (int i = 0; i < 3; i++) {
            propertyViewInventory.setItem(2 + (i * 9), createPropertyItem(properties.get("purple").get(i), Material.PINK_DYE, Material.PURPLE_DYE,  player));
        }
        for (int i = 0; i < 3; i++) {
            propertyViewInventory.setItem(3 + (i * 9), createPropertyItem(properties.get("orange").get(i), Material.ORANGE_DYE, Material.HONEYCOMB, player));
        }
        for (int i = 0; i < 3; i++) {
            propertyViewInventory.setItem(4 + (i * 9), createPropertyItem(properties.get("red").get(i), Material.RED_DYE, Material.NETHER_BRICK, player));
        }
        for (int i = 0; i < 3; i++) {
            propertyViewInventory.setItem(5 + (i * 9), createPropertyItem(properties.get("yellow").get(i), Material.YELLOW_DYE, Material.BOOK, player));
        }
        for (int i = 0; i < 3; i++) {
            propertyViewInventory.setItem(6 + (i * 9), createPropertyItem(properties.get("green").get(i), Material.GREEN_DYE, Material.LIME_DYE, player));
        }
        for (int i = 0; i < 2; i++) {
            propertyViewInventory.setItem(7 + (i * 9), createPropertyItem(properties.get("dark blue").get(i), Material.BLUE_DYE, Material.DIAMOND, player));
        }

        for (int i = 0; i < 4; i++) {
            propertyViewInventory.setItem(i + 40, createPropertyItem(properties.get("railroad").get(i), Material.BLACK_DYE, Material.NAME_TAG, player));
        }

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                propertyViewInventory.setItem(i + 37, createPropertyItem(properties.get("essentials").get(i), Material.CYAN_DYE, Material.BONE, player));
            } else {
                propertyViewInventory.setItem(i + 37, createPropertyItem(properties.get("essentials").get(i), Material.GRAY_DYE, Material.WHITE_DYE, player));
            }
        }

        return propertyViewInventory;
    }

    public Inventory getPropertyViewInventory(Player player) {
        Inventory propertyViewInventory = Bukkit.createInventory(null, 45, "properties");

        HashMap<String, ArrayList<BoardSpace>> properties = GameManager.getBoardHash();

        for (int i = 0; i < 2; i++) {
            propertyViewInventory.setItem(0 + (i * 9), createPropertyItem(properties.get("brown").get(i), Material.BROWN_DYE, Material.COAL, player));
        }
        for (int i = 0; i < 3; i++) {
            propertyViewInventory.setItem(1 + (i * 9), createPropertyItem(properties.get("light blue").get(i), Material.LIGHT_BLUE_DYE, Material.PRISMARINE_SHARD, player));
        }
        for (int i = 0; i < 3; i++) {
            propertyViewInventory.setItem(2 + (i * 9), createPropertyItem(properties.get("purple").get(i), Material.PINK_DYE, Material.PURPLE_DYE,  player));
        }
        for (int i = 0; i < 3; i++) {
            propertyViewInventory.setItem(3 + (i * 9), createPropertyItem(properties.get("orange").get(i), Material.ORANGE_DYE, Material.HONEYCOMB, player));
        }
        for (int i = 0; i < 3; i++) {
            propertyViewInventory.setItem(4 + (i * 9), createPropertyItem(properties.get("red").get(i), Material.RED_DYE, Material.NETHER_BRICK, player));
        }
        for (int i = 0; i < 3; i++) {
            propertyViewInventory.setItem(5 + (i * 9), createPropertyItem(properties.get("yellow").get(i), Material.YELLOW_DYE, Material.BOOK, player));
        }
        for (int i = 0; i < 3; i++) {
            propertyViewInventory.setItem(6 + (i * 9), createPropertyItem(properties.get("green").get(i), Material.GREEN_DYE, Material.LIME_DYE, player));
        }
        for (int i = 0; i < 2; i++) {
            propertyViewInventory.setItem(7 + (i * 9), createPropertyItem(properties.get("dark blue").get(i), Material.BLUE_DYE, Material.DIAMOND, player));
        }

        for (int i = 0; i < 4; i++) {
            propertyViewInventory.setItem(i + 40, createPropertyItem(properties.get("railroad").get(i), Material.BLACK_DYE, Material.NAME_TAG, player));
        }

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                propertyViewInventory.setItem(i + 37, createPropertyItem(properties.get("essentials").get(i), Material.CYAN_DYE, Material.BONE, player));
            } else {
                propertyViewInventory.setItem(i + 37, createPropertyItem(properties.get("essentials").get(i), Material.GRAY_DYE, Material.WHITE_DYE, player));
            }
        }

        return propertyViewInventory;

    }

    private ItemStack createPropertyItem(BoardSpace space, Material owningMaterial, Material notOwningMaterial, Player player) {
        ItemStack property;
        Player owner = null;
        if (!(space instanceof BuyableBoardSpace)) {
            return new ItemStack(Material.AIR);
        }

        BuyableBoardSpace buyableSpace = (BuyableBoardSpace) space;

        if (buyableSpace.getOwner() != null) {
            owner = buyableSpace.getOwner().getPlayer();
        }

        if ((owner != null) && (owner.equals(player))) {
            property = new ItemStack(owningMaterial);
        } else {
            property = new ItemStack(notOwningMaterial);
        }

        ItemMeta propertyMeta = property.getItemMeta();
        propertyMeta.setDisplayName(space.getName());

        if (space instanceof HousingSpace) {
            propertyMeta.setLocalizedName("housing space");
        } else if (space instanceof RailRoadSpace){
            propertyMeta.setLocalizedName("railroad");
        } else {
            propertyMeta.setLocalizedName("utility");
        }
        propertyMeta.setCustomModelData(space.getLocationOnBoard());


        propertyMeta.setLore(buyableSpace.getLore(player));

        property.setItemMeta(propertyMeta);
        return property;
    }

    public void setupHotBar(Player player) {
        PlayerInventory inventory = player.getInventory();

        ItemStack dice = new ItemStack(Material.GHAST_TEAR);
        ItemMeta diceMeta = dice.getItemMeta();
        diceMeta.setDisplayName("Dice");
        dice.setItemMeta(diceMeta);
        inventory.setItem(0, dice);

        ItemStack endTurn = new ItemStack(Material.BARRIER);
        ItemMeta endTurnMeta = endTurn.getItemMeta();
        endTurnMeta.setDisplayName("End Turn");
        endTurn.setItemMeta(endTurnMeta);
        inventory.setItem(8, endTurn);

        ItemStack buySpace = new ItemStack(Material.LIGHT_GRAY_DYE);
        ItemMeta buySpaceMeta = buySpace.getItemMeta();
        buySpaceMeta.setDisplayName("Buy Space");
        buySpace.setItemMeta(buySpaceMeta);
        inventory.setItem(2, buySpace);

        ItemStack viewProperties = new ItemStack(Material.MAGENTA_DYE);
        ItemMeta viewPropertiesMeta = viewProperties.getItemMeta();
        viewPropertiesMeta.setDisplayName("View Properties");
        viewProperties.setItemMeta(viewPropertiesMeta);
        inventory.setItem(3, viewProperties);

        ItemStack getOutOfJail = new ItemStack(Material.CHARCOAL);
        ItemMeta getOutOfJailsMeta = getOutOfJail.getItemMeta();
        getOutOfJailsMeta.setDisplayName("Get Out Of Jail");
        getOutOfJail.setItemMeta(getOutOfJailsMeta);
        inventory.setItem(4, getOutOfJail);
    }


}
