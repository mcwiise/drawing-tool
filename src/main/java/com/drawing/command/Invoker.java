package com.drawing.command;

import com.drawing.command.receiver.CanvasReceiver;
import com.drawing.command.receiver.LineReceiver;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Invoker {

    public static final String DRAW_CANVAS = "C";
    public static final String QUIT = "Q";

    private Map<String, Command> cmdCatalog;
    private CanvasReceiver canvasReceiver;
    private LineReceiver lineReceiver;


    public Invoker(){
        this.initReceivers();
        cmdCatalog = new HashMap<>();
        cmdCatalog.put("C", new CreateCanvasCmd(this.canvasReceiver));
        cmdCatalog.put("L", new CreateLineCmd(this.lineReceiver));
        cmdCatalog.put("Q", new QuitCmd());
    }

    private void initReceivers(){
        this.canvasReceiver = new CanvasReceiver();
        this.lineReceiver = new LineReceiver();
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
