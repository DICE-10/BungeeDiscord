package io.github.dice10.bungeediscord;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class EventListener implements Listener {
    private File file;
    private Configuration config;
    private int chatLinkFlg = 0;

    protected void DiscordToBungeeCord(String name,String sendMessage) throws IOException {
        file = new File(ProxyServer.getInstance().getPluginsFolder() + "/BungeeDiscord/discord_config.yml");
        config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        chatLinkFlg = (int) config.get("chatLink");
        TextComponent msg;
        if (chatLinkFlg == 1) {
            msg = new TextComponent("§b§n["+name+"@Discord]§f"+sendMessage);
            msg.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.google.com"));
        }
        else{
            msg = new TextComponent("§b["+name+"@Discord]§f"+sendMessage);
        }
        ProxyServer.getInstance().broadcast(msg);
    }

}
