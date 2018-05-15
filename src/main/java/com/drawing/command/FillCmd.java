package com.drawing.command;

import com.drawing.board.Point;
import com.drawing.command.receiver.FillReciever;
import com.drawing.command.receiver.ReceiverException;

import java.util.List;

public class FillCmd  extends AbstractCmd implements Command {

    private static final int NUM_PARAMS = 3;
    private FillReciever fillReciever;
    private List<String> params;
    private int x1;
    private int y1;
    private String filler;

    public FillCmd(FillReciever fillReciever){
        this.fillReciever = fillReciever;
    }

    @Override
    public void setParams(List<String> params) throws CommandException {
        if(params.size() != NUM_PARAMS){
            throw new CommandException("Wrong number of params.");
        }
        try {
            x1 = Integer.parseInt(params.get(0));
            y1 = Integer.parseInt(params.get(1));
            filler = params.get(2);
        } catch (NumberFormatException e){
            throw new CommandException("params are not properly set");
        }
    }

    @Override
    public void execute() throws CommandException {
        try {
            Point<Integer, Integer> point = new Point<>(x1, y1);
            if(this.fillReciever.isPointOnCanvas(point)){
                this.fillReciever.fill(point, filler);
            } else {
                throw new CommandException("Point to start filling out of canvas");
            }
        } catch (ReceiverException e) {
            System.out.println(e.getMessage());
            throw new CommandException("Cannot fill");
        }
    }
}
