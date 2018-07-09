package com.sanket.simplecounterapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class SavedData extends AppCompatActivity {

    DatabaseHelper myDb;
    Cursor cursor;
    List_Adapter list_adapter;
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    ImageView cancel;
    Vibrator Vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_data);

        Vibrator = (Vibrator)getSystemService(MainActivity.VIBRATOR_SERVICE);
        listView = findViewById(R.id.list_view);
        cancel = findViewById(R.id.cancel);
        myDb= new DatabaseHelper(getApplicationContext());
        sqLiteDatabase = myDb.getReadableDatabase();
        list_adapter = new List_Adapter(getApplicationContext(),R.layout.data_list);
        listView.setAdapter(list_adapter);
        cursor = myDb.getAllData(sqLiteDatabase);

        if(cursor.moveToFirst())
        {
            do {
                String id,name,count;
                id = cursor.getString(0);
                count= cursor.getString(1);
                name = cursor.getString(2);
                data_list dl = new data_list(id,name,count);
                list_adapter.add(dl);

            } while (cursor.moveToNext());
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vibrator.vibrate(25);
                finish();
                overridePendingTransition(R.anim.from_right, R.anim.to_left);

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.from_right, R.anim.to_left);
    }
}
