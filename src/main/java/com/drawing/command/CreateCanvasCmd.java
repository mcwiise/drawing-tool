package com.drawing.command;

public class CreateCanvasCmd implements Command{

    private BoardReceiver boardReceiver;

    public CreateCanvasCmd(BoardReceiver boardReceiver){
        this.boardReceiver = boardReceiver;
    }

    @Override
    public void execute() {
        System.out.print(boardReceiver.createCanvas());
    }

}
