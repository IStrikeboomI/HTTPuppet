package Strikeboom.HTTPuppet.operation.operations;

import Strikeboom.HTTPuppet.HTTPuppet;
import Strikeboom.HTTPuppet.operation.IOperation;
import Strikeboom.HTTPuppet.operation.InvalidOperationException;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;

/**
 * Removes a channel
 */
public class OperationRemoveChannel implements IOperation {
    @Override
    public String getUrl() {
        return "/removechannel.html";
    }

    @Override
    public String getName() {
        return "Remove Channel";
    }

    /**
     *
     *
     * @param strings@throws InvalidOperationException when one of the parameters are not strings or valid
     */
    @Override
    public void handleOperation(String[] strings) throws InvalidOperationException {
        Guild guild = HTTPuppet.jda.getGuildById(strings[0]);
        if (guild != null) {
            GuildChannel channel = guild.getGuildChannelById(strings[1]);
            if (channel != null) {
                channel.delete().queue();
            } else {
                throw new InvalidOperationException("Channel ID is not valid!",this);
            }
        }  else {
            throw new InvalidOperationException("Guild ID is not valid!",this);
        }
    }

    @Override
    public String getFileLocation() {
        return "src/main/resources/html/operations/removechannel.html";
    }
}
