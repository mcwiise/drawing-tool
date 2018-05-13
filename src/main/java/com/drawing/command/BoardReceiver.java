package com.drawing.command;

import com.drawing.utils.Matrix;

public class BoardReceiver {

    public String createCanvas(int width, int height){
        Matrix.getInstance().setSize(width, height);
        Matrix.getInstance().init();
        return Matrix.getInstance().toString();
    }

    public String createLine(int x1, int y1, int x2, int y2) {
        return "";
    }
}
