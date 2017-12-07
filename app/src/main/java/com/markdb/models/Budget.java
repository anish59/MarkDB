package com.markdb.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.auto.value.AutoValue;
import com.markdb.BudgetModel;
import com.markdb.helper.dbHelper.DataBaseManger;
import com.squareup.sqldelight.RowMapper;
import com.squareup.sqldelight.SqlDelightStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anish on 06-12-2017.
 */
@AutoValue
public abstract class Budget implements BudgetModel {
    public static final Factory<Budget> BUDGET_FACTORY = new Factory<>(AutoValue_Budget::new);
    public static final RowMapper<Budget> ROW_MAPPER = BUDGET_FACTORY.select_all_budgetMapper();

    public static void insertBudget(String budgetMonth, long budgetAmount, long createdDateInt, long updatedDateInt) {
        SQLiteDatabase sqLiteDatabase = DataBaseManger.getInstance().openDatabase();
        Budget.Insert_budget insert_budget = new Insert_budget(sqLiteDatabase);
        insert_budget.bind(budgetMonth, budgetAmount, createdDateInt, updatedDateInt);//using bind we pass the arguments
        insert_budget.program.execute();//when its update or delete we use executeUpdateDelete() method
        DataBaseManger.getInstance().closeDatabase();
    }

    public static List<Budget> showAllBudget() {
        List<Budget> budgets = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = DataBaseManger.getInstance().openDatabase();

        SqlDelightStatement sqlDelightStatement = BUDGET_FACTORY.select_all_budget();
        Cursor cursor = sqLiteDatabase.rawQuery(sqlDelightStatement.statement, sqlDelightStatement.args);

        while (cursor.moveToNext()) {
            budgets.add(Budget.ROW_MAPPER.map(cursor));
        }
        DataBaseManger.closeCursor(cursor);
        DataBaseManger.getInstance().closeDatabase();
        return budgets;
    }

    public static void deleteBudget(long budgetID) {
        SQLiteDatabase sqLiteDatabase = DataBaseManger.getInstance().openDatabase();

        Budget.Delete_budget delete_budget = new Delete_budget(sqLiteDatabase);
        delete_budget.bind(budgetID);

        delete_budget.program.executeUpdateDelete();
        DataBaseManger.getInstance().closeDatabase();
    }

    public static void updateBudget(long budgetId, String month) {
        SQLiteDatabase sqLiteDatabase = DataBaseManger.getInstance().openDatabase();

        Budget.Update_budget update_budget = new Update_budget(sqLiteDatabase);
        update_budget.bind(month, budgetId);

        update_budget.program.executeUpdateDelete();
        DataBaseManger.getInstance().closeDatabase();
    }

}
