package com.drawing.command;

import com.drawing.command.receiver.CanvasReceiver;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Invoker {

    public static final String DRAW_CANVAS = "C";
    public static final String QUIT = "Q";

    private Map<String, Command> cmdCatalog;
    private CanvasReceiver canvasReceiver;

    public Invoker(){
        this.canvasReceiver = new CanvasReceiver();
        cmdCatalog = new HashMap<>();
        cmdCatalog.put("C", new CreateCanvasCmd(this.canvasReceiver));
        cmdCatalog.put("Q", new QuitCmd());
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
