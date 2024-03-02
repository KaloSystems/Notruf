package de.fqbi.notrufsystem.listener;

import de.fqbi.notrufsystem.NotrufSystemPlugin;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;


public class InventoryClickListener implements Listener {

    @EventHandler
    public void handle(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(event.getInventory().getTitle().equalsIgnoreCase("§7Notrufe")) {
            event.setCancelled(true);
                if (!(event.getCurrentItem().getType() != Material.SKULL_ITEM)) {
                    Player creator = Bukkit.getPlayer(event.getCurrentItem().getItemMeta().getDisplayName()
                            .replaceAll("§8» §b", ""));
                    String reason = NotrufSystemPlugin.getInstance().getData().getNotruf().get(creator);

                    double x = player.getLocation().getBlockX();
                    double y = player.getLocation().getBlockY();
                    double z = player.getLocation().getBlockZ();

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

                    TextComponent main = new TextComponent("\n§8» §bNotruf von §7" + event.getCurrentItem().getItemMeta().getDisplayName()
                            .replaceAll("\n§8» §b", "") + "§7 ist eingegangen. §8« \n" +
                            "\n§bGrund §8» §7" + reason + "\n" +
                            "§bOrt §8» §7Hensburg §8(§3" + x + " " + y + " §3" + z + "§8)\n\n");
                    main.addExtra(accept);
                    main.addExtra(" §8| ");
                    main.addExtra(report);
                    main.addExtra(" §8| ");
                    main.addExtra(delete);

                    player.spigot().sendMessage(main);
                }
            }
    }
}
