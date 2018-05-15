package com.drawing.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class InputTest {

    @Test
    public void shouldTransformCanvasCommandLineTest(){
        Input input = Input.transform("C 23 23");
        assertEquals("C",input.getAction());
    }
}
