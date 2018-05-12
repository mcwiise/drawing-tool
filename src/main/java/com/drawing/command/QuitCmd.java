package com.drawing.command;

import java.util.List;

public class QuitCmd implements Command {

    @Override
    public void setParams(List<String> params) throws CommandException {

    }

    @Override
    public void execute() {
        System.out.println("Bye!!");
    }
}
