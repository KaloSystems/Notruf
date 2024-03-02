package de.fqbi.notrufsystem.commands;

import de.fqbi.notrufsystem.NotrufSystemPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class Decline_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase("decline")) {

            if (args.length == 1) {
                if (player.hasPermission("ispolice")) {
                    Player creator = Bukkit.getPlayer(args[0]);

                    if (!creator.isOnline())
                        player.sendMessage("§7Der Spieler ist nicht online oder existiert nicht§8.");

                    creator.sendMessage(NotrufSystemPlugin.getInstance().getData().prefix + "§7Dein Notruf wurde gelöscht!");

                    Consumer<Player> funk = players -> {
                        if (players.hasPermission("ispolice")) {
                            players.sendMessage(NotrufSystemPlugin.getInstance().getData().funk_prefix + player.getName()
                                    + "§7 hat den Notruf von §6" + creator.getName() + "§7 gelöscht.");
                        }
                    };
                    funk.accept(player);
                    NotrufSystemPlugin.getInstance().getData().getNotruf().remove(creator);

                } else {
                    player.sendMessage(NotrufSystemPlugin.getInstance().getData().prefix + "§cKeine Rechte§8.");
                }
            }else {
                player.sendMessage("Usage: /decline <Spielername>");
            }

        }
        return false;
    }
}
