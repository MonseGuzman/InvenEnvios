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


    //              |NUM | TIPO
    //----------------------------------------
    //ABASTECIMIENTO|  1 | VISTA NORMAL
    //              |  2 | VISTA CON DETALLES
    public void botones(View v)
    {
        Intent i = new Intent();

        switch (v.getId())
        {
            case R.id.ArticulosButton:
                i = new Intent(this, ArticuloActivity.class);
                break;
            case R.id.ClientesButton:
                i = new Intent(this, ClientesActivity.class);
                break;
            case R.id.PedidosButton:
                i = new Intent(this, PedidoActivity.class);
                break;
            case R.id.AbastecimientoButton:
                i = new Intent(this, AbastecimientosActivity.class);
                i.putExtra("Tipo", 2);
                break;
            case R.id.EntregaButton:
                /*i = new Intent(this, AbastecimientosActivity.class);
                i.putExtra("Tipo", 2); //ENTREGA*/
                i = new Intent (this, AbastecimientoAhoraSiActivity.class);
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
