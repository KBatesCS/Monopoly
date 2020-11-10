package io.guthub.kbates23.monopoly;

import org.bukkit.plugin.java.JavaPlugin;
import io.guthub.kbates23.listeners.*;

import java.util.ArrayList;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        new EventListener(this);
        this.getCommand("createGame").setExecutor(new CommandListener(this));
    }

    @Override
    public void onDisable() {
        GameManager.endAllGames();
    }
}
