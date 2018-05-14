package com.drawing.command;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class QuitCmdTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    public QuitCmd quitCmd;

    @Before
    public void init(){
        this.quitCmd = new QuitCmd();
    }

    @Test
    public void shouldQuitTest(){
        quitCmd.execute();
        assertEquals("Bye!!",
                systemOutRule.getLog().substring(0, systemOutRule.getLog().length()-1));
    }
}
