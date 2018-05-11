package com.drawing.command;

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
public class CreateCanvasCmdTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Mock
    public BoardReceiver boardReceiver;

    @InjectMocks
    public CreateCanvasCmd createCanvasCmd;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateCanvas2x2Test(){

        given(boardReceiver.createCanvas()).willReturn("valencia");

        createCanvasCmd.execute();

        assertEquals("valencia", systemOutRule.getLog());
    }
}
