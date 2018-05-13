package com.drawing.utils;

import java.util.Arrays;

public class Matrix {

    private static Matrix instance;

    private String[][] matrix;
    private int width;
    private int height;

    private Matrix(){}

    public static Matrix getInstance(){
        if(instance == null){
            instance = new Matrix();
        }
        return instance;
    }

    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
        this.matrix = new String[this.height][this.width];
    }

    public void init(){
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.matrix[i][j] = " ";
            }
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.addFrame()).append("\n");
        Arrays.stream(this.matrix).forEach( row ->
            sb.append("|")
                    .append(this.formatRow(row))
                    .append("|\n")
        );
        sb.append(this.addFrame());
        return sb.toString();
    }

    private String formatRow(String[] row){
        return Arrays.toString(row)
                .replaceAll("\\[|\\]|, ", "");
    }

    private String addFrame(){
        String[] frame = new String[this.width+2];
        Arrays.fill(frame, "-");
        return Arrays.toString(frame)
                .replaceAll("\\[|\\]|\\, ", "");
    }
}
