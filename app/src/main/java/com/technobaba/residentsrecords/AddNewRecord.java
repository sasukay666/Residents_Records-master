package com.technobaba.residentsrecords;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddNewRecord extends AppCompatActivity {

    EditText rname, rage, rmob, raddress, rdetails, rother;
    DatabaseHelper dbhp;
    SQLiteDatabase db;
    Spinner spinner;
    ArrayAdapter arrayAdapter;
    String r_gender;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_record);

         rname = (EditText) findViewById(R.id.rname);
         rage = (EditText) findViewById(R.id.rage);
         rmob = (EditText) findViewById(R.id.rmob);
         raddress = (EditText) findViewById(R.id.raddress);
         rdetails = (EditText) findViewById(R.id.rdetails);
         rother = (EditText) findViewById(R.id.rother);

         save = (Button) findViewById(R.id.save);
         save.setEnabled(false);

        rname.addTextChangedListener(watcher);
        rage.addTextChangedListener(watcher);
        rmob.addTextChangedListener(watcher);
        raddress.addTextChangedListener(watcher);
        rdetails.addTextChangedListener(watcher);
        rother.addTextChangedListener(watcher);

        // code to show choices in gender menu
        spinner=(Spinner)findViewById(R.id.rgender);
        arrayAdapter=ArrayAdapter.createFromResource(this, R.array.gender_options,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
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
            }
                @Override
                public void onNothingSelected(AdapterView<?> parent)
                {
                }
            });
    }

    public void saveRecord(View v){
        if(v.getId() == R.id.save){     //Both buttons save and cancel actually invokes this same method. So if save is pressed if statements run and then finally finish(), otherwise only finish() runs in case if cancel pressed
            String r_name = rname.getText().toString();
            String r_rage = rage.getText().toString();
            String r_address = raddress.getText().toString();
            String r_details = rdetails.getText().toString();
            String r_mob = rmob.getText().toString();
            String r_other = rother.getText().toString();
            int r_age = Integer.parseInt(r_rage);

            dbhp = new DatabaseHelper(getApplicationContext());
            db = dbhp.getWritableDatabase();
            dbhp.addInfo(r_name,r_gender,r_age,r_mob,r_address,r_details,r_other,db);
            System.out.println("Table Info Saved");
            Toast.makeText(this, "New Record Added", Toast.LENGTH_SHORT).show();
            dbhp.close();
        }
        finish();
    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {}
        @Override
        public void afterTextChanged(Editable s) {
            if (rname.getText().toString().length() == 0 || (rage.getText().toString()).length() == 0 || rmob.getText().toString().length() == 0 || raddress.getText().toString().length() == 0 || rdetails.getText().toString().length() == 0 || rother.getText().toString().length() == 0){
                save.setEnabled(false);
            }
            else {
            save.setEnabled(true);
            }
        }
    };

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
