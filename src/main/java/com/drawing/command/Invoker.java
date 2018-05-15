package com.drawing.command;

import com.drawing.board.dao.BoardDAO;
import com.drawing.board.dao.BoardDAOException;
import com.drawing.board.dao.BoardDAOImpl;
import com.drawing.command.receiver.CanvasReceiver;
import com.drawing.command.receiver.FillReciever;
import com.drawing.command.receiver.LineReceiver;
import com.drawing.command.receiver.RectReceiver;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Invoker {

    public static final String CREATE_CANVAS = "C";
    public static final String QUIT = "Q";
    public static final String CREATE_LINE = "L";
    public static final String CREATE_RECT = "R";
    public static final String BUCKET_FILL = "B";

    private Map<String, Command> cmdCatalog;
    private CanvasReceiver canvasReceiver;
    private LineReceiver lineReceiver;
    private RectReceiver rectReceiver;
    private FillReciever fillReciever;

    private BoardDAO boardDAO;


    public Invoker() throws CommandException{
        try {
            this.initReceivers();
            this.boardDAO.init(BoardDAO.BOARD_FILE);
            cmdCatalog = new HashMap<>();
            cmdCatalog.put(CREATE_CANVAS, new CreateCanvasCmd(this.canvasReceiver));
            cmdCatalog.put(CREATE_LINE, new CreateLineCmd(this.lineReceiver));
            cmdCatalog.put(CREATE_RECT, new CreateRectCmd(this.rectReceiver));
            cmdCatalog.put(QUIT, new QuitCmd(this.boardDAO));
            cmdCatalog.put(BUCKET_FILL, new FillCmd(this.fillReciever));
        } catch (BoardDAOException e) {
            System.out.println(e.getMessage());
            throw new CommandException("Cannot initialize game. ");
        }
    }

    private void initReceivers(){
        this.boardDAO = new BoardDAOImpl();
        this.canvasReceiver = new CanvasReceiver(this.boardDAO);
        this.lineReceiver = new LineReceiver(this.boardDAO);
        this.rectReceiver = new RectReceiver(this.boardDAO);
        this.fillReciever = new FillReciever(this.boardDAO);
    }

    public Command lookUpCommand(String action) throws CommandException{
        Optional cmdOptional = Optional.ofNullable(this.cmdCatalog.get(action));
        if(cmdOptional.isPresent()){
            return (Command) cmdOptional.get();
        } else {
            throw new CommandException("Command not found");
        }
    }

}
