package org.homework.lexer;

import org.homework.lexer.constants.AppConstants;
import org.homework.lexer.modules.Lexer;
import org.homework.lexer.modules.State;
import org.homework.lexer.modules.Token;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        BufferedWriter fileWriter = null;
        try {
            List<State> statesList = AppConstants.getStates();
            State startState = AppConstants.getStartState();
            String filePath = "src/org/homework/lexer/teste/aici.txt";

            Lexer lexer = new Lexer(statesList, startState, filePath);
            String tokenFilePath = "src/org/homework/lexer/tokens.txt";
            fileWriter = new BufferedWriter(new FileWriter(tokenFilePath));
            List<Token> tokenList = new ArrayList<Token>();

            while(!lexer.isDone()) {
                Token token = lexer.getToken();
                if (token != null ) {
                    tokenList.add(token);
                }
                if(lexer.isTokenAccepted(token)) {
                    fileWriter.write(token.toString() + "\n");
                    fileWriter.flush();
                }
            }

            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if(fileWriter != null)
                    fileWriter.close();
            } catch (IOException ex) {

            }
        }

    }
}
