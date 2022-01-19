package Strikeboom.HTTPuppet.operations;

import java.util.ArrayList;
import java.util.List;

public class Operations {
    public static final List<IOperation> OPERATIONS = new ArrayList<>();
    public static void init() {
        OPERATIONS.add(new OperationSendMessage());
    }
}
