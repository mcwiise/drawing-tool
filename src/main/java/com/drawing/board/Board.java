package com.drawing.board;

import java.io.Serializable;
import java.util.Arrays;

/**
 * This is a representation of a board. The class implements {@link Serializable}
 * thus being possible to store it on disk.
 */
public class Board implements Serializable {

    private String[][] grid;
    private int width;
    private int height;

    /**
     * Init a 2D array to represent a grid canvas
     * @param width the  grid width
     * @param height the grid height
     */
    public void initGrid(int width, int height){
        this.width = width+2;
        this.height = height+2;
        this.grid = new String[this.height][this.width];
    }

    /**
     * Grid getter
     * @return a 2D array of {@link String}
     */
    public String[][] getGrid() {
        return grid;
    }

    /**
     * Grid setter
     * @param grid the grid to set
     */
    public void setGrid(String[][] grid) {
        this.grid = grid;
    }

    /**
     * @return board width
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return board height
     */
    public int getHeight() {
        return height;
    }

    @Override
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
