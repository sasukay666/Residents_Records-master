package com.technobaba.residentsrecords;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SearchPage extends AppCompatActivity {

    DatabaseHelper dbhp;
    SQLiteDatabase db;
    AutoCompleteTextView ac;
    Button search;
    int found_id;
    List<String> names_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        search = (Button) findViewById(R.id.search);
        search.setEnabled(false);

        dbhp = new DatabaseHelper(getApplicationContext());
        db = dbhp.getReadableDatabase();
        Cursor cursor = dbhp.viewNameAndId(db);  // viewName from DatabaseHelper returns names of the stored records
        if(cursor.getCount()==0){
            Toast.makeText(this, "Their are currently no records in Database", Toast.LENGTH_SHORT).show();
        }
        else {
            cursor.moveToFirst();
            do {
                names_list.add(cursor.getString(cursor.getColumnIndex("rid"))+" - "+cursor.getString(cursor.getColumnIndex("rname")));
            }while (cursor.moveToNext());

            ac = (AutoCompleteTextView) findViewById(R.id.ac);
            ArrayAdapter<String> auto_comp_adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, names_list);
            ac.setAdapter(auto_comp_adapter);
            ac.setThreshold(1);
            ac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                    String query, id;
                    query = (String) parent.getItemAtPosition(position);
                    id = query.substring( 0, query.indexOf(" ")); //Trims the query to get id of the found record
                    found_id = Integer.parseInt(id);
                    search.setEnabled(true); //Enables the Search button which then takes to the edit page which displays all the stored values of that particular record
                }
            });
        }
        dbhp.close();

    }

    public void searchFunction(View v){
            Toast.makeText(this, "Match Found", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,EditPage.class);
            intent.putExtra("ID",found_id);
            startActivity(intent);
            finish();
    }

    public void deleteAll(View v){
        DatabaseHelper dbhp = new DatabaseHelper(this);
        SQLiteDatabase db = dbhp.getWritableDatabase();
        dbhp.clearAll(db);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}

