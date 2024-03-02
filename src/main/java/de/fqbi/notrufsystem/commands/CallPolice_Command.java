package de.fqbi.notrufsystem.commands;

import de.fqbi.notrufsystem.NotrufSystemPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CallPolice_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase("callpolice")){

            if(args.length == 0) {
                if(!NotrufSystemPlugin.getInstance().getData().getNotruf().containsKey(player)) {
                    player.sendMessage("§bSchreibe nun eine Begründung für deinen Notruf in den Chat! Beachte Notrufmissbrauch ist strafbar!");
                    NotrufSystemPlugin.getInstance().getData().getNotrufCreate().add(player);
                }else{
                    player.sendMessage(NotrufSystemPlugin.getInstance().getData().prefix + "§cDu hast bereits einen Notruf erstellt§8.");
                }
            }else{
                player.sendMessage("Usage: /callpolice");
            }
        }
        return false;
    }
}
