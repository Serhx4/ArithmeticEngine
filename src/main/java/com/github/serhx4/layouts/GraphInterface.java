package com.github.serhx4.layouts;

import com.github.serhx4.dao.ResultDao;
import com.github.serhx4.model.Result;

import javax.swing.*;
import java.util.List;

/**
 * Created by Serhii on 8/15/2022.
 */
public class GraphInterface {
    private static JFrame jFrame;
    private static JPanel mainPanel;
    private static ArithmeticForm arithmeticForm;
    private static DatabaseForm databaseForm;
    private static JPanel basePanel;
    static LogForm logForm;
    private static JTextArea logText;

    public static void initialize() {
        jFrame = new MainFrame();

        // Main Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Arithmetic Panel
        arithmeticForm = new ArithmeticForm();

        databaseForm = new DatabaseForm();

        // Panel for results
        basePanel = new JPanel();
        basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.Y_AXIS));

        // Log Panel
        logForm = new LogForm();
        logText = logForm.getLogArea();

        setUpInterface();
    }

    public static void setUpInterface() {
        showAllResults();
        mainPanel.add(arithmeticForm.getArithmeticPanel());
        mainPanel.add(databaseForm.getDatabasePanel());
        mainPanel.add(new JScrollPane(basePanel));
        mainPanel.add(new JScrollPane(logForm.getLogPanel()));

        jFrame.add(mainPanel);
        jFrame.revalidate();
    }

    public static void fillBasePanel(List<Result> results) {
        basePanel.removeAll();
        results.forEach(result -> basePanel.add(new ResultForm(result).getResultPanel()));
        jFrame.revalidate();
    }

    public static void showAllResults() {
        fillBasePanel(ResultDao.getResults());
    }
}
