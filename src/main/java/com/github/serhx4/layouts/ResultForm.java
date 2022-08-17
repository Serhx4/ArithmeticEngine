package com.github.serhx4.layouts;

import com.github.serhx4.model.Result;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Serhii on 8/15/2022.
 */
public class ResultForm {
    private JLabel idLabel;
    private JTextField expressionField;
    private JLabel resultField;
    private JButton editButton;
    private JButton deleteButton;
    private JButton recalculateButton;
    private JPanel resultPanel;
    private Result result;

    public ResultForm(Result result) {
        this.result = result;
        fillForm(result);
        editButton.addActionListener(editListener());
        recalculateButton.addActionListener(recalculateListener());
        deleteButton.addActionListener(deleteListener());
    }

    public void fillForm (Result result) {
        idLabel.setText(result.getId() + "");
        expressionField.setText(result.getExpression());
        resultField.setText(result.getResult() + "");
    }

    public JPanel getResultPanel() {
        return resultPanel;
    }

    private ActionListener editListener() {
        return e -> {
          expressionField.setEditable(true);
          editButton.setVisible(false);
          recalculateButton.setVisible(true);
        };
    }

    private ActionListener recalculateListener() {
        return e -> {
            InterfaceController.recalculate(result.getId(), getInput());
            expressionField.setEditable(false);
            recalculateButton.setVisible(false);
            editButton.setVisible(true);
        };
    }
    private ActionListener deleteListener() {
        return e -> {
            InterfaceController.delete(result.getId());
        };
    }

    private String getInput() {
        return expressionField.getText();
    }
}
