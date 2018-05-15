package com.drawing.board.dao;

/**
 * To handle propagate exception from the DAO layer
 */
public class BoardDAOException extends Exception {
    public BoardDAOException(String message){
        super(message);
    }
}
