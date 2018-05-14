package com.drawing.command.receiver;

import com.drawing.board.Board;
import com.drawing.board.Point;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RectReceiverTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    private RectReceiver rectReceiver;

    @Before
    public void init(){
        this.rectReceiver = new RectReceiver();
    }

    @Test(expected = ReceiverException.class)
    public void shouldThrowReceiverExceptionWhenNoCanvasSetTest() throws ReceiverException {
        Board.getInstance().setGrid(null);
        List<Point<Integer, Integer>> path =
                Arrays.asList(new Point<>(1,2),
                        new Point<>(2,2),
                        new Point<>(3,2),
                        new Point<>(4,2),
                        new Point<>(5,2),
                        new Point<>(6,2));

        this.rectReceiver.drawRectangle(path, path, path, path);
    }

    @Test
    public void shouldDrawLineFrom12to62Test() throws ReceiverException {
        Board.getInstance().setGrid(TestUtils.mockGrid());
        List<Point<Integer, Integer>> mockPath1 =
                Arrays.asList(new Point<>(16,1),
                        new Point<>(17,1),
                        new Point<>(18,1),
                        new Point<>(19,1),
                        new Point<>(20,1));

        List<Point<Integer, Integer>> mockPath2 =
                Arrays.asList(new Point<>(20,1),
                        new Point<>(20,2),
                        new Point<>(20,3));

        List<Point<Integer, Integer>> mockPath3 =
                Arrays.asList(new Point<>(16,3),
                        new Point<>(17,3),
                        new Point<>(18,3),
                        new Point<>(19,3),
                        new Point<>(20,3));

        List<Point<Integer, Integer>> mockPath4 =
                Arrays.asList(new Point<>(16,1),
                        new Point<>(16,2),
                        new Point<>(16,3));

        this.rectReceiver.drawRectangle(mockPath1, mockPath2, mockPath3, mockPath4);

        assertEquals(TestUtils.getExpected("rect/20x4-R.txt"),
                systemOutRule.getLog().substring(0, systemOutRule.getLog().length()-1));
    }
}
