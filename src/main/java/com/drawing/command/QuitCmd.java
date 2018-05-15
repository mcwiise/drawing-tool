package com.drawing.command;

import com.drawing.board.dao.BoardDAO;

import java.util.List;

public class QuitCmd extends AbstractCmd implements Command {

    private BoardDAO boardDAO;

    public QuitCmd(BoardDAO boardDAO){
        this.boardDAO = boardDAO;
    }
    @Override
    public void setParams(List<String> params) throws CommandException {
        //Not necessary to implement
    }

    @Override
    public void execute() {
        this.boardDAO.delete(BoardDAO.BOARD_FILE);
        System.out.println("Bye!!");
    }
}
