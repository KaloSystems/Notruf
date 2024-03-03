package de.fqbi.notrufsystem.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Data {

    public String prefix = "§c§lNotruf §8» §7";
    public String funk_prefix = "§b§lFunk §8» §7";

    private HashMap<Player, String> call = new HashMap<>();
    private ArrayList<Player> call_create = new ArrayList<>();
    public HashMap<Player, String> getCall() {
        return call;
    }

    public ArrayList<Player> getCreateCall() {
        return call_create;
    }

    public String randomLocation(String[] array){
        Random random = new Random();
        int index = random.nextInt(array.length);
        return array[index];
    }
}
