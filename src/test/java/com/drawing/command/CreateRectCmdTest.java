package com.drawing.command;

import com.drawing.command.receiver.ReceiverException;
import com.drawing.command.receiver.RectReceiver;
import com.drawing.command.receiver.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class CreateRectCmdTest {

    @Mock
    private RectReceiver rectReceiver;

    @InjectMocks
    private CreateRectCmd createRectCmd;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test(expected = CommandException.class)
    public void shouldThrowExceptionWhenMoreNumberParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2","4","12","15", "23");
        createRectCmd.setParams(params);
    }


    @Test(expected = CommandException.class)
    public void shouldThrowExceptionWhenLessNumberParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2");
        createRectCmd.setParams(params);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowExceptionWhenWrongTypeParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2","sdf","15", "23");
        createRectCmd.setParams(params);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowExceptionWhenOutOfCanvasTest() throws CommandException, ReceiverException {
        given(this.rectReceiver.isRectangleInCanvas(any(), any(), any(), any())).willReturn(false);
        this.createRectCmd.execute();
    }

    @Test(expected = CommandException.class)
    public void shouldThrowExceptionWhenCannotDrawTest() throws CommandException, ReceiverException {
        given(this.rectReceiver.isRectangleInCanvas(any(), any(), any(), any())).willReturn(true);
        doThrow(ReceiverException.class).when(this.rectReceiver).drawRectangle(anyList());
        this.createRectCmd.execute();
    }

    @Test
    public void shouldCreateRectangleTest() throws ReceiverException, CommandException {
        List<String> params = Arrays.asList("16","1","20","3");
        given(this.rectReceiver.isRectangleInCanvas(any(), any(), any(), any())).willReturn(true);
        given(this.rectReceiver.computePaths(any(), any(), any(), any())).willReturn(TestUtils.getMockPaths());

        doNothing().when(this.rectReceiver).drawRectangle(anyList());

        createRectCmd.setParams(params);
        createRectCmd.execute();
    }
}
