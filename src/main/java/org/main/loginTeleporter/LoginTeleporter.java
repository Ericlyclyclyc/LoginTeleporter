package org.main.loginTeleporter;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LoginTeleporter extends JavaPlugin implements Listener, CommandExecutor {

    private Location teleportLocation;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("loatp").setExecutor(this);
        this.getLogger().info(ChatColor.GREEN+"Enabled"+ ChatColor.LIGHT_PURPLE+" LoginTeleporter");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (teleportLocation != null) {
            player.teleport(teleportLocation);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("loatp")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("teleport.set")) {
                    teleportLocation = player.getLocation();
                    player.sendMessage("传送点已设置为: " + teleportLocation.toString());
                    return true;
                } else {
                    player.sendMessage("你没有权限使用此命令。");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onDisable()
    {
        this.getLogger().info(ChatColor.RED+"Disabled"+ ChatColor.LIGHT_PURPLE+" LoginTeleporter");
    }
}