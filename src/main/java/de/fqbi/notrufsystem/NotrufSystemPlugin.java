package de.fqbi.notrufsystem;

import de.fqbi.notrufsystem.commands.*;
import de.fqbi.notrufsystem.listener.InventoryClickListener;
import de.fqbi.notrufsystem.listener.PlayerChatListener;
import de.fqbi.notrufsystem.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NotrufSystemPlugin extends JavaPlugin {

    private static NotrufSystemPlugin instance;
    private Data data;

    @Override
    public void onEnable() {
        instance = this;
        data = new Data();

        getCommand("accept").setExecutor(new Accept_Command());
        getCommand("decline").setExecutor(new Decline_Command());
        getCommand("report").setExecutor(new Report_Command());
        getCommand("callpolice").setExecutor(new CallPolice_Command());
        getCommand("notruf").setExecutor(new Notruf_Command());

        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);

    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public Data getData() {
        return data;
    }

    public static NotrufSystemPlugin getInstance() {
        return instance;
    }
}
