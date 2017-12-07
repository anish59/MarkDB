package com.markdb;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.markdb.adapters.BudgetSpinnerAdapter;
import com.markdb.models.Budget;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private android.widget.Spinner spinnerBudgetMonth;
    private Context context;
    private List<Budget> budgetList;
    private BudgetSpinnerAdapter mySpinnerAdapter;
    private android.widget.Button btnNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
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
    }
}

