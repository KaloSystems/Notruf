package de.fqbi.notrufsystem.commands;

import de.fqbi.notrufsystem.NotrufSystemPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.function.Consumer;

public class Accept_Command implements CommandExecutor {

    private HashMap<Player, Boolean> policeaccept = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase("accept")){

            if(args.length == 1) {

                if (player.hasPermission("ispolice")) {
                    Player creator = Bukkit.getPlayer(args[0]);

                    if (creator != null) player.sendMessage("§7Der Spieler ist nicht online oder existiert nicht§8.");

                    creator.sendMessage(NotrufSystemPlugin.getInstance().getData().prefix + "§7Dein Notruf wurde angenommen!");

                    Consumer<Player> funk = players -> {
                        if (players.hasPermission("ispolice")) {
                            players.sendMessage(NotrufSystemPlugin.getInstance().getData().funk_prefix + player.getName()
                                    + "§7 hat den Notruf von §6" + creator.getName() + "§7 angenommen.");
                        }
                    };
                    funk.accept(player);


                }else{
                    player.sendMessage(NotrufSystemPlugin.getInstance().getData().prefix + "§cKeine Rechte§8.");
                }
                } else {
                    player.sendMessage("Usage: /accept <Spielername>");
            }
        }
        return false;
    }
}