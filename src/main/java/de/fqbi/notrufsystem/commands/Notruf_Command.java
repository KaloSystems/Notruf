package de.fqbi.notrufsystem.commands;

import de.fqbi.notrufsystem.NotrufSystem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class Notruf_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(command.getName().equalsIgnoreCase("notruf")){

            if(player.hasPermission("ispolice")) {
                Inventory inventory = Bukkit.createInventory(null, InventoryType.CHEST, "§7Notrufe");

                for (Map.Entry<Player, String> entry : NotrufSystem.getInstance().getData().getNotruf().entrySet()) {

                    ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
                    SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
                    meta.setDisplayName("§8» §b" + entry.getKey().getName());
                    meta.setOwner(entry.getKey().getName());
                    itemStack.setItemMeta(meta);

                    inventory.addItem(itemStack);
                }
                player.openInventory(inventory);
            }else{
                player.sendMessage(NotrufSystem.getInstance().getData().prefix + "§cKeine Rechte§8.");
            }
        }
        return false;
    }
}
