package com.drawing.board;

/**
 * This is a representation of a Cartesian coordinate in for of a Tuple
 * @param <X> x coordinate
 * @param <Y> y coordinate
 */
public class Point<X, Y>{
    private X x;
    private Y y;

    public Point(X x, Y y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * getter
     * @return x coordinate
     */
    public X getX() {
        return x;
    }

    /**
     * getter
     * @return y coordinate
     */
    public Y getY() {
        return y;
    }

    public void setX(X x) {
        this.x = x;
    }

    public void setY(Y y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}