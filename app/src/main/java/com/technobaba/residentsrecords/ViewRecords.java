package com.technobaba.residentsrecords;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ViewRecords extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase db;
    DatabaseHelper dbhp;
    ListDataAdapter dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);

        listView = (ListView) findViewById(R.id.listv);
        dbhp = new DatabaseHelper(getApplicationContext());
        db = dbhp.getReadableDatabase();
        Cursor cursor = dbhp.viewAll(db);
        dataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(dataAdapter);

        if (cursor.moveToFirst()) {
            do{
                String r_id, rname, rgender, r_age, rmob, raddress, rdetails, rother;
                r_id=cursor.getString(0);
                int rid=Integer.parseInt(r_id);
                rname=cursor.getString(1);
                rgender=cursor.getString(2);
                r_age=cursor.getString(3);
                int rage=Integer.parseInt(r_age);
                rmob=cursor.getString(4);
                raddress=cursor.getString(5);
                rdetails=cursor.getString(6);
                rother=cursor.getString(7);

                DataProvider record=new DataProvider(rid,rname,rgender,rage,rmob,raddress,rdetails,rother);
                dataAdapter.add(record);
            }while(cursor.moveToNext());
        }
        else{
            Toast.makeText(this, "Nothing to show", Toast.LENGTH_SHORT).show();
        }
        dbhp.close();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
