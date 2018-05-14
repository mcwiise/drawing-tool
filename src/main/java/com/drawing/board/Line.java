package com.drawing.board;

public class Line {

    public static final String VERTICAL = "vertical";
    public static final String HORIZONTAL = "horizontal";
    public static final String NONE = "none";

    private Point<Integer, Integer> from;
    private Point<Integer, Integer> to;
    private String direction;

    public Line(){
        this.from = new Point<>(0,0);
        this.to = new Point<>(0,0);
        this.direction = Line.NONE;
    }

    public Line(Point<Integer, Integer> from, Point<Integer, Integer> to) {
        this.from = from;
        this.to = to;
        this.setDirection();
    }

    public String getDirection(){
        return this.direction;
    }

    public Point<Integer, Integer> getFrom() {
        return from;
    }

    public void setFrom(Point<Integer, Integer> from) {
        this.from = from;
    }

    public Point<Integer, Integer> getTo() {
        return to;
    }

    public void setTo(Point<Integer, Integer> to) {
        this.to = to;
    }

    private void setDirection() {
        if(isHorizontalLine()){
            this.direction = Line.HORIZONTAL;
        } else if(isVerticalLine()){
            this.direction = Line.VERTICAL;
        } else {
            this.direction = Line.NONE;
        }
    }

    private boolean isVerticalLine(){
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

    private boolean isHorizontalLine() {
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

    @Override
    public String toString() {
        return "Line{" +
                "from=" + from +
                ", to=" + to +
                ", direction='" + direction + '\'' +
                '}';
    }
}
