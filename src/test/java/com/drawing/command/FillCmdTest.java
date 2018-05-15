package com.drawing.command;

import com.drawing.command.receiver.FillReciever;
import com.drawing.command.receiver.ReceiverException;
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
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class FillCmdTest {

    @Mock
    private FillReciever fillReciever;

    @InjectMocks
    private FillCmd fillCmd;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowExceptionWhenMoreNumberParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2", "4", "12", "15", "23");
        fillCmd.setParams(params);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowExceptionWhenLessNumberParamsTest() throws CommandException {
        List<String> params = Arrays.asList("2");
        fillCmd.setParams(params);
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenWrongTypeParamsTest() throws CommandException {
        List<String> params = Arrays.asList("asd", "2", "15");
        fillCmd.setParams(params);
    }

    @Test
    public void shouldFillTest() throws CommandException, ReceiverException {
        given(this.fillReciever.isPointOnCanvas(any())).willReturn(true);
        List<String> params = Arrays.asList("2", "2", "b");
        fillCmd.setParams(params);
        fillCmd.execute();
    }
}
