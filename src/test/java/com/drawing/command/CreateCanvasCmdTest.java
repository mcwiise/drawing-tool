package com.drawing.command;

import com.drawing.command.receiver.CanvasReceiver;
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
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
public class CreateCanvasCmdTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Mock
    private CanvasReceiver canvasReceiver;

    @InjectMocks
    private CreateCanvasCmd createCanvasCmd;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateCanvas2x2Test() throws CommandException {

        List<String> params = Arrays.asList("2","2");
        doNothing().when(this.canvasReceiver).drawCanvas(anyInt(), anyInt());

        createCanvasCmd.setParams(params);
        createCanvasCmd.execute();

        then(this.canvasReceiver).should().drawCanvas(2,2);
    }

    @Test
    public void shouldCreateCanvas4x2Test() throws CommandException {

        List<String> params = Arrays.asList("4","2");
        doNothing().when(this.canvasReceiver).drawCanvas(anyInt(), anyInt());

        createCanvasCmd.setParams(params);
        createCanvasCmd.execute();

        then(this.canvasReceiver).should().drawCanvas(4,2);
    }

    @Test
    public void shouldCreateCanvas2x4Test() throws CommandException {

        List<String> params = Arrays.asList("2","4");
        doNothing().when(this.canvasReceiver).drawCanvas(anyInt(), anyInt());

        createCanvasCmd.setParams(params);
        createCanvasCmd.execute();

        then(this.canvasReceiver).should().drawCanvas(2,4);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommanExceptionWhenMoreNumberParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2","4","12","15");
        createCanvasCmd.setParams(params);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommanExceptionWhenLessNumberParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2");
        createCanvasCmd.setParams(params);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenWrongTypeParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2","e");
        createCanvasCmd.setParams(params);
    }
}
