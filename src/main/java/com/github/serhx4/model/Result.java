package com.github.serhx4.model;

/**
 * Created by Serhii on 8/15/2022.
 */
public class Result {
    int id;
    private String expression;
    private double result;

    public Result(int id, String expression, double result) {
        this.id = id;
        this.expression = expression;
        this.result = result;
    }

    public Result(String expression, double result) {
        this.expression = expression;
        this.result = result;
    }

    public String getExpression() {
        return expression;
    }

    public double getResult() {
        return result;
    }

    public int getId() { return id; }

    @Override
    public String toString() {
        return "id: " + id + " \t" + expression + " = " + result;
    }
}
