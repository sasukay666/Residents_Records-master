package com.technobaba.residentsrecords;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EditPage extends AppCompatActivity {

    TextView rid;
    EditText rname, rage, rmob, raddress, rdetails, rother;
    String r_gender;
    Spinner spinner;
    int found_id;
    DatabaseHelper dbhp;
    SQLiteDatabase db;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);

        found_id = getIntent().getIntExtra("ID",0);
        dbhp = new DatabaseHelper(getApplicationContext());
        db = dbhp.getReadableDatabase();
        Cursor cursor = dbhp.findContactActivity(found_id,db);
        cursor.moveToFirst();
        dbhp.close();

        rid = (TextView) findViewById(R.id.edit_rid);
        rname = (EditText) findViewById(R.id.edit_rname);
        spinner = (Spinner) findViewById(R.id.edit_rgender);
        rage = (EditText) findViewById(R.id.edit_rage);
        rmob = (EditText) findViewById(R.id.edit_rmob);
        raddress = (EditText) findViewById(R.id.edit_raddress);
        rdetails = (EditText) findViewById(R.id.edit_rdetails);
        rother = (EditText) findViewById(R.id.edit_rother);

        rid.setText(Integer.toString(found_id));
        rname.setHint(cursor.getString(cursor.getColumnIndex("rname")));
        rname.setText(cursor.getString(cursor.getColumnIndex("rname")));
        rage.setHint(cursor.getString(cursor.getColumnIndex("rage")));
        rage.setText(cursor.getString(cursor.getColumnIndex("rage")));
        rmob.setHint(cursor.getString(cursor.getColumnIndex("rmob")));
        rmob.setText(cursor.getString(cursor.getColumnIndex("rmob")));
        raddress.setHint(cursor.getString(cursor.getColumnIndex("raddress")));
        raddress.setText(cursor.getString(cursor.getColumnIndex("raddress")));
        rdetails.setHint(cursor.getString(cursor.getColumnIndex("rdetails")));
        rdetails.setText(cursor.getString(cursor.getColumnIndex("rdetails")));
        rother.setHint(cursor.getString(cursor.getColumnIndex("rother")));
        rother.setText(cursor.getString(cursor.getColumnIndex("rother")));

        r_gender = cursor.getString(cursor.getColumnIndex("rgender"));
        int position = -1;
        if( r_gender.equals("-"))
            position = 0;
        if( r_gender.equals("M"))
            position = 1;
        if( r_gender.equals("F"))
            position = 2;
        if( r_gender.equals("O"))
            position = 3;

        arrayAdapter=ArrayAdapter.createFromResource(this, R.array.gender_options,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(position);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()  //used only in case if gender is changed
        {
            @Override
            public void onItemSelected(AdapterView<?>parent, View view, int position, long id){
                if(position==0)
                    r_gender="-";
                if(position==1)
                    r_gender="M";
                if(position==2)
                    r_gender="F";
                if(position==3)
                    r_gender="O";
                System.out.println("r_gender: "+r_gender);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });

    }

    public void editRecord(View v){
        String r_name = rname.getText().toString();
        String r_rage = rage.getText().toString();
        String r_address = raddress.getText().toString();
        String r_details = rdetails.getText().toString();
        String r_mob = rmob.getText().toString();
        String r_other = rother.getText().toString();
        int r_age = Integer.parseInt(r_rage);

        System.out.println(found_id+"- "+r_name+", "+r_gender+", "+r_age+", "+r_mob+", "+r_address+", "+r_details+", "+r_other);
        dbhp = new DatabaseHelper(getApplicationContext());
        db = dbhp.getWritableDatabase();
        dbhp.editInfo(found_id,r_name,r_gender,r_age,r_mob,r_address,r_details,r_other,db);
        Toast.makeText(this, "Record Edited successfully", Toast.LENGTH_SHORT).show();
        dbhp.close();
    }

    public void deleteRecord(View v){
        dbhp = new DatabaseHelper(getApplicationContext());
        db = dbhp.getReadableDatabase();
        dbhp.deleteById(found_id,db);
            Toast.makeText(this, "Record Deleted", Toast.LENGTH_SHORT).show();
            finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
