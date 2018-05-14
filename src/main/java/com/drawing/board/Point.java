package com.drawing.board;

public class Point<X, Y>{
    private X x;
    private Y y;

    public Point(X x, Y y) {
        this.setX(x);
        this.setY(y);
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    public void setX(X x) {
        this.x = x;
    }

    public void setY(Y y) {
        this.y = y;
    }
}