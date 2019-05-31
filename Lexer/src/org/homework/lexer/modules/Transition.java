package org.homework.lexer.modules;

public class Transition {

    private String pattern;
    private State state;

    public Transition(String pattern, State state) {
        this.pattern = pattern;
        this.state = state;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
