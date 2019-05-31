package org.homework.lexer.constants;

import org.homework.lexer.modules.State;
import org.homework.lexer.modules.Transition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AppConstants {

    private AppConstants() {

    }

    // Initializare state-uri fara tranzitii
    public static final Map<String, State> stateMap = new HashMap<String, State>() {{
        put("START", new State("START", false));
        put("CHARACTER_BEGIN", new State("CHARACTER_BEGIN", false));
        put("CHARACTER_BACKSLASH", new State("CHARACTER_BACKSLASH", false));
        put("CHARACTER", new State("CHARACTER", false));
        put("CHARACTER_END", new State("CHARACTER_END", true, "character"));
        put("COMMENT_BEGIN_//", new State("COMMENT_BEGIN_//", false));
        put("COMMENT_END_//", new State("COMMENT_END_//", true, "comment"));
        put("COMMENT_BEGIN_/*", new State("COMMENT_BEGIN_/*", false));
        put("COMMENT_END_*", new State("COMMENT_END_*", false));
        put("COMMENT_END_*/", new State("COMMENT_END_*/", true, "comment"));
        put("EXPONENT_BEGIN", new State("EXPONENT_BEGIN", false));
        put("EXPONENT_END", new State("EXPONENT_END", true, "exponent"));
        put("FORWARD_SLASH", new State("FORWARD_SLASH", true, "operator"));
        put("IDENTIFIER", new State("IDENTIFIER", true, "identifier"));
        put("INTEGER", new State("INTEGER", true, "integer"));
        put("HEXADECIMAL", new State("HEXADECIMAL", true, "hexadecimal"));
        put("NEGATIVE_SIGN", new State("NEGATIVE_SIGN", true, "operator"));
        put("NEWLINE", new State("NEWLINE", true, "newline"));
        put("OCTADECIMAL", new State("OCTADECIMAL", true, "octadecimal"));
        put("OPERATOR_BEGIN", new State("OPERATOR_BEGIN", true, "operator"));
        put("OPERATOR_END", new State("OPERATOR_END", true, "operator"));
        put("OPERATOR_|", new State("OPERATOR_|", true, "operator"));
        put("OPERATOR_&", new State("OPERATOR_&", true, "operator"));
        put("OPERATOR_<", new State("OPERATOR_<", true, "operator"));
        put("OPERATOR_>", new State("OPERATOR_>", true, "operator"));
        put("OPERATOR_<<", new State("OPERATOR_<<", true, "operator"));
        put("OPERATOR_>>", new State("OPERATOR_>>", true, "operator"));
        put("POINT", new State("POINT", true, "operator"));
        put("PLUS_SIGN", new State("PLUS_SIGN", true, "operator"));
        put("REAL_NUMBER", new State("REAL_NUMBER", true, "real number"));
        put("SPACE", new State("SPACE", true, "space"));
        put("SPECIAL_SYMBOLS", new State("SPECIAL_SYMBOLS", true, "special symbols"));
        put("STRING_BACKSLASH", new State("STRING_BACKSLASH", false));
        put("STRING_BEGIN", new State("STRING_BEGIN", false));
        put("STRING_END", new State("STRING_END", true, "string"));
        put("X", new State("X", false));
        put("ZERO", new State("ZERO", true, "integer"));
        put("FINAL", new State("FINAL", true, "final"));
        put("ERROR", new State("ERROR", true, "error"));
    }};

    //
    public static final Map<String, List<Transition>> transitionMap = new HashMap<String, List<Transition>>() {{
        put("START", new ArrayList<Transition>() {{
            add(new Transition("(\\/)", stateMap.get("FORWARD_SLASH")));
            add(new Transition("(\\')", stateMap.get("CHARACTER_BEGIN")));
            add(new Transition("([A-Z]|[a-z]|_)", stateMap.get("IDENTIFIER")));
            add(new Transition("([1-9])", stateMap.get("INTEGER")));
            add(new Transition("(\\-)", stateMap.get("NEGATIVE_SIGN")));
            add(new Transition("(\\n)", stateMap.get("NEWLINE")));
            add(new Transition("(\\*|\\%|\\=|\\!|\\^)", stateMap.get("OPERATOR_BEGIN")));
            add(new Transition("(\\,|\\:)", stateMap.get("OPERATOR_END")));
            add(new Transition("(\\&)", stateMap.get("OPERATOR_&")));
            add(new Transition("(\\|)", stateMap.get("OPERATOR_|")));
            add(new Transition("(\\<)", stateMap.get("OPERATOR_<")));
            add(new Transition("(\\>)", stateMap.get("OPERATOR_>")));
            add(new Transition("(\\.)", stateMap.get("POINT")));
            add(new Transition("(\\+)", stateMap.get("PLUS_SIGN")));
            add(new Transition("(\\s)", stateMap.get("SPACE")));
            add(new Transition("(\\[|\\]|\\(|\\)|\\{|\\}|\\;)", stateMap.get("SPECIAL_SYMBOLS")));
            add(new Transition("(\\\")", stateMap.get("STRING_BEGIN")));
            add(new Transition("([0])", stateMap.get("ZERO")));
            add(new Transition("(.)", stateMap.get("ERROR")));
        }});
        put("CHARACTER_BEGIN", new ArrayList<Transition>() {{
            add(new Transition("([^,\\\\,\\n])", stateMap.get("CHARACTER")));
            add(new Transition("(\\\\)", stateMap.get("CHARACTER_BACKSLASH")));
            add(new Transition("(\\n)", stateMap.get("ERROR")));
        }});
        put("CHARACTER_BACKSLASH", new ArrayList<Transition>() {{
            add(new Transition("([^,\\n])", stateMap.get("CHARACTER")));
            add(new Transition("(\\n)", stateMap.get("ERROR")));
        }});
        put("CHARACTER", new ArrayList<Transition>() {{
            add(new Transition("(\\')", stateMap.get("CHARACTER_END")));
            add(new Transition("(.)", stateMap.get("ERROR")));
        }});
        put("CHARACTER_END", new ArrayList<Transition>() {{

        }});
        put("COMMENT_BEGIN_//", new ArrayList<Transition>() {{
            add(new Transition("[^,\\n]", stateMap.get("COMMENT_BEGIN_//")));
            add(new Transition("(\\n)", stateMap.get("COMMENT_END_//")));
        }});
        put("COMMENT_END_//", new ArrayList<Transition>() {{

        }});
        put("COMMENT_BEGIN_/*", new ArrayList<Transition>() {{
            add(new Transition("([^,\\*])", stateMap.get("COMMENT_BEGIN_/*")));
            add(new Transition("(\\*)", stateMap.get("COMMENT_END_*")));
        }});
        put("COMMENT_END_*", new ArrayList<Transition>() {{
            add(new Transition("([^,\\*,\\/])", stateMap.get("COMMENT_BEGIN_/*")));
            add(new Transition("(\\*)", stateMap.get("COMMENT_END_*")));
            add(new Transition("(\\/)", stateMap.get("COMMENT_END_*/")));
        }});
        put("COMMENT_END_*/", new ArrayList<Transition>() {{

        }});
        put("EXPONENT_BEGIN", new ArrayList<Transition>() {{
            add(new Transition("(\\+|\\-|[0-9])", stateMap.get("EXPONENT_END")));
            add(new Transition("(.)", stateMap.get("ERROR")));
        }});
        put("EXPONENT_END", new ArrayList<Transition>() {{
            add(new Transition("([0-9])", stateMap.get("EXPONENT_END")));
        }});
        put("FORWARD_SLASH", new ArrayList<Transition>() {{
            add(new Transition("(\\/)", stateMap.get("COMMENT_BEGIN_//")));
            add(new Transition("(\\*)", stateMap.get("COMMENT_BEGIN_/*")));
        }});
        put("IDENTIFIER", new ArrayList<Transition>() {{
            add(new Transition("([A-Z]|[a-z]|[0-9]|_)", stateMap.get("IDENTIFIER")));
        }});
        put("INTEGER", new ArrayList<Transition>() {{
            add(new Transition("([0-9])", stateMap.get("INTEGER")));
            add(new Transition("([.])", stateMap.get("REAL_NUMBER")));
            add(new Transition("([e]|[E])", stateMap.get("EXPONENT_BEGIN")));
        }});
        put("HEXADECIMAL", new ArrayList<Transition>() {{
            add(new Transition("([0-9]|[a-f]|[A-F])", stateMap.get("HEXADECIMAL")));
            add(new Transition("([g-z]|[G-Z])", stateMap.get("ERROR")));
        }});
        put("NEGATIVE_SIGN", new ArrayList<Transition>() {{
            add(new Transition("(\\-|\\=)", stateMap.get("OPERATOR_END")));
            add(new Transition("([1-9])", stateMap.get("INTEGER")));
        }});
        put("NEWLINE", new ArrayList<Transition>() {{

        }});
        put("OCTADECIMAL", new ArrayList<Transition>() {{
            add(new Transition("([0-7])", stateMap.get("OCTADECIMAL")));
            add(new Transition("([a-z]|[A-Z]|[8-9])", stateMap.get("ERROR")));
        }});
        put("OPERATOR_BEGIN", new ArrayList<Transition>() {{
            add(new Transition("(\\=)", stateMap.get("OPERATOR_END")));
        }});
        put("OPERATOR_END", new ArrayList<Transition>() {{

        }});
        put("OPERATOR_|", new ArrayList<Transition>() {{
            add(new Transition("(\\|)", stateMap.get("OPERATOR_END")));
        }});
        put("OPERATOR_&", new ArrayList<Transition>() {{
            add(new Transition("(\\&)", stateMap.get("OPERATOR_END")));
        }});
        put("OPERATOR_<", new ArrayList<Transition>() {{
            add(new Transition("(\\<)", stateMap.get("OPERATOR_<<")));
        }});
        put("OPERATOR_>", new ArrayList<Transition>() {{
            add(new Transition("(\\>)", stateMap.get("OPERATOR_>>")));
        }});
        put("OPERATOR_<<", new ArrayList<Transition>() {{
            add(new Transition("(\\=)", stateMap.get("OPERATOR_END")));
        }});
        put("OPERATOR_>>", new ArrayList<Transition>() {{
            add(new Transition("(\\=)", stateMap.get("OPERATOR_END")));
        }});
        put("POINT", new ArrayList<Transition>() {{
            add(new Transition("([0-9])", stateMap.get("REAL_NUMBER")));
        }});
        put("PLUS_SIGN", new ArrayList<Transition>() {{
            add(new Transition("(\\+|\\=)", stateMap.get("OPERATOR_END")));
            add(new Transition("([1-9])", stateMap.get("INTEGER")));
        }});
        put("REAL_NUMBER", new ArrayList<Transition>() {{
            add(new Transition("([0-9])", stateMap.get("REAL_NUMBER")));
            add(new Transition("([e]|[E])", stateMap.get("EXPONENT_BEGIN")));
        }});
        put("SPACE", new ArrayList<Transition>() {{

        }});
        put("SPECIAL_SYMBOLS", new ArrayList<Transition>() {{

        }});
        put("STRING_BACKSLASH", new ArrayList<Transition>() {{
            add(new Transition("(.)", stateMap.get("STRING_BEGIN")));
            add(new Transition("([\\n])", stateMap.get("STRING_BEGIN")));
        }});
        put("STRING_BEGIN", new ArrayList<Transition>() {{
            add(new Transition("([^,\\\\,\\\",\\n])", stateMap.get("STRING_BEGIN")));
            add(new Transition("([\\\\])", stateMap.get("STRING_BACKSLASH")));
            add(new Transition("([\\\"])", stateMap.get("STRING_END")));
            add(new Transition("([\\n])", stateMap.get("ERROR")));
        }});
        put("STRING_END", new ArrayList<Transition>() {{

        }});
        put("X", new ArrayList<Transition>() {{
            add(new Transition("([0-9]|[a-f]|[A-F])", stateMap.get("HEXADECIMAL")));
            add(new Transition("(.)", stateMap.get("ERROR")));
        }});
        put("ZERO", new ArrayList<Transition>() {{
            add(new Transition("([x]|[X])", stateMap.get("X")));
            add(new Transition("([0-7])", stateMap.get("OCTADECIMAL")));
        }});
        put("FINAL", new ArrayList<Transition>() {{

        }});
        put("ERROR", new ArrayList<Transition>() {{

        }});
    }};

    public static List<State> getStates() {
        List<State> states = new ArrayList<State>();
        for (Map.Entry<String, State> state: stateMap.entrySet()) {
            String stateName = state.getKey();
            State stateValue = state.getValue();
            stateValue.setTransitions(transitionMap.get(stateName));
            states.add(stateValue);
        }
        return states;
    }

    public static State getStartState() {
        return stateMap.get("START");
    }
}
