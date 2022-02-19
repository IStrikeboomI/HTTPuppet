package Strikeboom.HTTPuppet.operation.operations;

import Strikeboom.HTTPuppet.operation.IOperation;
import Strikeboom.HTTPuppet.webserver.json.JSONFiles;

public class OperationRefresh implements IOperation {
    @Override
    public String getUrl() {
        return "/refresh.html";
    }

    @Override
    public String getName() {
        return "Refresh";
    }

    /**
     *  All it does is refresh the guild json
     * @param objects should be nothing, if something is provided it just ignores it
     */
    @Override
    public void handleOperation(Object[] objects) {
        JSONFiles.guildJSON.refresh();
    }

    @Override
    public String getFileLocation() {
        return "src/main/resources/html/operations/refresh.html";
    }
}
