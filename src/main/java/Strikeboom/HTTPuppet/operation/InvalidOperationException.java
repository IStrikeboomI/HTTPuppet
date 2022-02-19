package Strikeboom.HTTPuppet.operation;

import Strikeboom.HTTPuppet.logger.Logger;
import Strikeboom.HTTPuppet.logger.TextColors;

public class InvalidOperationException extends Exception{
    public InvalidOperationException(String str,IOperation operation) {
        super(str);
        String name = "";
        if (operation != null) {
            name = operation.getName();
        }
        Logger.getInstance().log(TextColors.RED,new String[]{name},str);
    }
}
