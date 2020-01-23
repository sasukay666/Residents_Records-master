package com.technobaba.residentsrecords;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EntryFound extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_found);
    }

    public void editEntry(View v){
        if(v.getId()==R.id.delete){
            Toast.makeText(this, "Entry Deleted", Toast.LENGTH_SHORT).show();
            //code to delete entry from table will come here
        }
        else{
            Intent in = new Intent(this, EditPage.class);
            startActivity(in);
        }
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
