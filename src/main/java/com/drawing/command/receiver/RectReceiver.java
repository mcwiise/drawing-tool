package com.drawing.command.receiver;

import com.drawing.board.Board;
import com.drawing.board.Point;

import java.util.List;
import java.util.Optional;

public class RectReceiver extends AbstractReceiver{

    public void drawRectangle(List<Point<Integer,Integer>> path1,
                              List<Point<Integer,Integer>> path2,
                              List<Point<Integer,Integer>> path3,
                              List<Point<Integer,Integer>> path4) throws ReceiverException {

        Optional gridOpt = Optional.ofNullable(Board.getInstance().getGrid());
        if(!gridOpt.isPresent()){
            throw new ReceiverException("Please, draw a canvas first. ");
        } else {
            String[][] grid = (String[][]) gridOpt.get();
            path1.forEach(point -> grid[point.getY()][point.getX()] = "x");
            path2.forEach(point -> grid[point.getY()][point.getX()] = "x");
            path3.forEach(point -> grid[point.getY()][point.getX()] = "x");
            path4.forEach(point -> grid[point.getY()][point.getX()] = "x");
            Board.getInstance().setGrid(grid);
        }
        System.out.println(Board.getInstance().toString());
    }
}
