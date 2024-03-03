package de.fqbi.notrufsystem;

import de.fqbi.notrufsystem.commands.*;
import de.fqbi.notrufsystem.listener.InventoryClickListener;
import de.fqbi.notrufsystem.listener.PlayerChatListener;
import de.fqbi.notrufsystem.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NotrufSystem extends JavaPlugin {

    private static NotrufSystem instance;
    private Data data;
    private Accept_Command acceptCommand;

    @Override
    public void onEnable() {
        instance = this;
        data = new Data();
        acceptCommand = new Accept_Command();

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

    public Accept_Command getAcceptCommand() {
        return acceptCommand;
    }

    public Data getData() {
        return data;
    }

    public static NotrufSystem getInstance() {
        return instance;
    }
}
