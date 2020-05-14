package io.github.dice10.bungeediscord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordEvents extends ListenerAdapter {
    private final JDA api;

    public DiscordEvents(JDA api) {
        this.api = api;
    }


    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String messageSent = event.getMessage().getContentRaw();
        String name = event.getMember().getUser().getName();
        if(event.getMember().getUser().isBot()){
            return;
        }

        if(messageSent.equalsIgnoreCase("Hello")){
            if(!event.getMember().getUser().isBot()) {
                event.getChannel().sendMessage("Hi!!"+name).queue();
            }
        }
        EventListener eventListener = new EventListener();
        eventListener.DiscordToBungeeCord(name,messageSent);
    }

}
