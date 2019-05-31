package org.homework.lexer.modules;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FiniteStateMachine {

    private List<State> states;
    private State startState;
    private State currentState;
    private FileInputStream fileReader;
    private int currentCharInASCII;
    private String currentChar;

    public FiniteStateMachine(List<State> states, State startState, String filePath) throws IOException {
        this.states = states;
        this.startState = startState;

        File file = new File(filePath);
        this.fileReader = new FileInputStream(file);

        this.currentCharInASCII = this.fileReader.read();
        this.currentChar = String.valueOf((char)(currentCharInASCII));
    }

    public Map<String, Object> searchToken() throws IOException {
        Map<String, Object> tokenInfo = new HashMap<String, Object>();
        StringBuilder currentString = new StringBuilder();
        Boolean continueSearching = true;
        this.currentState = this.startState;

        if (currentCharInASCII < 0) {
            tokenInfo.put("endOfFile", true);
            return tokenInfo;
        }
        tokenInfo.put("endOfFile", false);

        while (currentCharInASCII >= 0) {
            continueSearching = false;

            for (Transition transition : this.currentState.getTransitions()) {
                if (this.currentChar.matches(transition.getPattern())) {
                    this.currentState = transition.getState();

                    currentString.append(this.currentChar);
                    continueSearching = true;

                    break;
                }
            }

            if (!continueSearching)
                break;

            this.currentCharInASCII = this.fileReader.read();
            this.currentChar = String.valueOf((char)(currentCharInASCII));
        }

        tokenInfo.put("tokenValue", currentString.toString());
        tokenInfo.put("tokenState", this.currentState);

        return tokenInfo;
    }
}
