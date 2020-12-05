package fr.jayrex.maintenance.listeners;

import fr.jayrex.maintenance.Maintenance;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LoginListener implements Listener {

    private final Maintenance Maintenance;

    public LoginListener(Maintenance Maintenance) {
        this.Maintenance = Maintenance;
        Maintenance.registerListener(this);
    }

    @EventHandler
    public void onLogin(PostLoginEvent postLoginEvent) {
        ProxiedPlayer proxiedPlayer = postLoginEvent.getPlayer();
        if (Maintenance.getData().isEnabled())
            if (!proxiedPlayer.hasPermission("maintenance.join"))
                proxiedPlayer.disconnect(new TextComponent(Maintenance.getData().getDisconnectMSG()));
    }
}
