package io.guthub.kbatesCS.monopoly;

import io.guthub.kbatesCS.inventoryHandlers.GameListener;
import org.bukkit.plugin.java.JavaPlugin;
import io.guthub.kbatesCS.commandHandlers.*;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        new AutoCompleter(this);
        this.getCommand("createGame").setExecutor(new CommandListener(this));
        this.getCommand("join").setExecutor(new CommandListener(this));
        this.getCommand("join").setTabCompleter(new AutoCompleter(this));
        this.getCommand("startGame").setExecutor(new CommandListener(this));
        this.getCommand("endGame").setExecutor(new CommandListener(this));

        this.getServer().getPluginManager().registerEvents(new GameListener(), this);
    }

    @Override
    public void onDisable() {
        GameManager.endGame();
    }
}
