package com.innovati.felipehernandez.invenenvios.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ArticuloAdapter;

public class ArticuloActivity extends AppCompatActivity
{
    private ArticuloAdapter adaptador;
    private ListView articuloListView_A;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulo);

        inicializacion();

        adaptador = new ArticuloAdapter(this, R.layout.listview_articulos);
        articuloListView_A.setAdapter(adaptador);
    }

    private void inicializacion()
    {
        articuloListView_A = (ListView)findViewById(R.id.articuloListView_A);
    }
}
