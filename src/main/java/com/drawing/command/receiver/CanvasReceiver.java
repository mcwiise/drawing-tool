package com.drawing.command.receiver;

import com.drawing.board.dao.BoardDAOException;
import com.drawing.board.Board;
import com.drawing.board.dao.BoardDAO;

import java.util.Arrays;

public class CanvasReceiver extends AbstractReceiver{

    public CanvasReceiver(BoardDAO boardDAO){
        this.boardDAO = boardDAO;
    }

    public void drawCanvas(int width, int height) throws ReceiverException{
        try {
            Board board = new Board();
            board.initGrid(width, height);
            String[][] grid = board.getGrid();

            Arrays.fill(grid[0], "-");
            Arrays.fill(grid[board.getHeight()-1], "-");

            for (int i = 1; i < board.getHeight()-1; i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if(j == 0 || j == board.getWidth()-1){
                        grid[i][j] = "|";
                    } else {
                        grid[i][j] = " ";
                    }
                }
            }
            board.setGrid(grid);
            System.out.println(board.toString());
            boardDAO.save(board);
        } catch (BoardDAOException e) {
            System.out.println(e.getMessage());
            throw new ReceiverException("Cannot draw canvas");
        }
    }
}
