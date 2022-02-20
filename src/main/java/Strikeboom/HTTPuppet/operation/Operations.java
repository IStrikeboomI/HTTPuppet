package Strikeboom.HTTPuppet.operation;

import Strikeboom.HTTPuppet.operation.operations.OperationCreateChannel;
import Strikeboom.HTTPuppet.operation.operations.OperationRefresh;
import Strikeboom.HTTPuppet.operation.operations.OperationRemoveChannel;
import Strikeboom.HTTPuppet.operation.operations.OperationSendMessage;

import java.util.ArrayList;
import java.util.List;

public class Operations {
    public static final List<IOperation> OPERATIONS = new ArrayList<>();

    public static IOperation refresh;
    public static IOperation sendMessage;
    public static IOperation createChannel;
    public static IOperation removeChannel;

    public static void init() {
        refresh = new OperationRefresh();
        sendMessage = new OperationSendMessage();
        createChannel = new OperationCreateChannel();
        removeChannel = new OperationRemoveChannel();

        OPERATIONS.add(refresh);
        OPERATIONS.add(sendMessage);
        OPERATIONS.add(createChannel);
        OPERATIONS.add(removeChannel);
    }
}
