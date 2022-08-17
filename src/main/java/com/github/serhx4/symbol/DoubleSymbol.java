package com.github.serhx4.symbol;

/**
 * Created by Serhii on 8/15/2022.
 */
public class DoubleSymbol extends Symbol {
    private double number;
    public DoubleSymbol(String value, SymbolName symbolName) {
        super(value, symbolName);
        this.number = Double.parseDouble(value);
    }

    public double getNumber() {
        return number;
    }
    public void setNegativeNumber() {
        number = number * -1;
    }
}
