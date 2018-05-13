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

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DrawLineCmdTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Mock
    private BoardReceiver boardReceiver;

    @InjectMocks
    private DrawLineCmd drawLineCmd;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommanExceptionWhenMoreNumberParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2","4","12","15", "23");
        drawLineCmd.setParams(params);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommanExceptionWhenLessNumberParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2");
        drawLineCmd.setParams(params);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenWrongTypeParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2","e","5", "$");
        drawLineCmd.setParams(params);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenNoHorizontalLineTest() throws CommandException {
        List<String> params = Arrays.asList("2","3","5", "4");
        drawLineCmd.setParams(params);
    }

    @Test(expected = CommandException.class)
    public void shouldDrawHorizontalLineTest() throws CommandException {
        List<String> params = Arrays.asList("2","3","5", "4");
        drawLineCmd.setParams(params);
    }
}
