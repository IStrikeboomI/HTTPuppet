package Strikeboom.HTTPuppet.operation;

import Strikeboom.HTTPuppet.operation.operations.*;

import java.util.ArrayList;
import java.util.List;

public class Operations {
    public static final List<IOperation> OPERATIONS = new ArrayList<>();

    public static IOperation refresh;
    public static IOperation sendMessage;
    public static IOperation createChannel;
    public static IOperation removeChannel;
    public static IOperation renameChannel;

    public static void init() {
        refresh = new OperationRefresh();
        sendMessage = new OperationSendMessage();
        createChannel = new OperationCreateChannel();
        removeChannel = new OperationRemoveChannel();
        renameChannel = new OperationRenameChannel();

        OPERATIONS.add(refresh);
        OPERATIONS.add(sendMessage);
        OPERATIONS.add(createChannel);
        OPERATIONS.add(removeChannel);
        OPERATIONS.add(renameChannel);
    }
}
