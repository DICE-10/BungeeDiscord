package io.github.dice10.bungeediscord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;

public class BungeeDiscord extends Plugin implements Listener {
    private static final  Logger logger = (org.apache.logging.log4j.core.Logger) LogManager.getRootLogger();
    protected JDA jda;
    protected DiscordEvents discordEvents;
    protected String senderServer;
    private File file;
    private Configuration config;
    private String Token;
    private long channel_ID;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getProxy().getPluginManager().registerListener(this, this);
        getProxy().registerChannel("my:channel");

//        Discord discord = new Discord();
        file = new File(getDataFolder(),"/discord_config.yml");
        getLogger().info(getDataFolder().toString());

        try {
            if(!getDataFolder().exists()){
                getDataFolder().mkdir();
            }
            if(!file.exists()){
                file.createNewFile();
                getProxy().getConsole().sendMessage("§6Discord's BOT won't start because BOT's token and");
                getProxy().getConsole().sendMessage("§6channel ID are not specified in the configuration file.");
                config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
                config.set("TOKEN","xxxxxxxxxxx");
                config.set("TextChannel_ID","xxxxxxxxxxx");
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(config,file);
            }
            else{

                DiscordMain();
            }

        } catch (LoginException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        LogAppender appender = new LogAppender();
        logger.addAppender(appender);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onChat(ChatEvent event)  {
        // コマンド実行の場合は、そのまま無視する
        if ( event.isCommand() ) {
            return;
        }

        // プレイヤーの発言ではない場合は、そのまま無視する
        if ( !(event.getSender() instanceof ProxiedPlayer) ) {
            return;
        }

        // 発言者と発言サーバーと発言内容の取得
        final ProxiedPlayer sender = (ProxiedPlayer)event.getSender();
        this.senderServer = sender.getServer().getInfo().getName();
        String message = event.getMessage();
        Discord discord = new Discord();
        String nameOnServer = "["+sender+"@"+senderServer+"]";
        long l =  665193946997194755L;
        if(jda != null) {
            TextChannel txtChannel = jda.getTextChannelById(l);
            if (txtChannel.canTalk()) {
                txtChannel.sendMessage(nameOnServer+message).queue();
            }
        }
    }
    public void DiscordMain() throws LoginException, InterruptedException {
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

    public void setToken(String token){
        this.Token = token;
    }

    public String getToken(){
        return this.Token;
    }

    public void setID(long id){
        this.channel_ID = id;
    }

    public long getID(){
        return this.channel_ID;
    }

}
