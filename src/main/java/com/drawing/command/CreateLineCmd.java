package com.drawing.command;

import com.drawing.board.Point;
import com.drawing.command.receiver.LineReceiver;
import com.drawing.command.receiver.ReceiverException;

import java.util.List;

public class CreateLineCmd extends AbstractCmd implements Command{

    private static final int NUM_PARAMS = 4;

    private LineReceiver lineReceiver;
    private int x1;
    private int y1;
    private int x2;
    private int y2;


    @Override
    public void setParams(List<String> params) throws CommandException {
        if(params.size() != NUM_PARAMS){
            throw new CommandException("Wrong number of params.");
        } else if (!isCorrectTypeParams(params)) {
            throw new CommandException("Wrong type of params, just ints are accepted");
        }

        int[] paramsArray = params.stream().mapToInt(Integer::parseInt).toArray();

        this.x1 = paramsArray[0];
        this.y1 = paramsArray[1];
        this.x2 = paramsArray[2];
        this.y2 = paramsArray[3];
    }

    @Override
    public void execute() throws CommandException{
        try {
            List<Point<Integer, Integer>> path = this.lineReceiver.computePath(x1,y1,x2,y2);
            this.lineReceiver.drawLine(path);
        } catch (ReceiverException e) {
            e.printStackTrace();
            throw new CommandException("ReceiverException when executing create line command. ", e);
        }
    }
}
