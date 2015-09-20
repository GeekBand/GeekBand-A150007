package com.geekband.tingyou.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

/**
 * This receiver is to detect the WIFI enable state change of the phone
 */
public class WifiChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            Toast.makeText(context, "WIFI enabled", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "WIFI disabled", Toast.LENGTH_SHORT).show();
        }
    }
}
