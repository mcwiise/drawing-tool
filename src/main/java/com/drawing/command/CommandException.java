package com.drawing.command;

public class CommandException extends Exception {

    public CommandException(){
        super();
    }

    public CommandException(String message){
        super(message);
    }
}
