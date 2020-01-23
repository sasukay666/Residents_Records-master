package com.technobaba.residentsrecords;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void aboutPage(View v){
        startActivity(new Intent(this, AboutPage.class));
    }

    public void addNewRecord(View v) {
        startActivity(new Intent(this, AddNewRecord.class));
    }

    public void searchRecord(View v) {
        startActivity(new Intent(this, SearchPage.class));
    }

    public void viewRecord(View v) {
        startActivity(new Intent(this, ViewRecords.class));
    }

    public void openSettings(View v) {
        startActivity(new Intent(this, AppSettings.class));
    }

    public void openHelp(View v) {
        startActivity(new Intent(this, HelpPage.class));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
