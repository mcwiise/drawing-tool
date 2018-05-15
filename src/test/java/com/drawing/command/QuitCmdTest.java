package com.drawing.command;

import com.drawing.board.dao.BoardDAO;
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

@RunWith(MockitoJUnitRunner.class)
public class QuitCmdTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Mock
    private BoardDAO boardDAO;

    @InjectMocks
    public QuitCmd quitCmd;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldQuitTest(){
        quitCmd.execute();
        assertEquals("Bye!!",
                systemOutRule.getLog().substring(0, systemOutRule.getLog().length()-1));
    }
}
