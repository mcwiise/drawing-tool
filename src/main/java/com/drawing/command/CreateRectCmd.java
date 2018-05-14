package com.drawing.command;

import com.drawing.board.Point;
import com.drawing.command.receiver.LineReceiver;
import com.drawing.command.receiver.ReceiverException;
import com.drawing.command.receiver.RectReceiver;

import java.util.List;

public class CreateRectCmd extends AbstractCmd implements Command {

    private static final int NUM_PARAMS = 4;
    private RectReceiver rectReceiver;
    private LineReceiver lineReceiver;

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public CreateRectCmd(RectReceiver rectReceiver, LineReceiver lineReceiver) {
        this.rectReceiver = rectReceiver;
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
    public void execute() throws CommandException {
        try {
            List<Point<Integer, Integer>> path1 = this.lineReceiver.computePath(x1, y1, x2, y1);
            List<Point<Integer, Integer>> path2 = this.lineReceiver.computePath(x2, y1, x2, y2);
            List<Point<Integer, Integer>> path3 = this.lineReceiver.computePath(x2, y2, x1, y2);
            List<Point<Integer, Integer>> path4 = this.lineReceiver.computePath(x1, y2, x1, y1);

            this.rectReceiver.drawRectangle(path1,path2, path3, path4);
        } catch (ReceiverException e) {
            System.out.println(e.getMessage());
            throw new CommandException("Cannot create rectangle. ");
        }
    }
}
