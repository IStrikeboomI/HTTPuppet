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
     * @param objects first object should be the guild id,
     *                second object should be the type of channel (text or voice chanel),
     *                third object should be the channel category,
     *                fourth object should be the channel name,
     * @throws InvalidOperationException when one of the parameters are not strings or valid
     */
    @Override
    public void handleOperation(Object[] objects) throws InvalidOperationException {
        for (int i = 0;i<objects.length;i++) {
            if (!(objects[i] instanceof String)) {
                throw new InvalidOperationException("Parameter " + i + " is not a string!",this);
            }
        }
        Guild guild = HTTPuppet.jda.getGuildById((String) objects[0]);
        if (guild != null) {
            String type = (String) objects[1];
            Category category = guild.getCategoryById((String) objects[2]);
            if (category != null) {
                if (type.equals("text")) {
                    guild.createTextChannel((String) objects[3],category).queue(REFRESH_CONSUMER);
                } else if (type.equals("voice")) {
                    guild.createVoiceChannel((String) objects[3],category).queue(REFRESH_CONSUMER);
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
