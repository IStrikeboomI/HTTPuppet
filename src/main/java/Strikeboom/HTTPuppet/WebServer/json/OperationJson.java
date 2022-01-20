package Strikeboom.HTTPuppet.WebServer.json;

import Strikeboom.HTTPuppet.operations.IOperation;
import Strikeboom.HTTPuppet.operations.Operations;
import org.json.JSONArray;
import org.json.JSONObject;

public class OperationJson implements IJSON {
    final private JSONArray array;
    public OperationJson() {
        array = new JSONArray();
        add();
    }

    @Override
    public void add() {
        for (IOperation operation : Operations.OPERATIONS) {
            JSONObject object = new JSONObject();
            object.put("url",operation.getUrl());
            object.put("name",operation.getName());

            array.put(object);
        }
    }

    @Override
    public String getArrayString() {
        return array.toString();
    }

    @Override
    public String getHostedUrl() {
        return "/operations.json";
    }
}
