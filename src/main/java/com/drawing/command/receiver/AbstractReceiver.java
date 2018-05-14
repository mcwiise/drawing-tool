package com.drawing.command.receiver;

import com.drawing.board.Point;

public abstract class AbstractReceiver {

    protected boolean isVerticalLine(Point<Integer, Integer> from, Point<Integer, Integer> to){
        if (from.getX().equals(to.getX())){
            if (from.getY() > to.getY()){
                Integer aux = from.getY();
                from.setY(to.getY());
                to.setY(aux);
            }
            return true;
        } else {
            return false;
        }
    }

    protected boolean isHorizontalLine(Point<Integer, Integer> from, Point<Integer, Integer> to) {
        if (from.getY().equals(to.getY())){
            if (from.getX() > to.getX()){
                Integer aux = from.getX();
                from.setX(to.getX());
                to.setX(aux);
            }
            return true;
        } else {
            return false;
        }
    }
}
