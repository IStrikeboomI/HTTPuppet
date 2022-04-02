package Strikeboom.HTTPuppet.operation.operations;

import Strikeboom.HTTPuppet.HTTPuppet;
import Strikeboom.HTTPuppet.operation.IOperation;
import Strikeboom.HTTPuppet.operation.InvalidOperationException;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;

/**
 * Operation for renaming a channel
 */
public class OperationRenameChannel implements IOperation {
    @Override
    public String getUrl() {
        return "/renamechannel.html";
    }

    @Override
    public String getName() {
        return "Rename Channel";
    }

    /**
     *
     * @param strings@throws InvalidOperationException when one of the parameters are not strings or valid
     */
    @Override
    public void handleOperation(String[] strings) throws InvalidOperationException {
        Guild guild = HTTPuppet.jda.getGuildById(strings[0]);
        if (guild != null) {
            GuildChannel channel = guild.getGuildChannelById(strings[1]);
            if (channel != null) {
                channel.getManager().setName(strings[2]).queue();
            } else {
                throw new InvalidOperationException("Channel ID is not valid!",this);
            }
        }  else {
            throw new InvalidOperationException("Guild ID is not valid!",this);
        }
    }

    @Override
    public String getFileLocation() {
        return "src/main/resources/html/operations/renamechannel.html";
    }
}
