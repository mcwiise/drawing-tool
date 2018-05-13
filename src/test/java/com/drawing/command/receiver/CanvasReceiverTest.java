package com.drawing.command.receiver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class CanvasReceiverTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    private CanvasReceiver canvasReceiver;

    @Before
    public void init(){
        this.canvasReceiver = new CanvasReceiver();
    }

    @Test
    public void shouldDraw2x2CanvasTest(){
        canvasReceiver.drawCanvas(2,2);
        Assert.assertEquals(TestUtils.getExpected("canvas/2x2.txt"),
                systemOutRule.getLog().substring(0, systemOutRule.getLog().length()-1));
    }

    @Test
    public void shouldDraw2x4CanvasTest(){
        canvasReceiver.drawCanvas(2,4);
        assertEquals(TestUtils.getExpected("canvas/2x4.txt"),
                systemOutRule.getLog().substring(0, systemOutRule.getLog().length()-1));
    }

    @Test
    public void shouldDraw4x2CanvasTest(){
        canvasReceiver.drawCanvas(4,2);
        assertEquals(TestUtils.getExpected("canvas/4x2.txt"),
                systemOutRule.getLog().substring(0, systemOutRule.getLog().length()-1));
    }
}
