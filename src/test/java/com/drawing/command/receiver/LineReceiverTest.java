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
public class LineReceiverTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    private LineReceiver lineReceiver;

    @Before
    public void init(){
        this.lineReceiver = new LineReceiver();
    }

    @Test(expected = ReceiverException.class)
    public void shouldThrowReceiverExceptionWhenNoHorizontalNorVerticalTest() throws ReceiverException {
        lineReceiver.computePath(1,2,3,4);
    }

    @Test
    public void shouldComputePathFrom12to62Test() throws ReceiverException {
        List<Point<Integer, Integer>> path = lineReceiver.computePath(1,2,6,2);
        assertEquals(6, path.size());
    }

    @Test
    public void shouldComputePathFrom63to67Test() throws ReceiverException {
        List<Point<Integer, Integer>> path = lineReceiver.computePath(6,3,6,7);
        assertEquals(5, path.size());
    }

    @Test
    public void shouldComputePathFrom62to12Test() throws ReceiverException {
        List<Point<Integer, Integer>> path = lineReceiver.computePath(6,2,1,2);
        assertEquals(6, path.size());
    }

    @Test
    public void shouldComputePathFrom67to63Test() throws ReceiverException {
        List<Point<Integer, Integer>> path = lineReceiver.computePath(6,7,6,3);
        assertEquals(5, path.size());
    }

    @Test(expected = ReceiverException.class)
    public void shouldThrowReceiverExceptionWhenNoCanvasSetTest() throws ReceiverException {
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
    public void shouldDrawLineFrom12to62Test() throws ReceiverException {
        Board.getInstance().setGrid(mockGrid());
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
    public void shouldDrawLineFrom61to64Test() throws ReceiverException {
        Board.getInstance().setGrid(mockGrid());
        List<Point<Integer, Integer>> path =
                Arrays.asList(new Point<>(6,1),
                        new Point<>(6,2),
                        new Point<>(6,3),
                        new Point<>(6,4));

        this.lineReceiver.drawLine(path);

        assertEquals(TestUtils.getExpected("line/20x4-VL.txt"),
                systemOutRule.getLog().substring(0, systemOutRule.getLog().length()-1));
    }

    private static String[][] mockGrid(){
        String[][] mockCanvas = new String[6][22];
        Arrays.fill(mockCanvas[0], "-");
        Arrays.fill(mockCanvas[5], "-");

        for (int i = 1; i < 5; i++) {
            for (int j = 0; j < 22; j++) {
                if (j == 0 || j == 21) {
                    mockCanvas[i][j] = "|";
                } else {
                    mockCanvas[i][j] = " ";
                }
            }
        }

        return mockCanvas;
    }
}
