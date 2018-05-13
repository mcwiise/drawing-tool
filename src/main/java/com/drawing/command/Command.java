package com.drawing.command;

import java.util.List;

public interface Command {
    public void setParams(List<String> params) throws CommandException;
    public void execute() throws CommandException;
}
