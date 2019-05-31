package org.homework.lexer.modules;

import java.util.*;

public class Token {
    public static List<String> tokenTypes = Arrays.asList(
            "character", "comment","exponent", "final","identifier", "keyword",
            "integer", "hexadecimal", "newline","octadecimal", "operator",
            "real number", "space", "special symbols", "string", "error"
    );

    private static List<String> tokenTables = Arrays.asList(
            "identifier", "keyword", "operator", "string", "error"
    );

    private static List<String> keywordTable = Arrays.asList(
            "auto", "break", "case", "char", "const", "continue", "default", "do",
            "double", "else", "enum", "extern", "float", "for", "goto", "if",
            "int", "long", "register", "return", "short", "signed", "sizeof", "static",
            "struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while"
    );

    private static List<String> identifierTable = new ArrayList<String>();
    private static List<String> stringTable = new ArrayList<String>();
    private static List<String> operatorTable = new ArrayList<String>();
    private static List<String> errorTable = new ArrayList<String>();

    private static List<String> skipTokenList = Arrays.asList("comment", "newline", "space");

    public Integer tokenTypeId;
    private Integer tokenValueId;
    private Integer tokenTableId;

    public Token(Map<String, Object> tokenInfo) {
        State endState = (State) tokenInfo.get("tokenState");
        String tokenValue = (String) tokenInfo.get("tokenValue");

        Map<String, Integer> processedTokenInfo = Token.processTokenInfo(endState, tokenValue);
        this.tokenTypeId = processedTokenInfo.get("tokenTypeId");
        this.tokenValueId = processedTokenInfo.get("tokenValueId");
        this.tokenTableId = processedTokenInfo.get("tokenTableId");
    }

    private static Map<String, Integer> processTokenInfo(State endState, String tokenValue) {
        Map<String, Integer> processedTokenInfo = new HashMap<>();
        int tokenTypeIdForMap;
        int tokenValueIdForMap;
        int tokenTableIdForMap;

        if (endState.getFinalState() && !endState.getDisplayName().equals("error")) {
            if (endState.getDisplayName().equals("identifier") && keywordTable.contains(tokenValue))
                tokenTypeIdForMap = Token.tokenTypes.indexOf("keyword");
            else
                tokenTypeIdForMap = Token.tokenTypes.indexOf(endState.getDisplayName());
            tokenValueIdForMap = Token.processValidValue(endState.getDisplayName(), tokenValue);
            tokenTableIdForMap = Token.getTableId(endState.getDisplayName(), tokenValue);
        } else {
            tokenTypeIdForMap = Token.tokenTypes.indexOf("error");
            tokenValueIdForMap = Token.processErrorValue(tokenValue);
            tokenTableIdForMap = Token.tokenTables.indexOf("error");
        }

        processedTokenInfo.put("tokenTypeId", tokenTypeIdForMap);
        processedTokenInfo.put("tokenValueId", tokenValueIdForMap);
        processedTokenInfo.put("tokenTableId", tokenTableIdForMap);

        return processedTokenInfo;
    }

    private static int processValidValue(String stateDisplayName, String validValue) {
        if (stateDisplayName.equals("identifier")) {
            if (keywordTable.contains(validValue)) {
                return keywordTable.indexOf(validValue);
            } else {//valoare unica incarcata
                if (!identifierTable.contains(validValue))
                    identifierTable.add(validValue);
                return identifierTable.indexOf(validValue);
            }
        } else if (stateDisplayName.equals("operator")) {
            if (!operatorTable.contains(validValue))
                operatorTable.add(validValue);
            return operatorTable.indexOf(validValue);
        } else {
            if (!stringTable.contains(validValue))
                stringTable.add(validValue);
            return stringTable.indexOf(validValue);
        }
    }

    private static int getTableId(String stateDisplayName, String validValue) {
        if (stateDisplayName.equals("identifier")) {
            if (keywordTable.contains(validValue)) {
                return Token.tokenTables.indexOf("keyword");
            } else {
                return Token.tokenTables.indexOf("identifier");
            }
        } else if (stateDisplayName.equals("operator")) {
            return Token.tokenTables.indexOf("operator");
        } else {
            return Token.tokenTables.indexOf("string");
        }
    }

    private static int processErrorValue(String errorValue) {
        String explicitErrorValue = "this element is not a token: " + errorValue;

        if (errorValue.length() > 0) {
            if (errorValue.charAt(0) == '\'') {
                explicitErrorValue = "character token not properly ended: " + errorValue;
            } else if (errorValue.charAt(0) == '\"') {
                explicitErrorValue = "string token not properly ended: " + errorValue;
            } else if (errorValue.charAt(0) == '0' && errorValue.length() > 1) {
                if (errorValue.charAt(1) == 'x' && errorValue.charAt(1) == 'X') {
                    explicitErrorValue = "hexadecimal token is written wrong: " + errorValue;
                } else {
                    explicitErrorValue = "octadecimal token is written wrong: " + errorValue;
                }
            } else if (errorValue.charAt(0) != 'e' || errorValue.charAt(0) != 'E') {
                explicitErrorValue = "exponent token is written wrong: " + errorValue;
            } else {
                explicitErrorValue = "undefined error: " + errorValue;
            }
        }

        if (!errorTable.contains(explicitErrorValue))
            errorTable.add(explicitErrorValue);
        return errorTable.indexOf(explicitErrorValue);
    }

    public Boolean displayableToken() {
        if (skipTokenList.contains(tokenTypes.get(tokenTypeId)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String tokenTypeToString = Token.tokenTypes.get(this.tokenTypeId);
        String tokenTableToString = Token.tokenTables.get(this.tokenTableId);
        String tokenValueToString;

        switch(tokenTableToString)
        {
            case "identifier":
                tokenValueToString = identifierTable.get(this.tokenValueId);
                break;
            case "keyword":
                tokenValueToString = keywordTable.get(this.tokenValueId);
                break;
            case "operator":
                tokenValueToString = operatorTable.get(this.tokenValueId);
                break;
            case "error":
                tokenValueToString = errorTable.get(this.tokenValueId);
                break;
            default:
                tokenValueToString = stringTable.get(this.tokenValueId);
        }

        return tokenTypeToString + " - " + tokenValueToString.trim();
    }
}
