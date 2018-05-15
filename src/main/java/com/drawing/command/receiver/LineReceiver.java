package com.drawing.command.receiver;

import com.drawing.board.Board;
import com.drawing.board.Point;
import com.drawing.board.dao.BoardDAO;
import com.drawing.board.dao.BoardDAOException;

import java.util.List;
import java.util.Optional;

/**
 * This is a concrete implementation of {@link AbstractReceiver}.
 * It keeps the logic to draw a line on canvas by System.out
 */
public class LineReceiver extends AbstractReceiver{

    public LineReceiver(BoardDAO boardDAO){
        this.boardDAO = boardDAO;
    }

    /**
     * Draws a line on the canvas
     * @param path the list of point that makes a line
     * @throws ReceiverException if there is a problem accessing the board, or
     * if the canvas is not set
     */
    public void drawLine(List<Point<Integer, Integer>> path) throws ReceiverException{
        try {
            Optional oBoard = boardDAO.load();
            if(oBoard.isPresent()){
                Board board = (Board) oBoard.get();
                String[][] grid = board.getGrid();
                path.forEach(point -> grid[point.getY()][point.getX()] = "x");
                board.setGrid(grid);
                System.out.println(board.toString());
                this.boardDAO.save(board);
            } else {
                throw new ReceiverException("Canvas is not set. ");
            }
        } catch (BoardDAOException e) {
            System.out.println(e.getMessage());
            throw new ReceiverException("Cannot draw line. ");
        }
    }
}
