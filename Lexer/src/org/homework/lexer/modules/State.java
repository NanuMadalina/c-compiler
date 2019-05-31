package org.homework.lexer.modules;

import java.util.List;

public class State {

    private String name;
    private List<Transition> transitions;
    private Boolean finalState = false;
    private String displayName = "";

    public State(String name, Boolean finalState) {
        this.name = name;
        this.finalState = finalState;
    }

    public State(String name, Boolean finalState, String displayName) {
        this.name = name;
        this.finalState = finalState;
        this.displayName = displayName;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public Boolean getFinalState() {
        return finalState;
    }

    public void setFinalState(Boolean finalState) {
        this.finalState = finalState;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
