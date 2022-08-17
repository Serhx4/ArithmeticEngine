package com.github.serhx4.postflix;

import com.github.serhx4.model.Result;
import com.github.serhx4.symbol.DoubleSymbol;
import com.github.serhx4.symbol.OperatorSymbol;
import com.github.serhx4.symbol.Symbol;
import com.github.serhx4.symbol.SymbolName;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Serhii on 8/15/2022.
 */
public class PostflixCalculator {

    public static Result calculate(String input) {
        return new Result(input, calculatePostflix(getPostflixStack(getInflixStack(input))));
    }

    private static Deque<Symbol> getInflixStack(String input) {
        Deque<Symbol> stack = new ArrayDeque<>();
        Matcher matcher = Pattern.compile("(\\d+\\.?\\d+|\\d+)|\\*|/|\\+|[(]|[)]|-|#").matcher(input);

        while (matcher.find()) {
            String value = input.substring(matcher.start(), matcher.end());
            SymbolName symbolName = SymbolName.determineType(value);
            switch (symbolName.getSymbolType()) {
                case NUMBER:
                    stack.add(new DoubleSymbol(value, symbolName));
                    break;
                case OPERATOR:
                case BRACKET:
                    stack.add(new OperatorSymbol(value, symbolName));
                    break;
                default:
                    stack.add(new Symbol(value, SymbolName.determineType(value)));
                    break;
            }
        }
//        stack.forEach(symbol -> System.out.println(symbol.getValue() + "\t\t" + symbol.getSymbolName().name()));
        return stack;
    }

    private static Deque<Symbol> getPostflixStack(Deque<Symbol> inflixStack) {
        Deque<Symbol> operatorsStack = new ArrayDeque<>();
        Deque<Symbol> output = new ArrayDeque<>();

        int priority = 0;
        boolean makeNegative = false;

        for (Symbol symbol : inflixStack) {
            switch (symbol.getSymbolName().getSymbolType()) {

                case NUMBER:
                    // check for makeNegative before adding. turn off negative
                    if (makeNegative) {
                        ((DoubleSymbol)symbol).setNegativeNumber();
                        makeNegative = false;
                    }
                    output.addFirst(symbol);
                    break;

                case OPERATOR:
                    // update priority
                    ((OperatorSymbol)symbol).updatePriority(priority);

                    if (operatorsStack.size() > 0) {

                        while (operatorsStack.peek() != null) {
                            // check priority with prev one operator (if exist)
                            if (((OperatorSymbol) operatorsStack.peek()).getPriority() > ((OperatorSymbol) symbol).getPriority()) {

                                // removing top operator from stack and add to output
                                output.addFirst(operatorsStack.poll());

                                if (operatorsStack.peek() == null) {
                                    operatorsStack.addFirst(symbol);
                                    break;
                                }
                            } else {
                                // else add current operator to stack
                                operatorsStack.addFirst(symbol);
                                break;
                            }
                        }

                    } else {
                        // else add current operator to stack
                        operatorsStack.addFirst(symbol);
                    }
                    break;

                case NEGATIVE:
                    makeNegative = true;
                    // turn on makeNegative condition
                    break;

                case BRACKET:
                    // add priority from bracket's
                    priority = ((OperatorSymbol)symbol).getPriority() + priority;
                    break;
            }
        }
        // finally add all operators left
        operatorsStack.forEach(output::addFirst);

        return output;
    }

    private static double calculatePostflix(Deque<Symbol> postflixStack) {
        Deque<Symbol> stack = new ArrayDeque<>();
        Iterator<Symbol> reverse = postflixStack.descendingIterator();

        while (reverse.hasNext()) {
            Symbol symbol = reverse.next();
            switch (symbol.getSymbolName().getSymbolType()) {
                case NUMBER:
                    stack.addFirst(symbol);
                    break;
                case OPERATOR:
                    double second = ((DoubleSymbol)stack.pollFirst()).getNumber();
                    double first = ((DoubleSymbol)stack.pollFirst()).getNumber();
                    double result = 0;

                    switch (symbol.getSymbolName()) {
                        case PLUS:
                            result = first + second;
                            break;
                        case MINUS:
                            result = first - second;
                            break;
                        case MULTIPLIER:
                            result = first * second;
                            break;
                        case DIVIDER:
                            // if second = 0 and operator = / stop calculating and return 0
//                            if (second == 0)

                            result = first / second;
                            break;
                    }
                    DoubleSymbol calc = new DoubleSymbol(result+"", SymbolName.DOUBLE);
                    stack.addFirst(calc);
                    break;
            }
        }
        return ((DoubleSymbol)stack.getFirst()).getNumber();
    }
}