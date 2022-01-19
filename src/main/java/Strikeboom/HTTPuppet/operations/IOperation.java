package Strikeboom.HTTPuppet.operations;

public interface IOperation {
    void handleOperation(Object[] objects) throws InvalidOperationException;
}
