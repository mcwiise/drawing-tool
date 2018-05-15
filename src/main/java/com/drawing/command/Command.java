package com.drawing.command;

import java.util.List;

/**
 * This is a implementation of the Command Pattern. This interface defines the behavior
 * of all the Commands that the application supports.
 */
public interface Command {

    /**
     * Sets the parameters what the given command is expecting for
     * @param params the list of parameters
     * @throws CommandException if there is a problem setting the parameters to the command
     */
    public void setParams(List<String> params) throws CommandException;

    /**
     * Executes the logic defined for the given command
     * @throws CommandException if there is a problem executing the command
     */
    public void execute() throws CommandException;
}
