package me.vahelce.vroleplay.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocationUtils {

    public static List<Player> getPlayersAt(Location location, int range) {
    	List<Player> l = new ArrayList<Player>();
    	try {
    		l.addAll(
    		Bukkit.getOnlinePlayers()
            .parallelStream()
            .filter(p -> {
                if (p.getLocation().equals(location)) return true;
                return (location.getWorld().equals(p.getWorld()) && p.getLocation().distance(location) >= range);
            })
            .collect(Collectors.toList()));
    	}
    	catch (Exception | Error e) {}
        return l;
    }
}

