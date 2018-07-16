package com.sanket.simplecounterapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.sanket.simplecounterapp.DatabaseHelper.DATABASE_NAME;

public class Settings extends AppCompatActivity {

    private TextView delete_data;
    DatabaseHelper myDb;
    Cursor cursor;
    android.os.Vibrator Vibrator;
    SQLiteDatabase sqLiteDatabase;
    ImageView cancel;
    TextView rate,source_code,other_apps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        delete_data = findViewById(R.id.delete_data);
        myDb= new DatabaseHelper(getApplicationContext());
        cancel = findViewById(R.id.cancel);
        rate = findViewById(R.id.rate);
        source_code = findViewById(R.id.source_code);
        other_apps = findViewById(R.id.other_apps);
        sqLiteDatabase = myDb.getWritableDatabase();
        cursor = myDb.getAllData(sqLiteDatabase);
        Vibrator = (Vibrator)getSystemService(Settings.VIBRATOR_SERVICE);


        delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Vibrator.vibrate(25);
                AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        getApplicationContext().deleteDatabase(myDb.getDatabaseName());
                        Vibrator.vibrate(25);
                        Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Vibrator.vibrate(25);
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator.vibrate(25);
                finish();
                overridePendingTransition(R.anim.from_left, R.anim.to_right);

            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator.vibrate(25);

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=PackageName")));


            }
        });

        source_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator.vibrate(25);

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://goo.gl/XoEUQQ"));
                startActivity(intent);

            }
        });

        other_apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator.vibrate(25);

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=7976342366781866089")));


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.from_left, R.anim.to_right);
    }
}
