package Strikeboom.HTTPuppet.WebServer.json;

import java.util.ArrayList;
import java.util.List;

public class JSONFiles {
    public static final List<IJSON> JSON_FILES = new ArrayList<>();
    public static void init() {
        JSON_FILES.add(new GuildJson());
        JSON_FILES.add(new OperationJson());

        for (IJSON ijson : JSON_FILES) {
            ijson.add();
        }
    }
}