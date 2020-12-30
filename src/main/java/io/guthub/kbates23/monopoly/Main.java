package io.guthub.kbates23.monopoly;

import org.bukkit.plugin.java.JavaPlugin;
import io.guthub.kbates23.commandHandlers.*;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        new AutoCompleter(this);
        this.getCommand("createGame").setExecutor(new CommandListener(this));
        this.getCommand("join").setExecutor(new CommandListener(this));
        this.getCommand("join").setTabCompleter(new AutoCompleter(this));
        this.getCommand("startGame").setExecutor(new CommandListener(this));
        this.getCommand("endGame").setExecutor(new CommandListener(this));
    }

    @Override
    public void onDisable() {
        GameManager.endAllGames();
    }
}
