package com.example.heretic.wi_fine;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import static android.widget.Toast.makeText;

public class MainActivity extends Activity {

    public static List<ScanResult> wifiList;
    private WifiManager wifi;


    Button enableButton, disableButton, scanButton;
    ListView logList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logList = findViewById(R.id.listview);
        enableButton= findViewById(R.id.button);
        disableButton= findViewById(R.id.button2);
        scanButton = findViewById(R.id.button3);

        wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiReceiver receiver = new WifiReceiver();
        registerReceiver(receiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        enableButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                assert wifi != null;
                wifi.setWifiEnabled(true);
                Toast.makeText(getApplicationContext(),"Wifi Enabled",Toast.LENGTH_SHORT).show();
            }
        });

        disableButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                assert wifi != null;
                wifi.setWifiEnabled(false);
                Toast.makeText(getApplicationContext(),"Wifi Disabled",Toast.LENGTH_SHORT).show();
            }
        });

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScan();
            }
        });


    }

    public void setAdapter()
    {
        ListAdapter adapter = new ListAdapter(getApplicationContext(), wifiList);
        logList.setAdapter(adapter);
    }

    @SuppressLint("ShowToast")
    protected void startScan() {
        final LocationManager manager = (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        Log.v("enab",""+manager.isProviderEnabled(LocationManager.GPS_PROVIDER));
        if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            Toast.makeText(this,"GPS is disabled. Please turn it on",Toast.LENGTH_SHORT).show();
        else {
            if (wifi.isWifiEnabled()) {
                new CountDownTimer(30000, 5000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Log.v("seconds remaining: ", "" + millisUntilFinished / 1000);
                        wifi.startScan();
                        wifiList = wifi.getScanResults();
                        setAdapter();

                        makeText(getApplicationContext(), "Scan finished", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFinish() {
                        Log.v("done!", "done");
                    }
                }.start();
            }
        }



    }


    class WifiReceiver extends BroadcastReceiver {
        public void onReceive(Context c, Intent intent) {
        }
    }

}