package com.drawing.command;

import com.drawing.board.Line;
import com.drawing.board.Point;
import com.drawing.command.receiver.LineReceiver;
import com.drawing.command.receiver.ReceiverException;

import java.util.List;

/**
 * This is a concrete implementation of a {@link Command}.
 * It defines the logic to create a {@link Line} in the canvas
 */
public class CreateLineCmd extends AbstractCmd implements Command{

    private static final int NUM_PARAMS = 4;

    private LineReceiver lineReceiver;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public CreateLineCmd (LineReceiver lineReceiver){
        this.lineReceiver = lineReceiver;
    }

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
            Point<Integer, Integer> from = new Point<>(x1,y1);
            Point<Integer, Integer> to = new Point<>(x2,y2);
            Line line = new Line(from, to);

            if(this.lineReceiver.isLineOnCanvas(line)){
                List<Point<Integer, Integer>> path = this.lineReceiver.computePath(line);
                this.lineReceiver.drawLine(path);
            } else {
                throw new CommandException("Line is out of canvas.");
            }
        } catch (ReceiverException e) {
            System.out.println(e.getMessage());
            throw new CommandException("Cannot create line. ");
        }
    }
}
