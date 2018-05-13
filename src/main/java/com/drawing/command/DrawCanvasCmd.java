package com.drawing.command;

import java.util.List;

public class DrawCanvasCmd implements Command{

    private BoardReceiver boardReceiver;
    private int width;
    private int height;
    private static final int NUM_PARAMS = 2;

    public DrawCanvasCmd(BoardReceiver boardReceiver){
        this.boardReceiver = boardReceiver;
    }

    @Override
    public void setParams(List<String> params) throws CommandException{
        if(params.size() != NUM_PARAMS){
            throw new CommandException("Wrong number of params.");
        } else if (!correctTypeParams(params)) {
            throw new CommandException("Wrong type of params, just ints are accepted");
        } else {
            int[] paramsArray = params.stream().mapToInt(Integer::parseInt).toArray();
            this.width = paramsArray[0];
            this.height = paramsArray[1];
        }
    }

    private boolean correctTypeParams(List<String> params){
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
    public void execute(){
        System.out.println(this.boardReceiver.createCanvas(width, height));
    }

}
