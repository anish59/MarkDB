package com.markdb;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.markdb.adapters.BudgetSpinnerAdapter;
import com.markdb.models.Budget;
import com.markdb.models.Expense;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private android.widget.Spinner spinnerBudgetMonth;
    private Context context;
    private List<Budget> budgetList;
    private BudgetSpinnerAdapter mySpinnerAdapter;
    private android.widget.Button btnNotify;
    private Button btnInsert;
    private android.widget.TextView txtShowResult;
    private Button btnShowAllData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.btnShowAllData = (Button) findViewById(R.id.btnShowAllData);
        this.txtShowResult = (TextView) findViewById(R.id.txtShowResult);
        this.btnInsert = (Button) findViewById(R.id.btnInsert);
        this.btnNotify = (Button) findViewById(R.id.btnNotify);

        context = this;
        this.spinnerBudgetMonth = (Spinner) findViewById(R.id.spinnerBudgetMonth);

        budgetList = new ArrayList<>();
        budgetList = Budget.showAllBudget();

        mySpinnerAdapter = new BudgetSpinnerAdapter(SecondActivity.this, budgetList);
        spinnerBudgetMonth.setAdapter(mySpinnerAdapter);

        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                budgetList.remove(0);
                mySpinnerAdapter.setBudgetSpinner(budgetList);
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long expenseAmount = 2000;
                long cDate = Calendar.getInstance().getTimeInMillis();
                long uDate = Calendar.getInstance().getTimeInMillis();
                long budgetId = ((Budget) spinnerBudgetMonth.getSelectedItem()).budgetId();
                Expense.insertExpenseData(budgetId, expenseAmount, cDate, uDate);
            }
        });

        btnShowAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Budget.ExpenditureDetail> expenditures = Budget.showAllDetail(6);//hardcoded argument
                for (int i = 0; i < expenditures.size(); i++) {
                    Log.e("Dats: [" + i + "] ", "Month :" + expenditures.get(i).Budget().budgetMonth() + " ExpenseAmount: " + expenditures.get(i).Expense().expenseAmount());
                }
            }
        });
    }
}

