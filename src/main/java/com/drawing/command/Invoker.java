package com.drawing.command;

import com.drawing.command.receiver.CanvasReceiver;
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

    private Map<String, Command> cmdCatalog;
    private CanvasReceiver canvasReceiver;
    private LineReceiver lineReceiver;
    private RectReceiver rectReceiver;


    public Invoker(){
        this.initReceivers();
        cmdCatalog = new HashMap<>();
        cmdCatalog.put(CREATE_CANVAS, new CreateCanvasCmd(this.canvasReceiver));
        cmdCatalog.put(CREATE_LINE, new CreateLineCmd(this.lineReceiver));
        cmdCatalog.put(CREATE_RECT, new CreateRectCmd(this.rectReceiver, this.lineReceiver));
        cmdCatalog.put(QUIT, new QuitCmd());
    }

    private void initReceivers(){
        this.canvasReceiver = new CanvasReceiver();
        this.lineReceiver = new LineReceiver();
        this.rectReceiver = new RectReceiver();
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
