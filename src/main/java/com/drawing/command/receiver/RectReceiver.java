package com.drawing.command.receiver;

import com.drawing.board.Board;
import com.drawing.board.Line;
import com.drawing.board.Point;
import com.drawing.board.dao.BoardDAO;
import com.drawing.board.dao.BoardDAOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This is a concrete implementation of {@link AbstractReceiver}.
 * It keeps the logic to draw a rectangle in console by System.out
 */
public class RectReceiver extends AbstractReceiver{

    public RectReceiver(BoardDAO boardDAO){
        this.boardDAO = boardDAO;
    }

    /**
     * Defines if all the lines that make a rectangle are in the canvas perimeter
     * @param line1 the first line
     * @param line2 the second line
     * @param line3 the third line
     * @param line4 the fourth line
     * @return true if all the lines are in canvas perimeter
     * @throws ReceiverException If there is one of the lines which is not on canvas
     */
    public boolean isRectangleInCanvas(Line line1, Line line2, Line line3, Line line4) throws ReceiverException {
        return this.isLineOnCanvas(line1) &&
                this.isLineOnCanvas(line2) &&
                this.isLineOnCanvas(line3) &&
                this.isLineOnCanvas(line4);
    }

    /**
     * Computes all the points of the lines that make a rectangle
     * @param line1 first line
     * @param line2 second line
     * @param line3 third line
     * @param line4 fourth line
     * @return a list with line paths
     * @throws ReceiverException if there is a problem computing the path of a line
     */
    public List<List<Point<Integer,Integer>>> computePaths(Line line1, Line line2, Line line3, Line line4) throws ReceiverException {
        List<List<Point<Integer,Integer>>> paths = new ArrayList<>();

        paths.add(this.computePath(line1));
        paths.add(this.computePath(line2));
        paths.add(this.computePath(line3));
        paths.add(this.computePath(line4));

        return paths;
    }

    /**
     * Draws a rectangle in console
     * @param paths The list of paths of the lines that make a rectangle
     * @throws ReceiverException if there is a problem accessing the board,
     * or if the canvas is not set yet.
     */
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
