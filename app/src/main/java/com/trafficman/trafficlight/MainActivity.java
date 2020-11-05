package com.trafficman.trafficlight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout bulb1;
    private LinearLayout bulb2;
    private LinearLayout bulb3;
    private Button button;
    private int counter = 0;

    private boolean start_stop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bulb1 = findViewById(R.id.bulb1);
        bulb2 = findViewById(R.id.bulb2);
        bulb3 = findViewById(R.id.bulb3);
        button = findViewById(R.id.main_button);
    }

    public void onClickListener(View view) {
        if (!start_stop) {
            start_stop = true;
            button.setText("Stop");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (start_stop) {
                        ++counter;
                        switch (counter) {
                            case 1:
                                bulb1.setBackgroundColor(getResources().getColor(R.color.green));
                                bulb2.setBackgroundColor(getResources().getColor(R.color.grey));
                                bulb3.setBackgroundColor(getResources().getColor(R.color.grey));
                                break;
                            case 2:
                                bulb2.setBackgroundColor(getResources().getColor(R.color.yellow));
                                bulb1.setBackgroundColor(getResources().getColor(R.color.grey));
                                bulb3.setBackgroundColor(getResources().getColor(R.color.grey));
                                break;
                            case 3:
                                bulb3.setBackgroundColor(getResources().getColor(R.color.red));
                                bulb2.setBackgroundColor(getResources().getColor(R.color.grey));
                                bulb1.setBackgroundColor(getResources().getColor(R.color.grey));
                                counter = 0;
                                break;
                        }
                        try {
                            Thread.sleep(800);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } else {
            start_stop = false;
            resetAll();
            button.setText("Start");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        start_stop = false;
    }

    private void resetAll(){
        bulb1.setBackgroundColor(getResources().getColor(R.color.grey));
        bulb2.setBackgroundColor(getResources().getColor(R.color.grey));
        bulb3.setBackgroundColor(getResources().getColor(R.color.grey));
        counter = 0;
    }
}