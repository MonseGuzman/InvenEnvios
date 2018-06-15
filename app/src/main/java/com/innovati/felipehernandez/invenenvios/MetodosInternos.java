package com.innovati.felipehernandez.invenenvios;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

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

    public boolean validacion(String user, String pass)
    {
        if (user.isEmpty() && pass.isEmpty())
            return false;
        else if (user.isEmpty())
            return false;
        else if (pass.isEmpty())
            return false;

        return true;
    }

    public void Alerta(int titulo, int mensaje)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.setIcon(R.drawable.ic_informacion);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}
