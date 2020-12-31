package io.guthub.kbatesCS.inventoryHandlers;

import io.guthub.kbatesCS.monopoly.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class GameListener implements Listener {
    @EventHandler
    public void onPlayerClicks(PlayerInteractEvent e) {
        if (!GameManager.playerInGame(e.getPlayer())) {
            return;
        }
        if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("dice")) {
            e.getPlayer().sendMessage("rolling dice");
        }
    }
}
