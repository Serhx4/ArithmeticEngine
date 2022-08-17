package com.github.serhx4.database;

import java.sql.*;

import static com.github.serhx4.database.TableNames.Names.*;

/**
 * Created by Serhii on 8/15/2022.
 */
public class SqLiteDatabase {
    private final static String urlLocal = "jdbc:sqlite:results.db";

    public static void initializeDatabase() {
        try {
            Connection conn = getConnection();

            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);

            String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + EXPRESSION + " TEXT NOT NULL, "
                    + RESULT + " DOUBLE NOT NULL DEFAULT 0);";

            statement.executeUpdate(createTable);
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(urlLocal);
    }

}
