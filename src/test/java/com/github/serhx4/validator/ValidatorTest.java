package com.github.serhx4.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Serhx4 on 8/16/2022.
 */
class ValidatorTest {

    private String input;

    @BeforeEach
    void setUp() {
        input = "2+2";
        System.out.println("Test input: " + input);
    }

    @Test
    void hasValidCharacters() {
        Assertions.assertTrue(Validator.hasValidCharacters(input));
    }

    @Test
    void hasValidCharacters1() {
        Assertions.assertFalse(Validator.hasInvalidOperators(input));
    }

}