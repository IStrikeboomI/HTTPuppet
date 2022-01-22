package Strikeboom.HTTPuppet.operations;

import Strikeboom.HTTPuppet.HTTPuppet;
import net.dv8tion.jda.api.entities.Guild;

/**
 * The Operation For Creating a channel
 */
public class OperationCreateChannel implements IOperation{
    @Override
    public String getUrl() {
        return "/createchannel.html";
    }

    @Override
    public String getName() {
        return "Create Channel";
    }
    /**
     * @param objects first object should be the guild id in a String
     *                second object should be the channel name in a String
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
            guild.createTextChannel((String)objects[1]).queue();
        } else {
            throw new InvalidOperationException("Guild ID is not valid!");
        }
    }

    @Override
    public String getFileLocation() {
        return "src/main/resources/html/operations/createchannel.html";
    }
}
