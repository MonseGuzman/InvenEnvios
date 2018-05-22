package com.innovati.felipehernandez.invenenvios;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MetodosInternos
{
    private Context context;

    public MetodosInternos(Context context) {
        this.context = context;
    }

    public boolean conexionRed()
    {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info_wifi = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo info_datos = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (String.valueOf(info_wifi.getState()).equals("CONNECTED") || String.valueOf(info_datos.getState()).equals("CONNECTED"))
            return true;
        else
            return false;
    }
}
