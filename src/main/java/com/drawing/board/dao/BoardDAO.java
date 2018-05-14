package com.drawing.board.dao;

import com.drawing.board.Board;

import java.util.Optional;

public interface BoardDAO {

    public static final String BOARD_FILE = "board.ser";

    public void init(String fileName) throws BoardDAOException;
    public void save(Board board) throws BoardDAOException;
    public Optional<Board> load() throws BoardDAOException;
    public boolean delete(String fileName);

}
