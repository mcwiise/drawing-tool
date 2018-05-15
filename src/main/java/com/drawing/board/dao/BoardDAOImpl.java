package com.drawing.board.dao;

import com.drawing.board.Board;

import java.io.*;
import java.util.Optional;

/**
 * Concrete implementation of {@link BoardDAO} interface
 */
public class BoardDAOImpl implements BoardDAO {

    private FileOutputStream fos;
    private ObjectOutputStream oos;
    private FileInputStream fis;
    private ObjectInputStream ois;

    @Override
    public void init(String fileName) throws BoardDAOException {
        if(BOARD_FILE.equals(fileName)){
            try {
                File file = new File(fileName);
                this.delete(BOARD_FILE);
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new BoardDAOException("IOException when saving board file. ");
            }
        } else {
            throw new BoardDAOException("Wrong board file name. ");
        }
    }

    @Override
    public void save(Board board) throws BoardDAOException {
        try {
            File file = new File(BOARD_FILE);
            if(file.exists()){
                this.fos = new FileOutputStream(file);
                this.oos = new ObjectOutputStream(fos);
                this.oos.writeObject(board);
                this.oos.close();
                this.fos.close();
            } else {
                throw new BoardDAOException("Board file does not exist. ");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new BoardDAOException("FileNotFoundException when saving the board");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new BoardDAOException("IOException when saving the board");
        }
    }

    @Override
    public Optional<Board> load() throws BoardDAOException {
        try {
            Optional<Board> oBoard = Optional.empty();
            File file = new File(BOARD_FILE);
            if (file.exists()){
                if(file.length() > 0){
                    this.fis = new FileInputStream(BOARD_FILE);
                    this.ois = new ObjectInputStream(fis);
                    Board board = (Board) ois.readObject();
                    Optional<String[][]> oGrid = Optional.ofNullable(board.getGrid());
                    if(oGrid.isPresent()){
                        oBoard = Optional.of(board);
                    }
                }
                return oBoard;
            } else {
                throw new BoardDAOException("Board file does not exist. ");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new BoardDAOException("FileNotFoundException when loading the board");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new BoardDAOException("IOException when loading the board");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new BoardDAOException("ClassNotFoundException when loading the board");
        }
    }

    @Override
    public boolean delete(String fileName){
        File file = new File(fileName);
        return file.delete();
    }
}
