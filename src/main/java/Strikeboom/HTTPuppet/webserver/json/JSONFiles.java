package Strikeboom.HTTPuppet.webserver.json;

import java.util.ArrayList;
import java.util.List;

public class JSONFiles {
    public static final List<IJSON> JSON_FILES = new ArrayList<>();

    public static IJSON guildJSON;
    public static IJSON operationJSON;

    public static void init() {
        guildJSON = new GuildJson();

        JSON_FILES.add(guildJSON);
        JSON_FILES.add(operationJSON);
    }
}
