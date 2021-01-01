package io.guthub.kbatesCS.inventoryHandlers;

import io.guthub.kbatesCS.boardSpaces.BoardSpace;
import io.guthub.kbatesCS.boardSpaces.HousingSpace;
import io.guthub.kbatesCS.monopoly.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GameHotBarHandler {

    public GameHotBarHandler() {
    }

    public Inventory getPropertyViewInventory() {
        Inventory propertyViewInventory = Bukkit.createInventory(null, 45, "properties");

        HashMap<String, ArrayList<BoardSpace>> properties = GameManager.getBoardHash();

        for (int i = 0; i < 2; i++) {
            propertyViewInventory.setItem(0 + (i * 9), createPropertyItem(properties.get("brown").get(i), Material.BROWN_WOOL));
        }

        return propertyViewInventory;

    }

    private ItemStack createPropertyItem(BoardSpace space, Material material) {
        ItemStack property = new ItemStack(material);
        ItemMeta propertyMeta = property.getItemMeta();
        propertyMeta.setDisplayName(space.getName());


        if (space instanceof HousingSpace) {
            propertyMeta.setLore(((HousingSpace) space).getLore());
        }

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

        ItemStack buySpace = new ItemStack(Material.GREEN_WOOL);
        ItemMeta buySpaceMeta = buySpace.getItemMeta();
        buySpaceMeta.setDisplayName("Buy Space");
        buySpace.setItemMeta(buySpaceMeta);
        inventory.setItem(2, buySpace);

        ItemStack viewProperties = new ItemStack(Material.BLUE_WOOL);
        ItemMeta viewPropertiesMeta = viewProperties.getItemMeta();
        viewPropertiesMeta.setDisplayName("View Properties");
        viewProperties.setItemMeta(viewPropertiesMeta);
        inventory.setItem(3, viewProperties);
    }


}
