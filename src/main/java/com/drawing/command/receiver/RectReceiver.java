package com.drawing.command.receiver;

import com.drawing.board.Board;
import com.drawing.board.Line;
import com.drawing.board.Point;
import com.drawing.board.dao.BoardDAO;
import com.drawing.board.dao.BoardDAOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RectReceiver extends AbstractReceiver{

    public RectReceiver(BoardDAO boardDAO){
        this.boardDAO = boardDAO;
    }

    public boolean isRectangleInCanvas(Line line1, Line line2, Line line3, Line line4) throws ReceiverException {
        return this.isLineOnCanvas(line1) &&
                this.isLineOnCanvas(line2) &&
                this.isLineOnCanvas(line3) &&
                this.isLineOnCanvas(line4);
    }

    public List<List<Point<Integer,Integer>>> computePaths(Line line1, Line line2, Line line3, Line line4) throws ReceiverException {
        List<List<Point<Integer,Integer>>> paths = new ArrayList<>();

        paths.add(this.computePath(line1));
        paths.add(this.computePath(line2));
        paths.add(this.computePath(line3));
        paths.add(this.computePath(line4));

        return paths;
    }

    public void drawRectangle(List<List<Point<Integer, Integer>>> paths) throws ReceiverException {

        try {
            Optional oBoard = boardDAO.load();
            if(oBoard.isPresent()){
                Board board = (Board) oBoard.get();

                String[][] grid = board.getGrid();
                paths.stream().forEach(path ->{
                    path.stream().forEach(point -> {
                        grid[point.getY()][point.getX()] = "x";
                    });
                });

                board.setGrid(grid);
                System.out.println(board.toString());
                this.boardDAO.save(board);
            } else {
                throw new ReceiverException("Canvas is not set");
            }
        } catch (BoardDAOException e) {
            System.out.println(e.getMessage());
            throw new ReceiverException("Cannot draw rectangle. ");
        }
    }
}
