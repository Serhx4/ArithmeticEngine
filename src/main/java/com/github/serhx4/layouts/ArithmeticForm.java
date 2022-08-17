package com.github.serhx4.layouts;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Serhii on 8/15/2022.
 */
public class ArithmeticForm {
    private JTextField inputTextField;
    private JButton calculateButton;
    private JPanel arithmeticPanel;

    ArithmeticForm() {
        calculateButton.addActionListener(calculateListener());
    }

    public JPanel getArithmeticPanel() {
        return arithmeticPanel;
    }

    private String getInput() {
        return inputTextField.getText();
    }

    // Calculate! button
    private ActionListener calculateListener() {
        return e -> {
            InterfaceController.calculate(getInput());
        };
    }

}
