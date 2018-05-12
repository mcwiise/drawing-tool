package com.drawing.command;

public class QuitCmd implements Command {

    @Override
    public void execute() {
        System.out.println("Bye!!");
    }
}
