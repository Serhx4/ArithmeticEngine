package com.github.serhx4.symbol;

/**
 * Created by Serhii on 8/15/2022.
 */
public class Symbol {
    private String value;
    private SymbolName symbolName;

    public Symbol(String value, SymbolName symbolName) {
        this.value = value;
        this.symbolName = symbolName;
    }


    public SymbolName getSymbolName() {
        return symbolName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
