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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class LineReceiverTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Mock
    private BoardDAO boardDAO;

    @InjectMocks
    private LineReceiver lineReceiver;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ReceiverException.class)
    public void shouldThrowReceiverExceptionWhenNONELineTest() throws ReceiverException {
        Point<Integer, Integer> from = new Point<>(1,2);
        Point<Integer, Integer> to = new Point<>(3,4);
        Line line = new Line(from, to);

        lineReceiver.computePath(line);
    }

    @Test
    public void shouldComputePathHorizontalLineTest() throws ReceiverException {
        Point<Integer, Integer> from = new Point<>(1,2);
        Point<Integer, Integer> to = new Point<>(6,2);
        Line line = new Line(from, to);

        List<Point<Integer, Integer>> path = lineReceiver.computePath(line);
        assertEquals(6, path.size());
        assertEquals("Point{x=1, y=2}", path.get(0).toString());
        assertEquals("Point{x=2, y=2}", path.get(1).toString());
        assertEquals("Point{x=3, y=2}", path.get(2).toString());
        assertEquals("Point{x=4, y=2}", path.get(3).toString());
        assertEquals("Point{x=5, y=2}", path.get(4).toString());
        assertEquals("Point{x=6, y=2}", path.get(5).toString());
    }

    @Test
    public void shouldComputePathVerticalLineTest() throws ReceiverException {
        Point<Integer, Integer> from = new Point<>(6,3);
        Point<Integer, Integer> to = new Point<>(6,7);
        Line line = new Line(from, to);

        List<Point<Integer, Integer>> path = lineReceiver.computePath(line);
        assertEquals(5, path.size());
        assertEquals("Point{x=6, y=3}", path.get(0).toString());
        assertEquals("Point{x=6, y=4}", path.get(1).toString());
        assertEquals("Point{x=6, y=5}", path.get(2).toString());
        assertEquals("Point{x=6, y=6}", path.get(3).toString());
        assertEquals("Point{x=6, y=7}", path.get(4).toString());
    }

    @Test
    public void shouldComputePathReverseHorizontalLineTest() throws ReceiverException {
        Point<Integer, Integer> from = new Point<>(6,2);
        Point<Integer, Integer> to = new Point<>(1,2);
        Line line = new Line(from, to);

        List<Point<Integer, Integer>> path = lineReceiver.computePath(line);
        assertEquals(6, path.size());
        assertEquals("Point{x=1, y=2}", path.get(0).toString());
        assertEquals("Point{x=2, y=2}", path.get(1).toString());
        assertEquals("Point{x=3, y=2}", path.get(2).toString());
        assertEquals("Point{x=4, y=2}", path.get(3).toString());
        assertEquals("Point{x=5, y=2}", path.get(4).toString());
        assertEquals("Point{x=6, y=2}", path.get(5).toString());
    }

    @Test
    public void shouldComputePathReverseVerticalLineTest() throws ReceiverException {
        Point<Integer, Integer> from = new Point<>(6,7);
        Point<Integer, Integer> to = new Point<>(6,3);
        Line line = new Line(from, to);

        List<Point<Integer, Integer>> path = lineReceiver.computePath(line);
        assertEquals(5, path.size());
        assertEquals("Point{x=6, y=3}", path.get(0).toString());
        assertEquals("Point{x=6, y=4}", path.get(1).toString());
        assertEquals("Point{x=6, y=5}", path.get(2).toString());
        assertEquals("Point{x=6, y=6}", path.get(3).toString());
        assertEquals("Point{x=6, y=7}", path.get(4).toString());
    }

    @Test(expected = ReceiverException.class)
    public void shouldThrowExceptionWhenNoBoardSetTest() throws ReceiverException, BoardDAOException {
        given(this.boardDAO.load()).willReturn(Optional.empty());
        List<Point<Integer, Integer>> path =
                Arrays.asList(new Point<>(1,2),
                        new Point<>(2,2),
                        new Point<>(3,2),
                        new Point<>(4,2),
                        new Point<>(5,2),
                        new Point<>(6,2));

        this.lineReceiver.drawLine(path);
    }

    @Test(expected = ReceiverException.class)
    public void shouldThrowExceptionWhenCannotLoadBoardTest() throws ReceiverException, BoardDAOException {
        given(this.boardDAO.load()).willThrow(BoardDAOException.class);
        List<Point<Integer, Integer>> path =
                Arrays.asList(new Point<>(1,2),
                        new Point<>(2,2),
                        new Point<>(3,2),
                        new Point<>(4,2),
                        new Point<>(5,2),
                        new Point<>(6,2));

        this.lineReceiver.drawLine(path);
    }

    @Test
    public void shouldDrawLineFrom12to62Test() throws ReceiverException, BoardDAOException {
        given(this.boardDAO.load()).willReturn(Optional.of(TestUtils.getMockBoard()));
        List<Point<Integer, Integer>> path =
                Arrays.asList(new Point<>(1,2),
                        new Point<>(2,2),
                        new Point<>(3,2),
                        new Point<>(4,2),
                        new Point<>(5,2),
                        new Point<>(6,2));

        this.lineReceiver.drawLine(path);

        assertEquals(TestUtils.getExpected("line/20x4-HL.txt"),
                systemOutRule.getLog().substring(0, systemOutRule.getLog().length()-1));
    }

    @Test
    public void shouldDrawLineFrom61to64Test() throws ReceiverException, BoardDAOException {
        given(this.boardDAO.load()).willReturn(Optional.of(TestUtils.getMockBoard()));
        List<Point<Integer, Integer>> path =
                Arrays.asList(new Point<>(6, 1),
                        new Point<>(6, 2),
                        new Point<>(6, 3),
                        new Point<>(6, 4));

        this.lineReceiver.drawLine(path);

        assertEquals(TestUtils.getExpected("line/20x4-VL.txt"),
                systemOutRule.getLog().substring(0, systemOutRule.getLog().length() - 1));
    }

}
