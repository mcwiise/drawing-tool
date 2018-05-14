package com.drawing.command.receiver;

import com.drawing.board.Board;
import com.drawing.board.dao.BoardDAOException;
import com.drawing.board.dao.BoardDAO;
import org.junit.Assert;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;


@RunWith(MockitoJUnitRunner.class)
public class CanvasReceiverTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Mock
    private BoardDAO boardDAO;

    @InjectMocks
    private CanvasReceiver canvasReceiver;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldDraw2x2CanvasTest() throws BoardDAOException, ReceiverException {
        canvasReceiver.drawCanvas(2,2);

        then(this.boardDAO).should().save(any());
        Assert.assertEquals(TestUtils.getExpected("canvas/2x2.txt"),
                systemOutRule.getLog().substring(0, systemOutRule.getLog().length()-1));
    }

    @Test
    public void shouldDraw2x4CanvasTest() throws BoardDAOException, ReceiverException {

        canvasReceiver.drawCanvas(2,4);

        then(this.boardDAO).should().save(any());
        assertEquals(TestUtils.getExpected("canvas/2x4.txt"),
                systemOutRule.getLog().substring(0, systemOutRule.getLog().length()-1));
    }

    @Test
    public void shouldDraw4x2CanvasTest() throws BoardDAOException, ReceiverException {

        canvasReceiver.drawCanvas(4,2);

        then(this.boardDAO).should().save(any());
        assertEquals(TestUtils.getExpected("canvas/4x2.txt"),
                systemOutRule.getLog().substring(0, systemOutRule.getLog().length()-1));
    }
}
