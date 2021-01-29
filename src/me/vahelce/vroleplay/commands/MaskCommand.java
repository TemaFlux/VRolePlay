package me.vahelce.vroleplay.commands;

import me.temaflux.utils.Utils;
import me.vahelce.vroleplay.ChatListener;
import me.vahelce.vroleplay.VRoleplay;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MaskCommand implements CommandExecutor {
    private String enabledMessage = "null";
    private String disabledMessage = "null";
    private String nopermissions = "null";
    private String notplayer = "null";
    
    public MaskCommand() {
        FileConfiguration configuration = VRoleplay.getInstance().getConfig();
        enabledMessage = configuration.getString("message.commands.mask.activate", "null");
        disabledMessage = configuration.getString("message.commands.mask.deactivate", "null");
        nopermissions = configuration.getString("message.general.nopermissions", "null");
        notplayer = configuration.getString("message.general.notplayer", "null");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("vroleplay.mask")) {
                if (ChatListener.isPlayerMasked(player)) {
                    ChatListener.removePlayerFromMasks(player);
                    Utils.sendMessage(sender, disabledMessage);
                } else {
                    ChatListener.addPlayerToMasks(player);
                    Utils.sendMessage(sender, enabledMessage);
                }
                return true;
            }
            Utils.sendMessage(sender, nopermissions);
            return false;
        }
        Utils.sendMessage(sender, notplayer);
        return false;
    }
}
