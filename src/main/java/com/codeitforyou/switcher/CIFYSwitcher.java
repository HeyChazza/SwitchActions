package com.codeitforyou.switcher;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.util.concurrent.TimeUnit;

public class CIFYSwitcher extends Plugin implements Listener {

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, this);
    }

    @EventHandler
    public void onSwitch(ServerSwitchEvent e) {
        ServerInfo currentServer = e.getPlayer().getServer().getInfo();

        getProxy().getScheduler().schedule(this, new Runnable() {
            @Override
            public void run() {
                ServerInfo newServer = e.getPlayer().getServer().getInfo();

                if (currentServer.getName().equalsIgnoreCase(newServer.getName())) {
                    getProxy().broadcast(e.getPlayer().getName() + " switched from " + currentServer.getName() + " to " + newServer.getName());
                }
            }
        }, 1, TimeUnit.SECONDS);
    }
}
