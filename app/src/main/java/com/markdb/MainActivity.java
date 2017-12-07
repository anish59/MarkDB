package com.markdb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.markdb.models.Budget;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private android.widget.Button btnInsert;
    private Button btnShowResult;
    private android.widget.TextView txtResult;
    private List<Budget> budgetList;
    private View viewOne;
    private android.widget.EditText edtDeleteId;
    private Button btnDelete;
    private View viewTwo;
    private android.widget.EditText edtUpdateId;
    private android.widget.EditText edtMonth;
    private Button btnUpdate;
    private Button btnFillExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnFillExpense = (Button) findViewById(R.id.btnFillExpense);
        this.btnUpdate = (Button) findViewById(R.id.btnUpdate);
        this.edtMonth = (EditText) findViewById(R.id.edtMonth);
        this.edtUpdateId = (EditText) findViewById(R.id.edtUpdateId);
        this.viewTwo = (View) findViewById(R.id.viewTwo);
        this.btnDelete = (Button) findViewById(R.id.btnDelete);
        this.edtDeleteId = (EditText) findViewById(R.id.edtDeleteId);
        this.viewOne = (View) findViewById(R.id.viewOne);
        this.txtResult = (TextView) findViewById(R.id.txtResult);
        this.btnShowResult = (Button) findViewById(R.id.btnShowResult);
        this.btnInsert = (Button) findViewById(R.id.btnInsert);
        long time = Calendar.getInstance().getTimeInMillis();

        initListener(time);
        showData();

    }

    private void initListener(long time) {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Budget.insertBudget("Dec", 2000, time, time);
                showData();
            }
        });

        btnShowResult.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View view) {
                showData();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtDeleteId.getText().toString();
                if (TextUtils.isEmpty(id)) {
                    Toast.makeText(MainActivity.this, "Please enter Id", Toast.LENGTH_SHORT).show();
                    return;
                }

                Budget.deleteBudget(Long.parseLong(id));
                showData();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtUpdateId.getText().toString();
                String month = edtMonth.getText().toString();

                if (TextUtils.isEmpty(id) || TextUtils.isEmpty(month)) {
                    Toast.makeText(MainActivity.this, "Please enter Update data", Toast.LENGTH_SHORT).show();
                    return;
                }

                Budget.updateBudget(Long.parseLong(id), month);
                showData();
            }
        });

        btnFillExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private void showData() {
        budgetList = new ArrayList<>();
        budgetList = Budget.showAllBudget();
        if (budgetList != null && !budgetList.isEmpty()) {
            String result = "";

            for (Budget budget : budgetList) {
                result = String.format("%sId: %d Month:%s \n", result, budget.budgetId(), budget.budgetMonth());
            }
            txtResult.setText(result);
        } else {
            txtResult.setText(R.string.nothing_to_show);
        }
    }
}
