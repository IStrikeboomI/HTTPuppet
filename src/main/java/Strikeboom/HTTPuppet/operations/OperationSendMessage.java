package Strikeboom.HTTPuppet.operations;

import Strikeboom.HTTPuppet.HTTPuppet;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

/**
    The operation for sending a message to a channel
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

    /**
     * @param objects first object should be the guild id in a String
     *                second object should be the channel id in a String
     *                third object should be the string content to be sent
     * @throws InvalidOperationException when one of the parameters are not strings or valid
     */
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
