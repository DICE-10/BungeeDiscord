package io.github.dice10.bungeediscord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.md_5.bungee.api.plugin.Listener;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;

import javax.security.auth.login.LoginException;

public class Discord extends AbstractAppender implements Listener {
    protected JDA jda;
    protected DiscordEvents discordEvents;

    protected Discord() {
        super("MyLogAppender", null, null);
        start();
    }

    public void DiscordMain() throws LoginException, InterruptedException {
        System.out.println("BOT");
        setJDA("NzA4OTQ3NjQ4NTU1NTE1OTY2.XrexIQ.6BtM2oiURa4CbjEdjQyXROr34xM");
        jda.awaitReady();
        this.discordEvents = new DiscordEvents(getJDA());
        jda.addEventListener(this.discordEvents);
    }

    public void setJDA(String token) throws LoginException {
        this.jda = new JDABuilder(token).build();
    }

    public JDA getJDA(){
        return this.jda;
    }

    @Override
    public void append(LogEvent event) {
        try {
            DiscordMain();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
        // if you don`t make it immutable, than you may have some unexpected behaviours
        LogEvent log = event.toImmutable();

        // do what you have to do with the log

        // you can get only the log message like this:
        String message = log.getMessage().getFormattedMessage();
        jda.getTextChannelById("669525705125658628").sendMessage(message);
    }
}
