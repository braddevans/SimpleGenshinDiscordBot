package uk.co.breadhub.genshindb;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import uk.co.breadhub.genshindb.command.CommandAddPlayer;
import uk.co.breadhub.genshindb.command.CommandTrack;
import uk.co.breadhub.genshindb.listeners.MessageListener;
import uk.co.breadhub.genshindb.listeners.ReadyListener;
import uk.co.breadhub.genshindb.objects.Player;

import javax.security.auth.login.LoginException;
import java.util.HashMap;

public class DiscordBot {

    private static JDA jda = null;
    private static final HashMap<String, Player> players = new HashMap<>();

    public static void main(String[] args) {
        try {
            CommandClientBuilder client = new CommandClientBuilder();
            client.setOwnerId("100077976342790144");

            // sets the bot prefix
            client.setPrefix("!>");

            // adds commands
            client.addCommands(new CommandTrack(), new CommandAddPlayer());

            JDABuilder builder = JDABuilder.createDefault("Nzg4Mzc1NDExOTkzODcwMzQ2.X9il0Q.9nKocyfztNeSixEaCIDmHL-NJrA");

            // Disable parts of the cache
            builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
            // Enable the bulk delete event
            builder.setBulkDeleteSplittingEnabled(true);
            // Disable compression (not recommended)
            builder.setCompression(Compression.ZLIB);
            builder.addEventListeners(new ReadyListener(), new MessageListener(), client.build());

            builder.setActivity(Activity.listening("AAAAAA"));

            jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public static JDA getJda() {
        return jda;
    }

    public static HashMap<String, Player> getPlayers() {
        return players;
    }
}
