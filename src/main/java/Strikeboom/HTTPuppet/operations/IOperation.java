package Strikeboom.HTTPuppet.operations;

public interface IOperation {
    String getUrl();
    String getName();
    void handleOperation(Object[] objects) throws InvalidOperationException;
}
