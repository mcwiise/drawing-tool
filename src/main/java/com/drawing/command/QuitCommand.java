package com.drawing.command;

public class QuitCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Bye");
        System.exit(0);
    }
}
