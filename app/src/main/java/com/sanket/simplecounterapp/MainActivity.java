package com.sanket.simplecounterapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    TextView counter;
    Vibrator Vibrator;
    int count;
    EditText count_name;
    ImageView save, reset, saved_data,settings;
    Button increase,decrease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        counter = findViewById(R.id.counter_id);
        count_name = findViewById(R.id.count_name);
        save = findViewById(R.id.save_button);
        reset = findViewById(R.id.reset);
        settings = findViewById(R.id.settings);
        saved_data = findViewById(R.id.saved_data);
        increase = findViewById(R.id.plus);
        decrease = findViewById(R.id.minus);
        count_name.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        Vibrator = (Vibrator)getSystemService(MainActivity.VIBRATOR_SERVICE);

        addData();


        count_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == count_name.getId())
                {
                    count_name.setCursorVisible(true);
                }
            }
        });
        count_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                count_name.setCursorVisible(false);
                if (event != null&& (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(count_name.getApplicationWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return false;
            }
        });


        increase.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                count++;
                Vibrator.vibrate(25);
                counter.setText(Integer.toString(count));
            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                count--;
                Vibrator.vibrate(25);
                counter.setText(Integer.toString(count));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                count = 0;
                count_name.setText("");
                Vibrator.vibrate(25);
                counter.setText(Integer.toString(count));
                Toast.makeText(getApplicationContext(),"Reset",Toast.LENGTH_LONG).show();
            }
        });

        saved_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator.vibrate(25);
                Intent intent = new Intent(getApplicationContext(),SavedData.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_left, R.anim.to_right);

            }

        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator.vibrate(25);
                Intent intent = new Intent(getApplicationContext(),Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right, R.anim.to_left);

            }

        });


    }


    public void addData(){
        save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Vibrator.vibrate(25);

                if (count == 0 && count_name.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"No Values",Toast.LENGTH_LONG).show();
                }

                else if (count_name.getText().toString().isEmpty())
                {
                    count_name.setText(R.string.empty_name);
                    boolean isInserted = myDb.insertData(counter.getText().toString(),count_name.getText().toString());
                    if(isInserted = true)
                        Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();

                }

                else {
                    boolean isInserted = myDb.insertData(counter.getText().toString(),count_name.getText().toString());

                    if(isInserted = true)
                        Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}

