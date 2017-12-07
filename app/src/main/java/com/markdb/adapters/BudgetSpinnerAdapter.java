package com.markdb.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.markdb.R;
import com.markdb.models.Budget;

import java.util.List;

public class BudgetSpinnerAdapter extends ArrayAdapter<Budget> {

    private List<Budget> items;
    private Activity activity;

    public BudgetSpinnerAdapter(Activity activity, List<Budget> items) {
        super(activity, android.R.layout.simple_list_item_1, items);
        this.items = items;
        this.activity = activity;
    }

    public void setBudgetSpinner(List<Budget> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView v = (TextView) super.getView(position, convertView, parent);

        if (v == null) {
            v = new TextView(activity);
        }
        v.setTextColor(Color.BLACK);
        v.setText(items.get(position).budgetMonth());
        return v;
    }

    @Override
    public Budget getItem(int position) {
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            v = inflater.inflate(R.layout.spinner_row, null);
        }
        TextView lbl = (TextView) v.findViewById(R.id.txtMonth);
        lbl.setTextColor(Color.BLACK);
        lbl.setText(items.get(position).budgetMonth());
        return v;
    }
}