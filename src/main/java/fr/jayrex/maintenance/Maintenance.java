package fr.jayrex.maintenance;

import fr.jayrex.maintenance.commands.MaintenanceCommand;
import fr.jayrex.maintenance.listeners.LoginListener;
import fr.jayrex.maintenance.managers.FileManager;
import fr.jayrex.maintenance.utils.Data;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class Maintenance extends Plugin {

    private Data data;
    private FileManager fileManager;

    public void onEnable() {
        CommandSender sender = this.getProxy().getConsole();
        sender.sendMessage(new TextComponent(this.getDescription().getName() + " Maintenance " + data.isEnabled()));
    }

    private void init() {
        data = new Data();
        fileManager = new FileManager(this);
        fileManager.createConfig();
        fileManager.loadConfig();
        fileManager.getDataFromConfig();
        new MaintenanceCommand(this);
        new LoginListener(this);
    }

    public void registerCommand(Command command) {
        this.getProxy().getPluginManager().registerCommand(this, command);
    }

    public void registerListener(Listener listener) {
        this.getProxy().getPluginManager().registerListener(this, listener);
    }

    public Data getData() {
        return data;
    }

    public FileManager getFileManager() {
        return fileManager;
    }
}
