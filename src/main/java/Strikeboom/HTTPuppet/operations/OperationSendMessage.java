package Strikeboom.HTTPuppet.operations;

import net.dv8tion.jda.api.entities.TextChannel;

/**
    The operation for sending a message requires a channel id and string content
    first object is channel
    second is string
 */
public class OperationSendMessage implements IOperation{
    @Override
    public void handleOperation(Object[] objects) throws InvalidOperationException {
        if (!(objects[0] instanceof TextChannel)) {
            throw new InvalidOperationException("First parameter is not a channel!");
        }
        if (!(objects[1] instanceof String)) {
            throw new InvalidOperationException("Second parameter is not a string!");
        }
        TextChannel channel = (TextChannel) objects[0];
        String content = (String) objects[1];

        channel.sendMessage(content).queue();
    }
}
