package com.drawing.client;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Input {

    private String action;
    private List<String> parameters;

    public Input(){
        this.action = "";
        this.parameters = new ArrayList<>();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void addParameter(String parameter) {
        this.parameters.add(parameter);
    }

    public List<String> getParameters(){
        return parameters;
    }

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
