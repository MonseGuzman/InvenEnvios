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
        NetworkInfo tipoRed = connectivity.getActiveNetworkInfo();

        if(tipoRed != null)
        {
            if (tipoRed.getType() == ConnectivityManager.TYPE_MOBILE || tipoRed.getType() == ConnectivityManager.TYPE_WIFI)
                return true;
        }
        return false;
    }
}
