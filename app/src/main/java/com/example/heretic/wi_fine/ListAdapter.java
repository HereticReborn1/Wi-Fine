package com.example.heretic.wi_fine;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.view.LayoutInflater;
import android.view.View;
import android.net.wifi.ScanResult;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List wifiList;

    ListAdapter(Context context1, List wifiList1)
    {
        this.context =context1;
        this.wifiList=wifiList1;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return wifiList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        System.out.println("viewpos" + position);
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.dataset, null);
            holder = new Holder();
            holder.tvDetails = view.findViewById(R.id.tvDetails);
            view.setTag(holder);
        }
        else
        {
            holder = (Holder) view.getTag();
        }
        ScanResult x = (ScanResult) wifiList.get(position);
        int level = WifiManager.calculateSignalLevel(x.level,4);
        String s = x.SSID + "\n" + "Strength: " + getStrength(level) + "\nBSSID: " + x.BSSID;

        holder.tvDetails.setText(s);
        return view;
    }

    private String getStrength(int level)
    {
        switch (level)
        {
            case 0: return "Dead";
            case 1: return "Weak";
            case 2: return "Fair";
            case 3: return "Strong";
            case 4: return "Excellent";
        }
        return "";
    }

    class Holder {
        TextView tvDetails;
    }
}
