package uk.co.breadhub.genshindb.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            if (event.getChannel().getId().equals("788363695721938955")) {
                if (!event.getAuthor().isBot()) {
                    System.out.println("[" + event.getGuild().getName() + "] [" + event.getTextChannel().getName() + "] " + event.getMember().getEffectiveName() + " : " + event.getMessage().getContentDisplay());
                }
            }
        } catch (Exception ignored) {}
    }
}