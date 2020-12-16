package uk.co.breadhub.genshindb.command;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import uk.co.breadhub.genshindb.DiscordBot;
import uk.co.breadhub.genshindb.objects.Player;

public class CommandAddPlayer extends Command {

    public CommandAddPlayer() {
        this.name = "addPlayer";
        this.help = "Insert Player into Temp Memory";
        this.arguments = "<username> <UID>";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        Guild guild = event.getGuild();
        MessageChannel channel = event.getChannel();
        StringBuilder builder = new StringBuilder();

        if (event.getArgs().isEmpty()) {
            event.replyWarning("<username> <UID>!");
        }
        else {
            String[] args = event.getArgs().split("\\s+");
            if (args.length == 1) { event.replyWarning("You only gave me one option, `" + args[0] + "`"); }
            else {
                DiscordBot.getPlayers().put(args[0],new Player(args[0],args[1]));
                event.replySuccess("User: " + args[0] + " UID: " + args[1]);
            }
        }
    }

}
