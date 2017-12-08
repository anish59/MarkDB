package com.markdb.models;

import android.database.sqlite.SQLiteDatabase;

import com.google.auto.value.AutoValue;
import com.markdb.ExpenseModel;
import com.markdb.helper.dbHelper.DataBaseManger;
import com.squareup.sqldelight.RowMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anish on 08-12-2017.
 */
@AutoValue
public abstract class Expense implements ExpenseModel {
    private static final Factory<Expense> EXPENSE_FACTORY = new Factory<>(AutoValue_Expense::new);
    private static final RowMapper<Expense> EXPENSE_ROW_MAPPER = EXPENSE_FACTORY.select_all_expenseMapper();

    public static void insertExpenseData(long budgetId, long expenseAmount, long createdDateInt, long updateDateInt) {
        SQLiteDatabase sqLiteDatabase = DataBaseManger.getInstance().openDatabase();
        Expense.Insert_into_expense insert_into_expense = new Insert_into_expense(sqLiteDatabase);
        insert_into_expense.bind(budgetId, expenseAmount, createdDateInt, updateDateInt);
        insert_into_expense.program.execute();
        DataBaseManger.getInstance().closeDatabase();
    }

    public List<Expense> getAllExpenses() {
        List<Expense> allExpenses = new ArrayList<>();
//        SQLiteDatabase
        return allExpenses;
    }

}
