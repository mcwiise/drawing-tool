package com.drawing.command;

import com.drawing.board.Point;
import com.drawing.command.receiver.LineReceiver;
import com.drawing.command.receiver.ReceiverException;
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

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class CreateLineCmdTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Mock
    private LineReceiver lineReceiver;

    @InjectMocks
    private CreateLineCmd createLineCmd;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommanExceptionWhenMoreNumberParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2","4","12","15", "23");
        createLineCmd.setParams(params);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommanExceptionWhenLessNumberParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2");
        createLineCmd.setParams(params);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenWrongTypeParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2","e","5", "$");
        createLineCmd.setParams(params);
    }

    @Test
    public void shouldCreateHorizontalLineTest() throws ReceiverException, CommandException {
        List<String> params = Arrays.asList("2","3","2","5");
        List<Point<Integer, Integer>> mockPath =
                Arrays.asList(new Point<>(2,3),
                        new Point<>(2,4),
                        new Point<>(2,5));
        given(this.lineReceiver.computePath(anyInt(), anyInt(), anyInt(), anyInt())).willReturn(mockPath);
        doNothing().when(this.lineReceiver).drawLine(anyList());

        createLineCmd.setParams(params);
        createLineCmd.execute();

        then(this.lineReceiver).should().drawLine(mockPath);
    }

    @Test
    public void shouldCreateVerticalLineTest() throws CommandException, ReceiverException {
        List<String> params = Arrays.asList("2","5","4","5");
        List<Point<Integer, Integer>> mockPath =
                Arrays.asList(new Point<>(2,5),
                        new Point<>(3,5),
                        new Point<>(4,5));
        given(this.lineReceiver.computePath(anyInt(), anyInt(), anyInt(), anyInt())).willReturn(mockPath);
        doNothing().when(this.lineReceiver).drawLine(anyList());

        createLineCmd.setParams(params);
        createLineCmd.execute();

        then(this.lineReceiver).should().drawLine(mockPath);
    }
}
