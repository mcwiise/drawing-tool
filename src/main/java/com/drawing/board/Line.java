package com.drawing.board;

/**
 * This is representation of a line to draw in a board
 */
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

    /**
     * getter
     * @return line direction
     */
    public String getDirection(){
        return this.direction;
    }

    /**
     * getter
     * @return the point where the line starts
     */
    public Point<Integer, Integer> getFrom() {
        return from;
    }

    /**
     * setter
     * @param from the initial point to set
     */
    public void setFrom(Point<Integer, Integer> from) {
        this.from = from;
    }

    /**
     * getter
     * @return the final point where the line ends
     */
    public Point<Integer, Integer> getTo() {
        return to;
    }

    /**
     * setter
     * @param to the final point to set
     */
    public void setTo(Point<Integer, Integer> to) {
        this.to = to;
    }

    /**
     * the method sets the line's direction from
     * Line.HORIZONTAL
     * Line.VERTICAL
     * Line.NONE
     */
    private void setDirection() {
        if(isHorizontalLine()){
            this.direction = Line.HORIZONTAL;
        } else if(isVerticalLine()){
            this.direction = Line.VERTICAL;
        } else {
            this.direction = Line.NONE;
        }
    }

    /**
     * Defines if a line's direction is vertical
     * @return true if the line is vertical
     */
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

    /**
     * Defines if a line's direction is horizontal
     * @return true if the line is horizontal
     */
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
