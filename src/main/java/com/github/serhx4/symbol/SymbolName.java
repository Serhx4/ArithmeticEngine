package com.github.serhx4.symbol;

import java.util.regex.Pattern;

import static com.github.serhx4.symbol.SymbolName.SymbolType.*;

/**
 * Created by Serhii on 8/15/2022.
 */
public enum SymbolName {

    DIVIDER(2, "\\/", OPERATOR),
    MULTIPLIER(2, "\\*", OPERATOR),
    PLUS(1, "\\+", OPERATOR),
    MINUS(1, "\\-", OPERATOR),

    OPEN_BRACKET(5, "[(]", BRACKET),
    CLOSE_BRACKET(-5, "[)]", BRACKET),

    NEGATIVE_NUMBER(10, "\\#", NEGATIVE),

    DOUBLE(0, "(\\d+\\.?\\d+|\\d+)", NUMBER),

    DEFAULT(0, "", SymbolType.DEFAULT);

    public enum SymbolType {
        OPERATOR, BRACKET, NEGATIVE, NUMBER, DEFAULT
    }

    private int priority;
    private String regEx;
    private SymbolType symbolType;

    SymbolName(int priority, String regEx, SymbolType symbolType) {
        this.priority = priority;
        this.regEx = regEx;
        this.symbolType = symbolType;
    }

    public static SymbolName determineType(String input) {
        for (SymbolName type : SymbolName.values()) {
            if (Pattern.compile(type.getRegEx()).matcher(input).find()) return type;
        }

        return DEFAULT;
    }

    public String getRegEx() {
        return regEx;
    }

    public int getPriority() {
        return priority;
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }
}
