package at.yawk.bungeebukkitannounce;

import com.google.common.base.Charsets;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

/**
 * @author Yawkat
 */
public class AnnounceBungee extends Plugin implements Listener {
    @Override
    public void onEnable() {
        getProxy().registerChannel("bbannounce");
        getProxy().getPluginManager().registerListener(this, this);
    }

    @EventHandler
    public void onMessage(PluginMessageEvent event) {
        if (event.getSender() instanceof Server) {
            String message = new String(event.getData(), Charsets.UTF_8);

            if (!message.startsWith("SEND|")) { return; }
            message = message.substring(5);

            // from alert command source
            StringBuilder builder = new StringBuilder();
            if (message.startsWith("&h")) {
                // Remove &h
                message = message.substring(2, message.length());
            } else {
                builder.append(ProxyServer.getInstance().getTranslation("alert"));
            }
            builder.append(ChatColor.translateAlternateColorCodes('&', message));
            getProxy().broadcast(builder.toString());

            event.setCancelled(true);
        }
    }
}
