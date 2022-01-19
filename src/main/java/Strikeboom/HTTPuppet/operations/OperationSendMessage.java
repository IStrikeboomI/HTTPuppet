package Strikeboom.HTTPuppet.operations;

import Strikeboom.HTTPuppet.HTTPuppet;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

/**
    The operation for sending a message requires a channel id and string content
    first object is guild
    second is channel
    third is string
 */
public class OperationSendMessage implements IOperation{
    @Override
    public String getUrl() {
        return "/sendmessage";
    }

    @Override
    public String getName() {
        return "Send Message";
    }

    @Override
    public void handleOperation(Object[] objects) throws InvalidOperationException {
        for (int i = 0;i<objects.length;i++) {
            if (!(objects[i] instanceof String)) {
                throw new InvalidOperationException("Parameter " + i + " is not a string!");
            }
        }
        Guild guild = HTTPuppet.jda.getGuildById((String) objects[0]);
        if (guild != null) {
            TextChannel channel = guild.getTextChannelById((String) objects[1]);
            if (channel != null) {
                channel.sendMessage((String) objects[2]).queue();
            } else {
                throw new InvalidOperationException("Channel ID is not valid!");
            }
        } else {
            throw new InvalidOperationException("Guild ID is not valid!");
        }
    }
}
