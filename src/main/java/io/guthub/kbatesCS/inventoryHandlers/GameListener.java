package io.guthub.kbatesCS.inventoryHandlers;

import io.guthub.kbatesCS.monopoly.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GameListener implements Listener {

    @EventHandler
    public void onPlayerClicks(PlayerInteractEvent e) {
        if (!GameManager.playerInGame(e.getPlayer()) || !GameManager.gameStarted()) {
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
            e.getPlayer().openInventory(GameManager.getGame().getHotBarHandler().getPropertyViewInventory());
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
}
