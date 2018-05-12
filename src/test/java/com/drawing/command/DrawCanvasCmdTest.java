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

import static org.junit.Assert.assertEquals;
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
    public void shouldDrawCanvas2x2Test(){

        String testFile = "canvas/2x2.txt";

        given(boardReceiver.createCanvas()).willReturn(TestUtils.getExpected(testFile));

        drawCanvasCmd.execute();

        assertEquals(TestUtils.getExpected(testFile), systemOutRule.getLog());
    }

    @Test
    public void shouldDrawCanvas4x2Test(){

        String testFile = "canvas/4x2.txt";

        given(boardReceiver.createCanvas()).willReturn(TestUtils.getExpected(testFile));

        drawCanvasCmd.execute();

        assertEquals(TestUtils.getExpected(testFile), systemOutRule.getLog());
    }

    @Test
    public void shouldDrawCanvas2x4Test(){

        String testFile = "canvas/2x4.txt";

        given(boardReceiver.createCanvas()).willReturn(TestUtils.getExpected(testFile));

        drawCanvasCmd.execute();

        assertEquals(TestUtils.getExpected(testFile), systemOutRule.getLog());
    }
}
