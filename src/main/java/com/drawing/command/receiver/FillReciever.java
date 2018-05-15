package com.drawing.command.receiver;

import com.drawing.board.Board;
import com.drawing.board.Point;
import com.drawing.board.dao.BoardDAO;
import com.drawing.board.dao.BoardDAOException;

import java.util.Optional;

/**
 * This is a concrete implementation of an {@link AbstractReceiver}.
 * It keeps the logic to fill a canvas with a character
 */
public class FillReciever extends AbstractReceiver {

    public FillReciever(BoardDAO boardDAO){
        this.boardDAO = boardDAO;
    }

    /**
     * Fills a canvas with a given character
     * @param point the point where to start filling from
     * @param filler the character what to fill the board with
     * @throws ReceiverException If there is a problem accessing the board, or
     * if the canvas is not set
     */
    public void fill(Point<Integer, Integer> point, String filler) throws ReceiverException {
        try {
            Optional oBoard = boardDAO.load();
            if(oBoard.isPresent()){
                Board board = (Board) oBoard.get();
                String[][] grid = board.getGrid();
                fillGrid(point.getY(), point.getX(), grid, filler);
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
