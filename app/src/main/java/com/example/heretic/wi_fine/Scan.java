package com.example.heretic.wi_fine;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Scan extends Activity{

    Button stopButton;
    public static ArrayList<String> strengthLog;
    //boolean contVar = true;

    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stopButton =  (Button) findViewById(R.id.stopButton);
        strengthLog = new ArrayList<>();
        strengthLog.clear();
        strengthLog.add("Time\t\tStrength Level");

        int t=0;
        //Time time = new Time();
        //time.setToNow();
        //long start = time.toMillis(true);
        try {
            while (t<=10) {
          //      time.setToNow();
          //      long now = time.toMillis(true);
          //      if (now >= start + 3000) {
                    WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    int level = 0;
                    if (wifi != null)
                        level = WifiManager.calculateSignalLevel(wifi.getConnectionInfo().getRssi(), 5);
                    strengthLog.add(t + "\t\t\t" + level);
                    t += 1;
                    Thread.sleep(1000);
          //      }
            }


            //Toast .makeText(getApplicationContext(),"Scan finished",Toast.LENGTH_SHORT);
            Intent i = new Intent(getApplicationContext(),StrengthLog.class);
            startActivity(i);
            setContentView(R.layout.log_strength);
        }
        catch (Exception ignored)
        {

        }

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
