package uk.co.breadhub.genshindb.command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import uk.co.breadhub.genshindb.DiscordBot;
import uk.co.breadhub.genshindb.objects.Player;
import uk.co.breadhub.genshindb.utils.TableRenderer;
import uk.co.breadhub.genshindb.utils.Utils;

import java.util.List;

public class CommandTrack extends Command {

    public CommandTrack() {
        this.name = "getPlayers";
        this.help = "get All Tracked in Memory Genshin Players";
        this.arguments = "";
        this.guildOnly = false;
    }

    private void handleSeperation(StringBuilder builder) {
        if (builder.length() != 0) { builder.append("\n\n"); }
    }

    private void formatPlayers(StringBuilder builder, Guild guild, MessageChannel channel) {
        handleSeperation(builder);

        TableRenderer table = new TableRenderer();
        table.setHeader("UID", "Username", "Region");
        for (Player player : DiscordBot.getPlayers().values()) {
            table.addRow(player.getUid(), player.getUsername(), player.getRegion());
        }
        builder.append(table.build());
    }

    @Override
    protected void execute(CommandEvent event) {
        Guild guild = event.getGuild();
        MessageChannel channel = event.getChannel();
        StringBuilder builder = new StringBuilder();

        String[] items = event.getArgs().split("\\s+");
        formatPlayers(builder, guild, channel);

        List<String> result = Utils.messageSplit(builder.toString(), 2000 - 6);
        for (String part : result) {
            event.replySuccess("```" + part + "```");
        }
    }

}
