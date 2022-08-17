package com.github.serhx4;

import com.github.serhx4.database.SqLiteDatabase;
import com.github.serhx4.layouts.GraphInterface;

/**
 * Created by Serhii on 8/15/2022.
 */
public class ArithmeticEngine {

    public static void main(String[] args) {
        SqLiteDatabase.initializeDatabase();
        GraphInterface.initialize();
    }
}
