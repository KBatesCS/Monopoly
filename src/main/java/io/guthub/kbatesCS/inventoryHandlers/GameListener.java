package io.guthub.kbatesCS.inventoryHandlers;

import io.guthub.kbatesCS.boardSpaces.BuyableBoardSpace;
import io.guthub.kbatesCS.boardSpaces.HousingSpace;
import io.guthub.kbatesCS.monopoly.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GameListener implements Listener {

    @EventHandler
    public void onPlayerClicks(PlayerInteractEvent e) {
        if (!GameManager.playerInGame(e.getPlayer()) || !GameManager.gameStarted()) {
            return;
        }
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            e.setCancelled(true);
        }
        if (!e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            return;
        }
        String itemName = e.getItem().getItemMeta().getDisplayName();
        if (itemName.equalsIgnoreCase("dice")) {
            if (GameManager.getGame().currentPlayer().equals(e.getPlayer())) {
                if (!GameManager.getGame().rollDice()) {
                    e.getPlayer().sendMessage("You can not roll your dice again");
                }
            } else {
                e.getPlayer().sendMessage("It is not your turn");
            }
        } else if (itemName.equalsIgnoreCase("end turn")) {
            if (!GameManager.getGame().startNewTurn()) {
                e.getPlayer().sendMessage("Your turn is not over yet");
            }
        } else if (itemName.equalsIgnoreCase("buy space")) {
            if (!GameManager.getGame().buySpace()) {
                e.getPlayer().sendMessage("Can not buy this space right now");
            }
        } else if (itemName.equalsIgnoreCase("View Properties")) {
            e.getPlayer().openInventory(GameManager.getGame().getHotBarHandler().getPropertyViewInventory(e.getPlayer()));
        }
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (GameManager.playerInGame(player) && GameManager.gameStarted()) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = (Player) e.getPlayer();
        if (GameManager.playerInGame(player) && GameManager.gameStarted()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if ((e.getCurrentItem() != null) && (e.getCurrentItem().hasItemMeta()) && (e.getCurrentItem().getItemMeta().hasLocalizedName())) {
            String localizedName = e.getCurrentItem().getItemMeta().getLocalizedName();
            if (localizedName.equalsIgnoreCase("housing space") || localizedName.equalsIgnoreCase("railroad") || localizedName.equalsIgnoreCase("utility")) {
                if (e.getAction().equals(InventoryAction.PICKUP_ALL)) {
                    BuyableBoardSpace space = (BuyableBoardSpace) GameManager.getGame().getSpace(e.getCurrentItem().getItemMeta().getCustomModelData());
                    if (space instanceof HousingSpace) {
                        if (!space.isMortgaged()) {
                            ((HousingSpace) space).addHouse(player);
                        } else {
                            space.unMortgageProperty();
                        }
                    } else {
                        space.unMortgageProperty();
                    }
                    player.openInventory(GameManager.getGame().getHotBarHandler().getPropertyViewInventory(player));
                } else if (e.getAction().equals(InventoryAction.PICKUP_HALF)) {

                    ((BuyableBoardSpace) GameManager.getGame().getSpace(e.getCurrentItem().getItemMeta().getCustomModelData())).mortgageProperty();
                    player.openInventory(GameManager.getGame().getHotBarHandler().getPropertyViewInventory(player));
                }
            }
        }
        if (GameManager.playerInGame(player) && GameManager.gameStarted()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        Player player = (Player) e.getPlayer();
        if (GameManager.playerInGame(player) && GameManager.gameStarted()) {
            e.setCancelled(true);
        }
    }
}
