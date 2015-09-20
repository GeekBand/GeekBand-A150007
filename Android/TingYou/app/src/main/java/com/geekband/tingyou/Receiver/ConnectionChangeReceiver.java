package com.geekband.tingyou.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * This receiver is to detect the connection change of the phone, then sent the
 */
public class ConnectionChangeReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectionManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isAvailable()) {
            Toast.makeText(context, "Network is available", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Opps, network not available now", Toast.LENGTH_SHORT).show();
        }
    }
}
