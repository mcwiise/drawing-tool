package com.drawing.command;

import com.drawing.board.Line;
import com.drawing.board.Point;
import com.drawing.command.receiver.ReceiverException;
import com.drawing.command.receiver.RectReceiver;

import java.util.List;

public class CreateRectCmd extends AbstractCmd implements Command {

    private static final int NUM_PARAMS = 4;
    private RectReceiver rectReceiver;

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public CreateRectCmd(RectReceiver rectReceiver) {
        this.rectReceiver = rectReceiver;
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
    public void execute() throws CommandException {
        try {
            Line line1 = new Line(new Point<>(x1, y1), new Point<>(x2,y1));
            Line line2 = new Line(new Point<>(x2, y1), new Point<>(x2,y2));
            Line line3 = new Line(new Point<>(x2, y2), new Point<>(x1,y2));
            Line line4 = new Line(new Point<>(x1, y2), new Point<>(x1,y1));

            if(this.rectReceiver.isRectangleInCanvas(line1, line2, line3, line4)){
                List<List<Point<Integer, Integer>>> paths = this.rectReceiver.computePaths(line1, line2, line3, line4);
                this.rectReceiver.drawRectangle(paths);
            } else {
                throw new CommandException("Rectangle is out of canvas.");
            }
        } catch (ReceiverException e) {
            System.out.println(e.getMessage());
            throw new CommandException("Cannot create rectangle. ");
        }
    }
}
