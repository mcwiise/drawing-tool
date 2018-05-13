package com.drawing.command;

import java.util.List;

public abstract class AbstractCmd {

    public boolean isCorrectTypeParams(List<String> params){
        return params.stream().allMatch(e -> {
            try {
                Integer.parseInt(e);
                return true;
            } catch (NumberFormatException nfe) {
                return false;
            }
        });
    }
}
