package com.drawing.command;

import com.drawing.command.receiver.CanvasReceiver;
import com.drawing.command.receiver.ReceiverException;

import java.util.List;

/**
 * This is an concrete implementation of a {@link Command}. Defines
 * the logic to create a Canvas in the Board
 */
public class CreateCanvasCmd extends AbstractCmd implements Command{

    private CanvasReceiver canvasReceiver;
    private int width;
    private int height;
    private static final int NUM_PARAMS = 2;

    public CreateCanvasCmd(CanvasReceiver canvasReceiver){
        this.canvasReceiver = canvasReceiver;
    }

    @Override
    public void setParams(List<String> params) throws CommandException{
        if(params.size() != NUM_PARAMS){
            throw new CommandException("Wrong number of params.");
        } else if (!isCorrectTypeParams(params)) {
            throw new CommandException("Wrong type of params, just ints are accepted");
        } else {
            int[] paramsArray = params.stream().mapToInt(Integer::parseInt).toArray();
            this.width = paramsArray[0];
            this.height = paramsArray[1];
        }
    }

    @Override
    public void execute() throws CommandException{
        try {
            this.canvasReceiver.drawCanvas(width, height);
        } catch (ReceiverException e) {
            throw new CommandException("asdfadf");
        }
    }

}
