package com.drawing.command.receiver;

import com.drawing.board.Board;

import java.util.Arrays;

public class CanvasReceiver {

    public void drawCanvas(int width, int height){
        Board.getInstance().initGrid(width, height);
        String[][] grid = Board.getInstance().getGrid();
        int bwidth = Board.getInstance().getWidth();
        int bheigth = Board.getInstance().getHeight();

        Arrays.fill(grid[0], "-");
        Arrays.fill(grid[bheigth-1], "-");

        for (int i = 1; i < bheigth-1; i++) {
            for (int j = 0; j < bwidth; j++) {
                if(j == 0 || j == bwidth-1){
                    grid[i][j] = "|";
                } else {
                    grid[i][j] = " ";
                }
            }
        }

        Board.getInstance().setGrid(grid);
        System.out.println(Board.getInstance().toString());
    }
}
