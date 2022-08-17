package com.github.serhx4.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Serhii on 8/15/2022.
 */
public class Validator {

    public static boolean validate(String input) {
        return bracketsAreCorrect(input) && hasValidCharacters(input) && !hasInvalidOperators(input);
    }

    static boolean hasValidCharacters(String input) {
        String validCharacters = "[()\\s\\d+-=*#]+";
        return input.matches(validCharacters);
    }

    static boolean hasInvalidOperators(String input) {
        // any combo with (*/+--.#) OR 'x.x.x' pattern OR 'x(' or ')x'
        String wrongOperator =
        ("([(]|\\+|\\*|\\/|\\.|\\#|\\-)(\\+|\\*|\\/|-{2,}|\\.|[)]|\\#{2,}|$)|(\\.\\d\\.)|(\\d[(])|([)]\\d)");
        return Pattern.compile(wrongOperator).matcher(input).find();
    }

    public static String formatInput(String input) {

        return replaceRegex(input, "^-?(\\d+\\.?\\d+|\\d+)|([(]|\\+|\\*|\\/)-(\\d+\\.?\\d+|\\d+)",
                "-", "#").replace(" ", "").replace(",", ".");
    }

    private static String replaceRegex(String input, String regex, String target, String replacement) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        String element;
        while (matcher.find()){
            element = input.substring(matcher.start(),matcher.end());
            element = element.replace(target, replacement);
            input = input.replace(input.substring(matcher.start(), matcher.end()), element);
        }
        return input;
    }

    private static boolean bracketsAreCorrect(String input) {
        int count = 0;
        for (char c : input.toCharArray()){
            if (c == '(') count++;
            if (c == ')'){
                if (count == 0) return false;
                count--;
            }
        }
        return count == 0;
    }
}
