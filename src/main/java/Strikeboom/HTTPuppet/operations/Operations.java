package Strikeboom.HTTPuppet.operations;

import java.util.ArrayList;
import java.util.List;

public class Operations {
    public static final List<IOperation> OPERATIONS = new ArrayList<>();

    public static IOperation refresh;
    public static IOperation sendMessage;
    public static IOperation createChannel;

    public static void init() {
        refresh = new OperationRefresh();
        sendMessage = new OperationSendMessage();
        createChannel = new OperationCreateChannel();

        OPERATIONS.add(refresh);
        OPERATIONS.add(sendMessage);
        OPERATIONS.add(createChannel);
    }
}
