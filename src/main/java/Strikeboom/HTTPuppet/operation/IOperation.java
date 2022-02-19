package Strikeboom.HTTPuppet.operation;

import java.util.function.Consumer;

public interface IOperation {
    /**
     * This is used for methods that should make new data to call inside the queue() function
     */
    Consumer<? super Object> REFRESH_CONSUMER = o -> {
        try {
            Operations.refresh.handleOperation(null);
        } catch (InvalidOperationException ignored) {}
    };
    /**
     * @return Url where iframe is hosted
     */
    String getUrl();

    /**
     * @return Name of the operation
     */
    String getName();

    /**
     *
     * @param objects this should depend on the specific operation
     * @throws InvalidOperationException when parameters are wrong
     */
    void handleOperation(Object[] objects) throws InvalidOperationException;

    /**
     * @return File location of operation iframe website
     */
    String getFileLocation();
}
