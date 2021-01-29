package me.temaflux.utils;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

public class Utils {
	public static String parseColor(String arg1) {
		try {
			return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', arg1);
		}
		catch (Exception | Error e) {
			return org.bukkit.ChatColor.translateAlternateColorCodes('&', arg1);
		}
	}
	
	public static void sendMessage(CommandSender sender, String... strings) {
		sendMessage(sender, Arrays.asList(strings));
	}
	
	public static void sendMessage(CommandSender sender, List<String> strings) {
		strings.forEach(s -> sender.sendMessage(parseColor(s)));
	}
}
