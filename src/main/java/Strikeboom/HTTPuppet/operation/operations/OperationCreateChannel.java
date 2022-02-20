package Strikeboom.HTTPuppet.operation.operations;

import Strikeboom.HTTPuppet.HTTPuppet;
import Strikeboom.HTTPuppet.operation.IOperation;
import Strikeboom.HTTPuppet.operation.InvalidOperationException;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;

/**
 * The Operation For Creating a channel
 */
public class OperationCreateChannel implements IOperation {
    @Override
    public String getUrl() {
        return "/createchannel.html";
    }

    @Override
    public String getName() {
        return "Create Channel";
    }
    /**
     *
     * @param strings@throws InvalidOperationException when one of the parameters are not strings or valid
     */
    @Override
    public void handleOperation(String[] strings) throws InvalidOperationException {
        Guild guild = HTTPuppet.jda.getGuildById(strings[0]);
        if (guild != null) {
            String type = strings[1];
            Category category = guild.getCategoryById(strings[2]);
            if (category != null) {
                if (type.equals("text")) {
                    guild.createTextChannel(strings[3],category).queue(REFRESH_CONSUMER);
                } else if (type.equals("voice")) {
                    guild.createVoiceChannel(strings[3],category).queue(REFRESH_CONSUMER);
                } else {
                    throw new InvalidOperationException("Channel type is not valid!",this);
                }
            } else {
                throw new InvalidOperationException("Category ID is not valid!",this);
            }
        } else {
            throw new InvalidOperationException("Guild ID is not valid!",this);
        }
    }

    @Override
    public String getFileLocation() {
        return "src/main/resources/html/operations/createchannel.html";
    }
}
