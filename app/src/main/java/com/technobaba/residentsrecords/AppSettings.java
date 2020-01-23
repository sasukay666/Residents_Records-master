package com.technobaba.residentsrecords;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AppSettings extends AppCompatActivity {

    String status;
    Switch notificationsSwitch;
    SeekBar notificationsVolume;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);

        Spinner spinner=(Spinner)findViewById(R.id.tone_spinner);
        ArrayAdapter arrayAdapter=ArrayAdapter.createFromResource(AppSettings.this, R.array.notification_tones,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?>parent, View view, int position, long id){
                    Toast.makeText(AppSettings.this,parent.getItemAtPosition(position)+" is selected.", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }

        });

        notificationsSwitch = (Switch) findViewById(R.id.notifications_switch);
        notificationsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (notificationsSwitch.isChecked()){
                    status="Notifications are On";
                }
                else {
                    status="Notifications are Off";
                }
                Toast.makeText(AppSettings.this, ""+status, Toast.LENGTH_SHORT).show();
            }
        });

        notificationsVolume = (SeekBar) findViewById(R.id.notifications_volume);
        notificationsVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(notificationsVolume.getProgress()==notificationsVolume.getMax()){
                    status="Maximum Volume Set";
                    Toast.makeText(AppSettings.this, ""+status, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
