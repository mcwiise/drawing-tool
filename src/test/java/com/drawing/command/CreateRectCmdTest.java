package com.drawing.command;

import com.drawing.board.Point;
import com.drawing.command.receiver.LineReceiver;
import com.drawing.command.receiver.ReceiverException;
import com.drawing.command.receiver.RectReceiver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class CreateRectCmdTest {

    @Mock
    private RectReceiver rectReceiver;

    @Mock
    private LineReceiver lineReceiver;

    @InjectMocks
    private CreateRectCmd createRectCmd;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenMoreNumberParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2","4","12","15", "23");
        createRectCmd.setParams(params);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommanExceptionWhenLessNumberParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2");
        createRectCmd.setParams(params);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenWrongTypeParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2","sdf","15", "23");
        createRectCmd.setParams(params);
    }

    @Test
    public void shouldCreateRectangleTest() throws ReceiverException, CommandException {
        List<String> params = Arrays.asList("16","1","20","3");
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

        given(this.lineReceiver.computePath(anyInt(), anyInt(), anyInt(), anyInt())).willReturn(mockPath1);
        given(this.lineReceiver.computePath(anyInt(), anyInt(), anyInt(), anyInt())).willReturn(mockPath2);
        given(this.lineReceiver.computePath(anyInt(), anyInt(), anyInt(), anyInt())).willReturn(mockPath3);
        given(this.lineReceiver.computePath(anyInt(), anyInt(), anyInt(), anyInt())).willReturn(mockPath4);

        doNothing().when(this.rectReceiver).drawRectangle(anyList(), anyList(), anyList(), anyList());

        createRectCmd.setParams(params);
        createRectCmd.execute();

        then(this.rectReceiver).should().drawRectangle(anyList(), anyList(), anyList(), anyList());
    }

}
