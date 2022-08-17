package com.github.serhx4.dao;

import com.github.serhx4.model.Result;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.github.serhx4.database.TableNames.Names.EXPRESSION;
import static com.github.serhx4.database.TableNames.Names.RESULT;
import static com.github.serhx4.database.TableNames.Names._ID;

/**
 * Created by Serhii on 8/15/2022.
 */
public class ResultMapper {
    public static Result resultMap(ResultSet resultSet) throws SQLException {
        return new Result(resultSet.getInt(_ID), resultSet.getString(EXPRESSION), resultSet.getDouble(RESULT));
    }
}
