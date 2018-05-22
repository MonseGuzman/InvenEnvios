package com.innovati.felipehernandez.invenenvios.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.innovati.felipehernandez.invenenvios.R;

public class MenuActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.setTitle("Menú");
    }


    public void botones(View v)
    {
        Intent i = new Intent();

        switch (v.getId())
        {
            case R.id.ArticulosImageButton:
                i = new Intent(this, ArticuloActivity.class);
            break;
            case R.id.ClientesImageButton:
                i = new Intent(this, ClientesActivity.class);
            break;

        }
        startActivity(i);
    }
}
