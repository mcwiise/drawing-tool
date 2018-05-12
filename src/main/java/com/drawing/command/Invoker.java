package com.drawing.command;

import java.util.HashMap;
import java.util.Map;

public class Invoker {

    public static final Character DRAW_CANVAS = 'C';
    public static final Character QUIT = 'Q';

    private Map<Character, Command> commandMap;
    private BoardReceiver boardReceiver;

    public Invoker(){
        this.boardReceiver = new BoardReceiver();
        commandMap = new HashMap<>();
        commandMap.put('C', new DrawCanvasCmd(this.boardReceiver));
        commandMap.put('Q', new QuitCommand());
    }

    public Command lookUpCommand(Character action){
        return commandMap.getOrDefault(action, null);
    }

}
