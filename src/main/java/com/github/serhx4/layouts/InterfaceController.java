package com.github.serhx4.layouts;

import com.github.serhx4.dao.ResultDao;
import com.github.serhx4.model.Result;
import com.github.serhx4.postflix.PostflixCalculator;
import com.github.serhx4.validator.Validator;

/**
 * Created by Serhii on 8/15/2022.
 */
public class InterfaceController {

    public static void calculate(String input) {
        input = Validator.formatInput(input);
        if (Validator.validate(input)) {
            Result result = PostflixCalculator.calculate(input);
            if (Double.isInfinite(result.getResult())) {
                appendLog("Error: dividing by 0 inside of expression");
            } else {
                ResultDao.save(result);
                GraphInterface.showAllResults();
                appendLog("Success! " + result.getExpression() + " = " + result.getResult());
            }
        } else {
            appendLog("Error. Wrong expression: " + input);
            GraphInterface.fillBasePanel(ResultDao.getResults());
        }
    }

    public static void recalculate(int id, String input) {
        input = Validator.formatInput(input);
        if(Validator.validate(input)) {
            ResultDao.update(id, PostflixCalculator.calculate(input));
        } else {
            appendLog("Wrong expression: " + input);
        }
        GraphInterface.showAllResults();
    }

    public static void delete(int id) {
        ResultDao.delete(id);
        GraphInterface.showAllResults();
    }

    public static void show(double from, double to) {
        GraphInterface.fillBasePanel(ResultDao.getResultsBetween(from,to));
    }

    public static void appendLog(String log) {
        GraphInterface.logForm.getLogArea().append("\n" + log);
    }
}
