package Strikeboom.HTTPuppet.operations;

public class InvalidOperationException extends Exception{
    public InvalidOperationException() {super("Invalid parameters");}
    public InvalidOperationException(String str) {super(str);}
}
