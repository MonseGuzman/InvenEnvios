package com.innovati.felipehernandez.invenenvios.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.innovati.felipehernandez.invenenvios.R;

public class MenuActivity extends AppCompatActivity
{
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.setTitle("Menú");
        preferences = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
    }


    public void botones(View v)
    {
        Intent i = new Intent();

        switch (v.getId())
        {
            case R.id.ArticulosButton:
                i = new Intent(this, ArticuloActivity.class);
                i.putExtra("actividad", "Articulos");
            break;
            case R.id.ClientesButton:
                i = new Intent(this, ClientesActivity.class);
                break;
            case R.id.PedidosButton:
                /*i = new Intent(this, ArticuloActivity.class);
                i.putExtra("actividad", "Pedidos");*/
                //i = new Intent(this, EntregasActivity.class);
                i = new Intent(this, ClientesActivity.class);
                i.putExtra("actividad", "PedidosCliente");
            break;
        }
        startActivity(i);
    }

    public void salir(View v)
    {
        preferences.edit().clear().apply();

        Intent i = new Intent(this, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //cierra esta actividad
        startActivity(i);
    }
}
