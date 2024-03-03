package de.fqbi.notrufsystem.commands;

import de.fqbi.notrufsystem.NotrufSystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.function.Consumer;

public class Accept_Command implements CommandExecutor {

    private HashMap<Player, Boolean> accepted = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase("accept")){

            if(args.length == 1) {

                if (player.hasPermission("ispolice")) {
                    Player creator = Bukkit.getPlayer(args[0]);

                    if (!creator.isOnline())
                        player.sendMessage("§7Der Spieler ist nicht online oder existiert nicht§8.");

                    if (NotrufSystem.getInstance().getData().getCall().containsKey(player)) {

                        if (accepted.containsKey(creator) != true) {
                            creator.sendMessage(NotrufSystem.getInstance().getData().prefix + "§7Dein Notruf wurde angenommen!");

                            Consumer<Player> funk = players -> {
                                if (players.hasPermission("ispolice")) {
                                    players.sendMessage(NotrufSystem.getInstance().getData().funk_prefix + player.getName()
                                            + "§7 hat den Notruf von §6" + creator.getName() + "§7 angenommen.");
                                    accepted.put(creator, true);
                                }
                            };
                            funk.accept(player);
                        } else {
                            player.sendMessage(NotrufSystem.getInstance().getData().prefix + "§7Dieser §3§lNotruf §7wurde bereits angenommen§8.");
                        }

                    } else {
                        player.sendMessage(NotrufSystem.getInstance().getData().prefix + "§cDer Notruf existiert nicht mehr§8.");
                    }

                    } else {
                        player.sendMessage(NotrufSystem.getInstance().getData().prefix + "§cKeine Rechte§8.");
                    }
                } else {
                    player.sendMessage("Usage: /accept <Spielername>");
            }
        }
        return false;
    }

    public HashMap<Player, Boolean> getAccepted() {
        return accepted;
    }
}
