package at.yawk.bungeebukkitannounce;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Yawkat
 */
public class AnnounceBukkit extends JavaPlugin implements CommandExecutor {
    @Override
    public void onEnable() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "bbannounce");
        getCommand("bbannounce").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission(command.getPermission())) { return false; }

        Player[] online = Bukkit.getOnlinePlayers();
        if (online.length == 0) {
            sender.sendMessage("No players online, cannot connect to bungee!");
            return true;
        }
        online[0].sendPluginMessage(this, "bbannounce", ("SEND|" + Joiner.on(' ').join(args)).getBytes(Charsets.UTF_8));
        return true;
    }
}
