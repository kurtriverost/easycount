package com.example.easycount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Models.Counter;

public class CounterAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Counter> counterArrayList;

    public CounterAdapter(Context context, ArrayList<Counter> counterArrayList) {
        this.context = context;
        this.counterArrayList = counterArrayList;
    }

    @Override
    public int getCount() {
        return counterArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return counterArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contador, parent, false);
        }

        TextView nombreTextView = convertView.findViewById(R.id.nombreTextView);
        TextView valorTextView = convertView.findViewById(R.id.valorTextView);

        Counter counter = counterArrayList.get(position);

        nombreTextView.setText(counter.getNombre());
        valorTextView.setText(String.valueOf(counter.getValor()));

        return convertView;
    }
}
