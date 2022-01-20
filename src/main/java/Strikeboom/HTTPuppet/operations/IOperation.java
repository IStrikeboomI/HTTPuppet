package Strikeboom.HTTPuppet.operations;

public interface IOperation {
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
}
