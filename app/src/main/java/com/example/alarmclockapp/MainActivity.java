package com.example.alarmclockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextClock;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.Toolbar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
TextClock currentTime;
TimePicker alarmTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        alarmTime=findViewById(R.id.timePicker1);
        currentTime=findViewById(R.id.tc1);


    }
    public void OnToggleClicked(View view)
    {
        if(((ToggleButton)view).isChecked()) {
            Toast.makeText(this, "Alarm on", Toast.LENGTH_SHORT).show();


            //I ama using Timer instead of Alarm Manager
            Timer timer = new Timer();
            Ringtone ringtone = RingtoneManager.getRingtone(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (currentTime.getText().toString().equals(Alarmtime())) {
                        ringtone.setVolume(1);
                        ringtone.play();

                    } else {
                        ringtone.stop();
                    }

                }
            }, 0, 1000);
        }


    }

    private String Alarmtime() {
        Integer hours=alarmTime.getHour();
        Integer min=alarmTime.getMinute();
        String alarmMin;
        if(min<10)
        {
            alarmMin="0"+min.toString();
        }
        else
        {
            alarmMin=min.toString();
        }
        String str;
        if(hours>12)
        {
            hours= hours-12;
            str=hours.toString().concat(":").concat(min.toString()).concat(" PM");
            return str;
        }
        str=hours.toString().concat(":").concat(alarmMin).concat(" AM");
        return str;
    }
}