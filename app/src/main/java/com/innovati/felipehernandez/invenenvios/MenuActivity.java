package com.innovati.felipehernandez.invenenvios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.setTitle("Menú");
    }

    public void clientes(View v)
    {
        Intent i = new Intent(this, ClientesActivity.class);
        startActivity(i);
    }
}
