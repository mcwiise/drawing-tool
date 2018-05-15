package com.drawing.board.dao;

import com.drawing.board.Board;

import java.util.Optional;

/**
 * This is an implementation of the DAO pattern, to store
 * the board representation on disk
 */
public interface BoardDAO {

    public static final String BOARD_FILE = "board.ser";

    /**
     * Creates a new file on disk
     * @param fileName the file name
     * @throws BoardDAOException if there is a problem accessing the disk to wirte
     */
    public void init(String fileName) throws BoardDAOException;

    /**
     * Saves any modification on the board on disk
     * @param board the board to save
     * @throws BoardDAOException if there is a problem accessing the disk to wirite
     */
    public void save(Board board) throws BoardDAOException;

    /**
     * Loads a file from disk
     * @return an {@link Optional} enclosing the current board
     * @throws BoardDAOException if there is a problem accessing the disk to read
     */
    public Optional<Board> load() throws BoardDAOException;

    /**
     * Deletes the file from the disk
     * @param fileName the fila to delete
     * @return true if the file was deleted.
     */
    public boolean delete(String fileName);

}
