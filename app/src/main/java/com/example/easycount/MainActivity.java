package com.example.easycount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

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

        subsButton.setOnClickListener(view -> {
            result = result - variation;
            counterResultTextView.setText(String.valueOf(result));
        });

        subsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    fixedTimer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            result = result - variation;
                        }
                    }, 1,1);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    //cancel timer
                    fixedTimer.cancel();
                }
                return false;
            }
        });

        addButton.setOnClickListener(view -> {
            result = result + variation;
            counterResultTextView.setText(String.valueOf(result));
        });

        addButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    fixedTimer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            result = result + variation;
                        }
                    }, 1,1);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    //cancel timer
                    fixedTimer.cancel();
                }
                return false;
            }
        });

        restartCounterButton.setOnClickListener(view -> {
            result = 0;
            counterResultTextView.setText(String.valueOf(result));
        });
    }

    public void initTimer() {
        fixedTimer = new Timer();

    }
}