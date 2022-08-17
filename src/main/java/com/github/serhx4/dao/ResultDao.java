package com.github.serhx4.dao;

import com.github.serhx4.database.SqLiteDatabase;
import com.github.serhx4.model.Result;
import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.github.serhx4.database.TableNames.Names.*;

/**
 * Created by Serhii on 8/15/2022.
 */
public class ResultDao {

    public static List<Result> getResults() {
        List<Result> results = new ArrayList<Result>();
        try {
            Statement statement = SqLiteDatabase.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            while (resultSet.next()) {
                results.add(ResultMapper.resultMap(resultSet));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static void save(Result result) {
        try {
            PreparedStatement preparedStatement
                    = SqLiteDatabase.getConnection().prepareStatement
                        ("INSERT INTO " + TABLE_NAME + " (" + EXPRESSION + ", " + RESULT + ")"
                                + " VALUES(?, ?)");
            preparedStatement.setString(1, result.getExpression());
            preparedStatement.setDouble(2, result.getResult());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Result show(int id) {
        Result result = null;
        try {
            PreparedStatement preparedStatement
                    = SqLiteDatabase.getConnection().prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = ResultMapper.resultMap(resultSet);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Result> getResultsBetween(double min, double max) {
        List<Result> results = new ArrayList<Result>();
        try {
            PreparedStatement statement = SqLiteDatabase.getConnection().prepareStatement
                    ("SELECT * FROM " + TABLE_NAME + " WHERE " + RESULT + " BETWEEN " + "?" + " AND " + "?");
            statement.setDouble(1,min);
            statement.setDouble(2, max);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                results.add(ResultMapper.resultMap(resultSet));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static void update(int id, Result newResult){
        try {
            PreparedStatement preparedStatement
                    = SqLiteDatabase.getConnection()
                    .prepareStatement
                        ("UPDATE " + TABLE_NAME + " SET " + EXPRESSION + "=?, " + RESULT + "=? WHERE " + _ID + "=?");
            preparedStatement.setString(1, newResult.getExpression());
            preparedStatement.setDouble(2,newResult.getResult());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            PreparedStatement preparedStatement =
                    SqLiteDatabase.getConnection().prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE " +_ID + "=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
