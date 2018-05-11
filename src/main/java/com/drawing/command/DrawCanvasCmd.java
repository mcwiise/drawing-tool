package com.drawing.command;

public class DrawCanvasCmd implements Command{

    private BoardReceiver boardReceiver;

    public DrawCanvasCmd(BoardReceiver boardReceiver){
        this.boardReceiver = boardReceiver;
    }

    @Override
    public void execute() {
        System.out.print(this.boardReceiver.createCanvas());
    }

}
