package com.drawing.client;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * This is a representation of the user input. It parses the command line
 * to this object
 */
public class Input {

    private String action;
    private List<String> parameters;

    public Input(){
        this.action = "";
        this.parameters = new ArrayList<>();
    }

    /**
     * getter
     * @return the command or action to perform in the board
     */
    public String getAction() {
        return action;
    }

    /**
     * setter
     * @param action the action or command to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Adds a parameter to the list of parameters
     * @param parameter the parameter to add
     */
    public void addParameter(String parameter) {
        this.parameters.add(parameter);
    }

    /**
     * getter
     * @return the list of parameters for the given action or command
     */
    public List<String> getParameters(){
        return parameters;
    }

    /**
     * Parses a user's input to this representation. It uses a {@link StringTokenizer} to
     * iterate over the parameters
     * @param line the console input
     * @return a representation of an {@link Input}
     */
    public static Input transform (String line){
        StringTokenizer st = new StringTokenizer(line, " ");
        Input input = new Input();
        int tokens=st.countTokens();

        for(int i=0; i<tokens; i++){
            if (i==0){
                input.setAction(st.nextToken());
            } else {
                input.addParameter(st.nextToken());
            }
        }
        return input;
    }
}
