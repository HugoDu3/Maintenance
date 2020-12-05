package fr.jayrex.maintenance .commands;

import fr.jayrex.maintenance.Maintenance;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MaintenanceCommand extends Command {

    private final Maintenance Maintenance;

    public MaintenanceCommand(Maintenance Maintenance) {
        super("maintenance", "maintenance.toggle");
        this.Maintenance = Maintenance;
        Maintenance.registerCommand(this);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage(new TextComponent(Maintenance.getData().getToggleState().replace("%STATE%", Maintenance.getData().getStateMSG())));
        } else if (strings.length == 1) {
            if (strings[0].equalsIgnoreCase("enable")) {
                if (Maintenance.getData().isEnabled())
                    commandSender.sendMessage(new TextComponent(Maintenance.getData().getAllreadyEnabled()));
                else {
                    Maintenance.getData().setEnabled(true);
                    Maintenance.getFileManager().setMaintenanceMode(true);
                    for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
                        if (!all.hasPermission("maintenance.join"))
                            all.disconnect(new TextComponent(Maintenance.getData().getDisconnectMSG()));
                    }
                    commandSender.sendMessage(new TextComponent(Maintenance.getData().getNowEnabled()));
                }
            } else if (strings[0].equalsIgnoreCase("disable")) {
                if (!Maintenance.getData().isEnabled())
                    commandSender.sendMessage(new TextComponent(Maintenance.getData().getAllreadyDisabled()));
                else {
                    Maintenance.getData().setEnabled(false);
                    Maintenance.getFileManager().setMaintenanceMode(false);
                    commandSender.sendMessage(new TextComponent(Maintenance.getData().getNowDisabled()));
                }
            } else commandSender.sendMessage(new TextComponent(Maintenance.getData() + " §cMauvaise utilisation! Veuillez utilisez /maintenance <enable/disable>"));
        } else commandSender.sendMessage(new TextComponent(Maintenance.getData() + " §cMauvaise utilisation! Veuillez utilisez /maintenance <enable/disable>"));
    }
}

