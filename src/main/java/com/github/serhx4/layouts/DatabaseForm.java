package com.github.serhx4.layouts;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Serhii on 8/15/2022.
 */
public class DatabaseForm {
    private JTextField textFieldFrom;
    private JTextField textFieldTo;
    private JButton showButton;
    private JPanel databasePanel;

    public DatabaseForm() {
        showButton.addActionListener(showListener());
    }

    private ActionListener showListener() {
        return e -> {
            InterfaceController.show(getDoubleFrom(), getDoubleTo());
        };
    }
    private double getDoubleFrom() {
        try {
            return Double.parseDouble(textFieldFrom.getText());
        } catch (Exception e){
            return 0;
        }
    }
    private double getDoubleTo() {
        try {
            return Double.parseDouble(textFieldTo.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public JPanel getDatabasePanel() {
        return databasePanel;
    }
}
