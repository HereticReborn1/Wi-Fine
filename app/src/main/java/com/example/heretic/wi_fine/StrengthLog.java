package com.example.heretic.wi_fine;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class StrengthLog extends Activity {
    ListView logList;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> strengthLog = Scan.strengthLog;
        logList =  findViewById(R.id.list_item);
        logList.setAdapter(new ArrayAdapter<>(this, R.id.listview,strengthLog));
    }
}
