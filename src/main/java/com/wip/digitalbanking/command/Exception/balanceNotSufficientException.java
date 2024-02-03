package com.wip.digitalbanking.command.Exception;

public class balanceNotSufficientException extends  RuntimeException{
    public balanceNotSufficientException(String message){
        super((message));
    }
}
