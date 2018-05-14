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
    public void shouldInvokeCreateCanvasCommandTest() throws CommandException {
        Command cmd = invoker.lookUpCommand(Invoker.CREATE_CANVAS);
        assertThat(cmd, instanceOf(CreateCanvasCmd.class));
    }

    @Test
    public void shouldInvokeCreateLineCommandTest() throws CommandException {
        Command cmd = invoker.lookUpCommand(Invoker.CREATE_LINE);
        assertThat(cmd, instanceOf(CreateLineCmd.class));
    }

    @Test
    public void shouldInvokeCreateRectCommandTest() throws CommandException {
        Command cmd = invoker.lookUpCommand(Invoker.CREATE_RECT);
        assertThat(cmd, instanceOf(CreateRectCmd.class));
    }

    @Test(expected = CommandException.class)
    public void shouldThrowCommandExceptionWhenNotFoundTest() throws CommandException {
        Command cmd = invoker.lookUpCommand("adfasdfasdasdf");
        assertThat(cmd, instanceOf(CreateCanvasCmd.class));
    }
}
