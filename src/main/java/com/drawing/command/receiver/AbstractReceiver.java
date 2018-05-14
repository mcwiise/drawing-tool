package com.drawing.command.receiver;

import com.drawing.board.Board;
import com.drawing.board.Line;
import com.drawing.board.Point;
import com.drawing.board.dao.BoardDAO;
import com.drawing.board.dao.BoardDAOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractReceiver {

    protected BoardDAO boardDAO;

    public List<Point<Integer, Integer>> computePath(Line line) throws ReceiverException {

        List<Point<Integer, Integer>> path = new ArrayList<>();

        if(Line.HORIZONTAL.equals(line.getDirection())){
            for (int i = line.getFrom().getX(); i < line.getTo().getX(); i++) {
                path.add(new Point<>(i,line.getTo().getY()));
            }
            path.add(line.getTo());
        } else if(Line.VERTICAL.equals(line.getDirection())){
            for (int j = line.getFrom().getY(); j < line.getTo().getY(); j++) {
                path.add(new Point<>(line.getTo().getX(),j));
            }
            path.add(line.getTo());
        } else {
            throw new ReceiverException("Line is not properly set.");
        }

        return path;
    }

    public boolean isLineOnCanvas(Line line) throws ReceiverException{
        try {
            Optional<Board> oBoard = this.boardDAO.load();
            boolean isOnCanvas = false;
            if(oBoard.isPresent()){
                Board board = oBoard.get();
                if(this.isPointOnCanvas(line.getFrom(), board) && this.isPointOnCanvas(line.getTo(), board)){
                    isOnCanvas = true;
                }
            }
            return isOnCanvas;
        } catch (BoardDAOException e) {
            System.out.println(e.getMessage());
            throw new ReceiverException("Cannot define if line on canvas. ");
        }
    }

    private boolean isPointOnCanvas(Point<Integer, Integer> point, Board board){
        boolean isOnCanvas = false;
        if(point.getX() > 0
                && point.getX() < board.getWidth()-1
                && point.getY() > 0
                && point.getY() < board.getHeight()-1){
            isOnCanvas = true;
        }
        return isOnCanvas;
    }
}
