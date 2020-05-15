package io.github.dice10.bungeediscord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class DiscordEvents extends ListenerAdapter {
    private final JDA api;
    private File file;
    private Configuration config;

    public DiscordEvents(JDA api) {
        this.api = api;
    }


    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String messageSent = event.getMessage().getContentRaw();
        String name = event.getMember().getNickname();
        if(name == null) {
            name = event.getMember().getUser().getName();
        }
        name = name.replaceAll("[\\uD800-\\uDFFF]", "");
        file = new File(ProxyServer.getInstance().getPluginsFolder() + "/BungeeDiscord/discord_config.yml");
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            return;
        }
        if(event.getChannel().getIdLong() != (Long) config.get("TextChannel_ID")){
            return;
        }
        if(event.getMember().getUser().isBot()){
            return;
        }
        EventListener eventListener = new EventListener();
        try {
            eventListener.DiscordToBungeeCord(name,messageSent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
