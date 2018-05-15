package com.drawing.command;

import java.util.List;

/**
 * Abstract representation of a {@link Command}. It keeps common functions used by commands
 */
public abstract class AbstractCmd {

    /**
     * Verifies it the type of parameters of a command are correct
     * @param params the list of params to verify
     * @return true if parameter types are correct.
     */
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
