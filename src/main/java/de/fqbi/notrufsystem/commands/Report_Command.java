package de.fqbi.notrufsystem.commands;

import de.fqbi.notrufsystem.NotrufSystemPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.function.Consumer;
public class Report_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase("report")){

            if(args.length == 1){

                if(player.hasPermission("ispolice")) {
                    Player creator = Bukkit.getPlayer(args[0]);

                    if (creator != null) player.sendMessage("§7Der Spieler ist nicht online oder existiert nicht§8.");

                    Consumer<Player> report = players -> {
                        if (players.hasPermission("team")) {
                            players.sendMessage(NotrufSystemPlugin.getInstance().getData().funk_prefix + player.getName()
                                    + "§7 hat den Notruf von §6" + creator.getName() + "§7 gemeldet. \n" +
                                    "\n§7Notruf Grund: §e" + NotrufSystemPlugin.getInstance().getData().getNotruf().get(creator));
                        }
                    };
                    report.accept(player);
                    NotrufSystemPlugin.getInstance().getData().getNotruf().remove(creator);
                }else{
                    player.sendMessage(NotrufSystemPlugin.getInstance().getData().prefix + "§cKeine Rechte§8.");
                }
            }else{
                player.sendMessage("Usage: /report <Spielername>");
            }
        }
        return false;
    }
}
