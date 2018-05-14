package com.drawing.board.dao;

import com.drawing.board.Board;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BoardDaoImplTest {

    public BoardDAO boardDAO;

    @Before
    public void setUp(){
        this.boardDAO = new BoardDAOImpl();
    }

    @After
    public void tearDown(){
        this.boardDAO.delete(BoardDAO.BOARD_FILE);
    }

    @Test(expected = BoardDAOException.class)
    public void shouldThrownExceptionWhenSavingWrongFileNameTest() throws BoardDAOException {
        this.boardDAO.init("adadfasdf");
        this.boardDAO.save(new Board());
    }

    @Test(expected = BoardDAOException.class)
    public void shouldThrownExceptionWhenSavingNoFileTest() throws BoardDAOException {
        this.boardDAO.save(new Board());
    }

    @Test
    public void shouldSaveNewBoardTest() throws BoardDAOException {
        this.boardDAO.init(BoardDAO.BOARD_FILE);
        this.boardDAO.save(mockEmptyBoard());
        Optional<Board> oBoard = this.boardDAO.load();
        assertTrue(oBoard.isPresent());
    }

    @Test(expected = BoardDAOException.class)
    public void shouldThrowExceptionWhenLoadingNoFileTest() throws BoardDAOException {
        this.boardDAO.load();
    }

    @Test
    public void shouldRetrieveNoBoardWhenLoadingEmptyFileTest() throws BoardDAOException {
        this.boardDAO.init(BoardDAO.BOARD_FILE);
        Optional<Board> oBoard = this.boardDAO.load();
        assertTrue(!oBoard.isPresent());
    }

    @Test
    public void shouldRetrieveNoBoardWhenNoGridFromFileTest() throws BoardDAOException {
        this.boardDAO.init(BoardDAO.BOARD_FILE);
        this.boardDAO.save(new Board());
        Optional<Board> oBoard = this.boardDAO.load();
        assertTrue(!oBoard.isPresent());
    }

    @Test
    public void shouldRetrieveNoBoardFromFileTest() throws BoardDAOException {
        this.boardDAO.init(BoardDAO.BOARD_FILE);
        this.boardDAO.save(mockEmptyBoard());
        Optional<Board> oBoard = this.boardDAO.load();
        assertTrue(oBoard.isPresent());
        assertEquals(22,oBoard.get().getWidth());
        assertEquals(6,oBoard.get().getHeight());
    }

    private static Board mockEmptyBoard(){
        Board board = new Board();
        board.initGrid(20, 4);
        return board;
    }
}
