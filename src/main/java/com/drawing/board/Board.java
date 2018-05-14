package com.drawing.board;

import java.io.Serializable;
import java.util.Arrays;

public class Board implements Serializable {

    private String[][] grid;
    private int width;
    private int height;

    public void initGrid(int width, int height){
        this.width = width+2;
        this.height = height+2;
        this.grid = new String[this.height][this.width];
    }

    public String[][] getGrid() {
        return grid;
    }

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        Arrays.stream(this.getGrid())
                .forEach(row ->
                        sb.append(Arrays.toString(row)
                                .replaceAll("\\[|\\]|, ",""))
                                .append("\n"));

        return sb.substring(0, sb.length()-1);
    }

}
