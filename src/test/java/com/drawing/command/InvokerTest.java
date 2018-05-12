package com.drawing.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class InvokerTest {

    private Invoker invoker;

    @Before
    public void init(){
        this.invoker = new Invoker();
    }

    @Test
    public void shouldInvokeQuitCommandTest() throws CommandException {
        Command cmd = invoker.lookUpCommand(Invoker.QUIT);
        assertThat(cmd, instanceOf(QuitCmd.class));
    }

    @Test
    public void shouldInvokeDrawCanvasCommandTest() throws CommandException {
        Command cmd = invoker.lookUpCommand(Invoker.DRAW_CANVAS);
        assertThat(cmd, instanceOf(DrawCanvasCmd.class));
    }
}
