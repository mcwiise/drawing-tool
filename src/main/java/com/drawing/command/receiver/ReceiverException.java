package com.drawing.command.receiver;

/**
 * This class handles and propagate exceptions form the receivers
 * to the upstream layers
 */
public class ReceiverException extends Exception{

    public ReceiverException(String message){
        super(message);
    }
}
