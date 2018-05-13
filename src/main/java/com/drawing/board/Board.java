package com.drawing.board;

import java.util.Arrays;

public class Board {

    private static Board instance;

    private String[][] grid;
    private int width;
    private int height;

    private Board(){}

    public static Board getInstance(){
        if(instance == null){
            instance = new Board();
        }
        return instance;
    }

    public void initGrid(int width, int height){
        this.width = width+2;
        this.height = height+2;
        this.setGrid(new String[this.height][this.width]);
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

        Arrays.stream(this.grid).forEach(row -> {
            sb.append(Arrays.toString(row).replaceAll("\\[|\\]|, ","")).append("\n");
        });

        return sb.substring(0, sb.length()-1);
    }
}
