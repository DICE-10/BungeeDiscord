package io.github.dice10.bungeediscord;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Listener;

public class EventListener implements Listener {
    protected void DiscordToBungeeCord(String name,String sendMessage){
        TextComponent msg = new TextComponent("§b§n["+name+"@Discord]§f"+sendMessage);
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.google.com"));
        ProxyServer.getInstance().broadcast(msg);
    }

}
