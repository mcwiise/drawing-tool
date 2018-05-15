package com.drawing.command.receiver;

import com.drawing.board.Board;
import com.drawing.board.dao.BoardDAO;
import com.drawing.board.dao.BoardDAOException;

import java.util.Arrays;

/**
 * This is a concrete implementation of an {@link AbstractReceiver}.
 * It keeps the logic to draw a canvas on console with System.out
 */
public class CanvasReceiver extends AbstractReceiver{

    public CanvasReceiver(BoardDAO boardDAO){
        this.boardDAO = boardDAO;
    }

    /**
     * Draws a canvas in console
     * @param width canvas's width
     * @param height canvas height
     * @throws ReceiverException
     */
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
