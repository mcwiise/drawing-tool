package com.drawing.command;

import java.util.List;

public class DrawLineCmd implements Command{

    private static final int NUM_PARAMS = 4;

    private BoardReceiver boardReceiver;
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

        if (!isHorizontalLine(paramsArray) && !isVerticalLine(paramsArray)){
            throw new CommandException("Cannot draw, Line is neither horizontal nor vertical.");
        }

        this.x1 = paramsArray[0];
        this.y1 = paramsArray[1];
        this.x2 = paramsArray[2];
        this.y2 = paramsArray[3];

    }

    private boolean isVerticalLine(int[] paramsArray) {
        return paramsArray[0] == paramsArray[2];

    }

    private boolean isHorizontalLine(int[] paramsArray) {
        return paramsArray[1] == paramsArray[3];
    }


    private boolean isCorrectTypeParams(List<String> params){
        return params.stream().allMatch(e -> {
            try {
                Integer.parseInt(e);
                return true;
            } catch (NumberFormatException nfe) {
                return false;
            }
        });
    }

    @Override
    public void execute() {
        System.out.print(boardReceiver.createLine(x1,y1,x2,y2));
    }
}
