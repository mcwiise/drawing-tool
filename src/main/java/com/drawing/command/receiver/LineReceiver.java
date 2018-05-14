package com.drawing.command.receiver;

import com.drawing.board.Line;
import com.drawing.board.dao.BoardDAOException;
import com.drawing.board.Board;
import com.drawing.board.dao.BoardDAO;
import com.drawing.board.Point;

import java.util.List;
import java.util.Optional;

public class LineReceiver extends AbstractReceiver{

    public LineReceiver(BoardDAO boardDAO){
        this.boardDAO = boardDAO;
    }

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
