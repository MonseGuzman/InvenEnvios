package com.innovati.felipehernandez.invenenvios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity
{

    private Button btIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializacion();
    }

    private void inicializacion()
    {
        btIngresar = (Button)findViewById(R.id.btIngresar);
    }

    public void login(View v)
    {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }
}
