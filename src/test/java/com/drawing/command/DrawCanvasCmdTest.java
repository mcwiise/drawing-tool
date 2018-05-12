package com.drawing.command;

import com.drawing.utils.TestUtils;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class DrawCanvasCmdTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Mock
    private BoardReceiver boardReceiver;

    @InjectMocks
    private DrawCanvasCmd drawCanvasCmd;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldDrawCanvas2x2Test() throws CommandException {

        String testFile = "canvas/2x2.txt";
        List<String> params = Arrays.asList("2","2");
        given(boardReceiver.createCanvas(anyInt(), anyInt())).willReturn(TestUtils.getExpected(testFile));

        drawCanvasCmd.setParams(params);
        drawCanvasCmd.execute();

        assertEquals(TestUtils.getExpected(testFile), systemOutRule.getLog());
    }

    @Test
    public void shouldDrawCanvas4x2Test() throws CommandException {

        String testFile = "canvas/4x2.txt";
        List<String> params = Arrays.asList("4","2");
        given(boardReceiver.createCanvas(anyInt(), anyInt())).willReturn(TestUtils.getExpected(testFile));

        drawCanvasCmd.setParams(params);
        drawCanvasCmd.execute();

        assertEquals(TestUtils.getExpected(testFile), systemOutRule.getLog());
    }

    @Test
    public void shouldDrawCanvas2x4Test() throws CommandException {

        String testFile = "canvas/2x4.txt";
        List<String> params = Arrays.asList("2","4");
        given(boardReceiver.createCanvas(anyInt(), anyInt())).willReturn(TestUtils.getExpected(testFile));

        drawCanvasCmd.setParams(params);
        drawCanvasCmd.execute();

        assertEquals(TestUtils.getExpected(testFile), systemOutRule.getLog());
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommanExceptionWhenMoreNumberParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2","4","12","15");
        drawCanvasCmd.setParams(params);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommanExceptionWhenLessNumberParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2");
        drawCanvasCmd.setParams(params);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenWrongTypeParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2","e");
        drawCanvasCmd.setParams(params);
    }
}
