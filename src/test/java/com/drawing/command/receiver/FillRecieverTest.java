package com.drawing.command.receiver;

import com.drawing.board.Point;
import com.drawing.board.dao.BoardDAO;
import com.drawing.board.dao.BoardDAOException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class FillRecieverTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Mock
    private BoardDAO boardDAO;

    @InjectMocks
    private FillReciever fillReciever;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ReceiverException.class)
    public void shouldThrowExceptionWhenNoCanvasTest() throws BoardDAOException, ReceiverException {
        given(this.boardDAO.load()).willReturn(Optional.empty());
        this.fillReciever.fill(new Point<>(1,1 ), "o");
    }

    @Test(expected = ReceiverException.class)
    public void shouldThrowExceptionWhenBoardExceptionTest() throws BoardDAOException, ReceiverException {
        given(this.boardDAO.load()).willThrow(BoardDAOException.class);
        this.fillReciever.fill(new Point<>(1,1 ), "o");
    }

    @Test
    public void shouldFillFrom2x2PointTest() throws BoardDAOException, ReceiverException {
        given(this.boardDAO.load()).willReturn(Optional.of(TestUtils.getMockBoard()));
        this.fillReciever.fill(new Point<>(1,1 ), "o");
        assertEquals(TestUtils.getExpected("fill/20x4-F.txt"),
                systemOutRule.getLog().substring(0, systemOutRule.getLog().length() - 1));
    }

    @Test
    public void shouldFillFrom10x3PointTest() throws BoardDAOException, ReceiverException {
        given(this.boardDAO.load()).willReturn(Optional.of(TestUtils.getMockBoard()));
        this.fillReciever.fill(new Point<>(10,3 ), "o");
    }
}
