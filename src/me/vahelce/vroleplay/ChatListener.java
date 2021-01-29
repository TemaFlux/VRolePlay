package me.vahelce.vroleplay;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class ChatListener implements Listener {
    private static final List<Player> maskedPlayers = new ArrayList<>();
    private final String maskName = VRoleplay.getInstance().getConfig().getString("message.commands.mask.name");

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (maskedPlayers.contains(event.getPlayer())) {
            synchronized (event) {
                Player masked = event.getPlayer();
                String oldName = masked.getCustomName();
                if (oldName == null) oldName = event.getPlayer().getName();
                masked.setCustomName(maskName);
                String finalOldName = oldName;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        masked.setCustomName(finalOldName);
                    }
                }.runTaskLater(VRoleplay.getInstance(), 1L);
            }
        }
    }

    public static boolean isPlayerMasked(Player player) {
        return maskedPlayers.contains(player);
    }

    public static void addPlayerToMasks(Player player) {
        maskedPlayers.add(player);
    }

    public static void removePlayerFromMasks(Player player) {
        maskedPlayers.remove(player);
    }
}
