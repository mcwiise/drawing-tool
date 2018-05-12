package com.drawing.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Invoker {

    public static final String DRAW_CANVAS = "C";
    public static final String QUIT = "Q";

    private Map<String, Command> cmdCatalog;
    private BoardReceiver boardReceiver;

    public Invoker(){
        this.boardReceiver = new BoardReceiver();
        cmdCatalog = new HashMap<>();
        cmdCatalog.put("C", new DrawCanvasCmd(this.boardReceiver));
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
