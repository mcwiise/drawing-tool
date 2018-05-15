package com.drawing.command.receiver;


import com.drawing.board.Line;
import com.drawing.board.Point;
import com.drawing.board.dao.BoardDAO;
import com.drawing.board.dao.BoardDAOException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class RectReceiverTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Mock
    private BoardDAO boardDAO;

    @InjectMocks
    private RectReceiver rectReceiver;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(boardDAO);
    }

    @Test(expected = ReceiverException.class)
    public void shouldThrowExceptionWhenNoCanvasSetTest() throws ReceiverException {
        List<List<Point<Integer, Integer>>> paths = new ArrayList<>();
        this.rectReceiver.drawRectangle(paths);
    }

    @Test
    public void shouldRetrieveFalseWhenRectangleNotInCanvas() throws ReceiverException, BoardDAOException {
        given(this.boardDAO.load()).willReturn(Optional.of(TestUtils.getMockBoard()));

        Line line1 = new Line(new Point<>(100, 1), new Point<>(20,1));
        Line line2 = new Line(new Point<>(20, 1), new Point<>(20,3));
        Line line3 = new Line(new Point<>(20, 3), new Point<>(16,3));
        Line line4 = new Line(new Point<>(16, 3), new Point<>(16,1));

        assertTrue(!this.rectReceiver.isRectangleInCanvas(line1, line2, line3, line4));
    }

    @Test
    public void shouldRetrieveTrueWhenRectangleInCanvas() throws ReceiverException, BoardDAOException {
        given(this.boardDAO.load()).willReturn(Optional.of(TestUtils.getMockBoard()));

        Line line1 = new Line(new Point<>(16, 1), new Point<>(20,1));
        Line line2 = new Line(new Point<>(20, 1), new Point<>(20,3));
        Line line3 = new Line(new Point<>(20, 3), new Point<>(16,3));
        Line line4 = new Line(new Point<>(16, 3), new Point<>(16,1));

        assertTrue(this.rectReceiver.isRectangleInCanvas(line1, line2, line3, line4));
    }

    @Test
    public void shouldDrawRectangleFrom12to62Test() throws ReceiverException, BoardDAOException {

        given(this.boardDAO.load()).willReturn(Optional.of(TestUtils.getMockBoard()));

        this.rectReceiver.drawRectangle(TestUtils.getMockPaths());

        assertEquals(TestUtils.getExpected("rect/20x4-R.txt"),
                systemOutRule.getLog().substring(0, systemOutRule.getLog().length()-1));
    }

    @Test
    public void shouldComputePathsFrom12to62Test() throws ReceiverException, BoardDAOException {

        Line line1 = new Line(new Point<>(16, 1), new Point<>(20,1));
        Line line2 = new Line(new Point<>(20, 1), new Point<>(20,3));
        Line line3 = new Line(new Point<>(20, 3), new Point<>(16,3));
        Line line4 = new Line(new Point<>(16, 3), new Point<>(16,1));

        List<List<Point<Integer,Integer>>> paths = this.rectReceiver.computePaths(line1, line2, line3, line4);

        assertEquals(TestUtils.getMockPaths().size(), paths.size());
    }
}
