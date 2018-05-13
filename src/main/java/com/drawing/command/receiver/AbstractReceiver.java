package com.drawing.command.receiver;

import com.drawing.board.Point;

public abstract class AbstractReceiver {

    protected boolean isVerticalLine(Point<Integer, Integer> from, Point<Integer, Integer> to){
        return from.getX().equals(to.getX());
    }

    protected boolean isHorizontalLine(Point<Integer, Integer> from, Point<Integer, Integer> to) {
        return from.getY().equals(to.getY());
    }

    protected boolean isOnCanvas(Point<Integer,Integer> point) {
        return true;
    }
}
