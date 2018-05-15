package com.drawing.command;

/**
 * This is used to handle and propagate exceptions from the command layer
 */
public class CommandException extends Exception {

    public CommandException(String message){
        super(message);
    }
}
