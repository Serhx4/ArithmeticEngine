package com.github.serhx4.symbol;

/**
 * Created by Serhii on 8/15/2022.
 */
public class OperatorSymbol extends Symbol {
    private int priority;

    public OperatorSymbol(String value, SymbolName symbolName) {
        super(value, symbolName);
        this.priority = symbolName.getPriority();
    }

    public int getPriority() {
        return priority;
    }

    public void updatePriority(int update) {
        priority = priority + update;
    }
}
