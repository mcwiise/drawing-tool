package com.drawing.command;

import com.drawing.matrix.Matrix;

public class BoardReceiver {

    public String createCanvas(int width, int height){
        Matrix.getInstance().setSize(width, height);
        Matrix.getInstance().init();
        return Matrix.getInstance().toString();
    }
}
