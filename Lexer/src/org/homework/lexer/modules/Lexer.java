package org.homework.lexer.modules;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Lexer {

    private FiniteStateMachine fsm;
    private Boolean isDone;

    public Lexer (List<State> states, State startState, String filePath) throws IOException {
        this.fsm = new FiniteStateMachine(states, startState, filePath);
        this.isDone = false;
    }

    public Boolean isDone() {
        return this.isDone;
    }

    public Token getToken() throws IOException {
        Token token = null;

        do {
            Map<String, Object> tokenInfo = this.fsm.searchToken();
            if ((Boolean) tokenInfo.get("endOfFile")) {
                this.isDone = true;
                break;
            }
            token = new Token(tokenInfo);
        } while (token == null);

        return token;
    }

    public Boolean isTokenAccepted(Token token) {
        if (token == null)
            return false;
        else if (!token.displayableToken())
            return false;
        return true;
    }
}
