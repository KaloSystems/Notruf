package de.fqbi.notrufsystem.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Data {

    public String prefix = "§c§lNotruf §8» §7";
    public String funk_prefix = "§b§lFunk §8» §7";

    private HashMap<Player, String> notruf = new HashMap<>();
    private ArrayList<Player> notruf_create = new ArrayList<>();
    public HashMap<Player, String> getNotruf() {
        return notruf;
    }

    public ArrayList<Player> getNotrufCreate() {
        return notruf_create;
    }

    public String randomLocation(String[] array){
        Random random = new Random();
        int index = random.nextInt(array.length);
        return array[index];
    }
}
