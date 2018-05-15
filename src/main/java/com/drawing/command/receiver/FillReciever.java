package com.drawing.command.receiver;

import com.drawing.board.Board;
import com.drawing.board.Point;
import com.drawing.board.dao.BoardDAO;
import com.drawing.board.dao.BoardDAOException;

import java.util.Optional;

public class FillReciever extends AbstractReceiver {

    public FillReciever(BoardDAO boardDAO){
        this.boardDAO = boardDAO;
    }

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
