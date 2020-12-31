package io.guthub.kbatesCS.inventoryHandlers;

import io.guthub.kbatesCS.monopoly.GameManager;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class GameHotBarHandler {

    public GameHotBarHandler() {

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


    }



}
