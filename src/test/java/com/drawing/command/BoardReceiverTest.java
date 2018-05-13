package com.drawing.command;

import com.drawing.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BoardReceiverTest {

    @InjectMocks
    private BoardReceiver boardReceiver;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreate2x2CanvasTest(){
        String board = boardReceiver.createCanvas(2,2);
        assertEquals(TestUtils.getExpected("canvas/2x2.txt"), board);
    }

    @Test
    public void shouldCreate2x4CanvasTest(){
        String board = boardReceiver.createCanvas(2,4);
        assertEquals(TestUtils.getExpected("canvas/2x4.txt"), board);
    }

    @Test
    public void shouldCreate4x2CanvasTest(){
        String board = boardReceiver.createCanvas(4,2);
        assertEquals(TestUtils.getExpected("canvas/4x2.txt"), board);
    }
}
