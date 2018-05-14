package com.drawing.command;

import java.util.List;

public class QuitCmd extends AbstractCmd implements Command {

    @Override
    public void setParams(List<String> params) throws CommandException {
        //Not necessary to implement
    }

    @Override
    public void execute() {
        System.out.println("Bye!!");
    }
}
