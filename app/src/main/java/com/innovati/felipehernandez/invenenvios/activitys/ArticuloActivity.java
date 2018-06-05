package com.innovati.felipehernandez.invenenvios.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ArticuloAdapter;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwArticulosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwClientesDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwArticulos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwArticulosDaoFactory;

public class ArticuloActivity extends AppCompatActivity
{
    private ArticuloAdapter adaptador;
    private ListView articuloListView_A;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulo);

        inicializacion();
        VwArticulos result[] = null;
        try
        {
            VwArticulosDao _dao = getVwArticulosDao();
            result = _dao.findAll();
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }

        adaptador = new ArticuloAdapter(this, R.layout.listview_articulos, result);
        articuloListView_A.setAdapter(adaptador);
    }

    private void inicializacion()
    {
        articuloListView_A = (ListView)findViewById(R.id.articuloListView_A);
    }

    public static VwArticulosDao getVwArticulosDao()
    {
        return VwArticulosDaoFactory.create();
    }

}
