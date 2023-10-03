package com.example.easycount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ListView countersListView;
    ConstraintLayout clearListButton;
    ConstraintLayout addNewCounterButton;

    //
    TextView counterTitleTextView;
    TextView counterResultTextView;
    ConstraintLayout subsButton;
    ConstraintLayout addButton;
    ConstraintLayout restartCounterButton;
    ConstraintLayout deleteCounterButton;
    ConstraintLayout variationCounterButton;

    public Integer result = 0;
    public Integer variation = 1;

    public Timer fixedTimer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViewElements();
        setListeners();
    }

    private void setViewElements(){
        countersListView = findViewById(R.id.counters_listview);
        clearListButton = findViewById(R.id.clear_list_button);
        addNewCounterButton = findViewById(R.id.new_counter_button);

        //
        counterTitleTextView = findViewById(R.id.title_counter_textview);
        counterResultTextView = findViewById(R.id.result_counter_textview);
        subsButton = findViewById(R.id.subs_button);
        addButton = findViewById(R.id.add_button);
        restartCounterButton = findViewById(R.id.restart_counter_button);
        deleteCounterButton = findViewById(R.id.delete_counter_button);
        variationCounterButton = findViewById(R.id.variation_counter_button);

        counterResultTextView.setText(String.valueOf(result));
    }

    private void setListeners(){

        clearListButton.setOnClickListener(view -> {

        });

        addNewCounterButton.setOnClickListener(view -> {

        });

        //

        subsButton.setOnClickListener(view -> {
            result = result - variation;
            counterResultTextView.setText(String.valueOf(result));
        });

        addButton.setOnClickListener(view -> {
            result = result + variation;
            counterResultTextView.setText(String.valueOf(result));
        });

        restartCounterButton.setOnClickListener(view -> {
            result = 0;
            counterResultTextView.setText(String.valueOf(result));
        });
    }

}