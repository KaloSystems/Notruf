package de.fqbi.notrufsystem.listener;

import de.fqbi.notrufsystem.NotrufSystem;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.function.Consumer;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();

        if(NotrufSystem.getInstance().getData().getNotrufCreate().contains(player)) {
            if(message.equalsIgnoreCase("stop")) {
                player.sendMessage(NotrufSystem.getInstance().getData().prefix + "§aDu hast erfolgreich dein Notruf" +
                        " abgebrochen§8.");
                NotrufSystem.getInstance().getData().getNotrufCreate().remove(player);
                event.setCancelled(true);
                return;
            }

                NotrufSystem.getInstance().getData().getNotruf().put(player, message);

                Consumer<Player> playerConsumer = getPlayerConsumer(player);
                playerConsumer.accept(player);
                NotrufSystem.getInstance().getData().getNotrufCreate().remove(player);
                event.setCancelled(true);
        }
    }

    private Consumer<Player> getPlayerConsumer(Player player) {
        double x = player.getLocation().getBlockX();
        double y = player.getLocation().getBlockY();
        double z = player.getLocation().getBlockZ();

        String[] locations = {"Hensburg", "Zentrum", "JVA Spegelsbach", "Niebrück", "Goldschweig", "Hohenbrück",
                "Gewerbegebiet Ost", "Krankenhaus Nord", "Westend"};

        TextComponent accept = new TextComponent();
        accept.setText("§a§lAnnehmen");
        accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("§8» §7Klicke zum Annehmen§8.").create()));
        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/accept " + player.getName()));

        TextComponent report = new TextComponent();
        report.setText("§e§lMelden");
        report.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("§8» §7Klicke zum Melden§8.").create()));
        report.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/report " + player.getName()));

        TextComponent delete = new TextComponent();
        delete.setText("§4§lLöschen");
        delete.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("§8» §7Klicke zum Löschen§8.").create()));
        delete.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/decline " + player.getName()));

        TextComponent main = new TextComponent("\n§8» §bNotruf von §7" + player.getName() + "§7 ist eingegangen. §8« \n" +
                "\n§bGrund §8» §7" + NotrufSystem.getInstance().getData().getNotruf().get(player) + "\n" +
                "§bOrt §8» §7" + NotrufSystem.getInstance().getData().randomLocation(locations)
                + " §8(§3" + x + " " + y + " " + z + "§8)\n\n");
        main.addExtra(accept);
        main.addExtra(" §8| ");
        main.addExtra(report);
        main.addExtra(" §8| ");
        main.addExtra(delete);

        Consumer<Player> playerConsumer = players -> {
            if(players.hasPermission("ispolice")) {
                players.spigot().sendMessage(main);
            }
        };
        return playerConsumer;
    }
}
