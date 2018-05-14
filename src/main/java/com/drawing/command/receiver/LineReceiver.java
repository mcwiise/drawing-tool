package com.drawing.command.receiver;

import com.drawing.board.Board;
import com.drawing.board.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LineReceiver extends AbstractReceiver{

    public List<Point<Integer, Integer>> computePath(int x1, int y1, int x2, int y2) throws ReceiverException {
        List<Point<Integer, Integer>> path = new ArrayList<>();
        Point<Integer, Integer> from = new Point<>(x1,y1);
        Point<Integer, Integer> to = new Point<>(x2, y2);

        if (!arePointsCorrect(from, to)){
            throw new ReceiverException("Points are not properly set.");
        }

        if (isHorizontalLine(from, to)) {
            for (int i = from.getX(); i < to.getX(); i++) {
                path.add(new Point<>(i,to.getY()));
            }
            path.add(to);
        }

        if (isVerticalLine(from, to)){
            for (int j = from.getY(); j < to.getY(); j++) {
                path.add(new Point<>(to.getX(),j));
            }
            path.add(to);
        }

        return path;
    }

    private boolean arePointsCorrect(Point<Integer, Integer> from, Point<Integer, Integer> to){
        if (!isHorizontalLine(from, to) && !isVerticalLine(from, to)) {
            return false;
        }
        return true;
    }

    public void drawLine(List<Point<Integer, Integer>> path) throws ReceiverException{
        Optional gridOpt = Optional.ofNullable(Board.getInstance().getGrid());
        if(!gridOpt.isPresent()){
            throw new ReceiverException("Please, draw a canvas first. ");
        } else {
            String[][] grid = (String[][]) gridOpt.get();
            path.forEach(point -> grid[point.getY()][point.getX()] = "x");
            Board.getInstance().setGrid(grid);
        }
        System.out.println(Board.getInstance().toString());
    }
}