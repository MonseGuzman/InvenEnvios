package com.innovati.felipehernandez.invenenvios.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ArticuloAdapter;
import com.innovati.felipehernandez.invenenvios.adapters.ClientesAdaptador;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwArticulosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwClientesDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwArticulos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwClientes;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwArticulosDaoFactory;

public class ArticuloActivity extends AppCompatActivity
{
    private ArticuloAdapter adaptador;
    private ListView articuloListView_A;
    private TextView buscarArticuloEditText_C;
    VwArticulos result[];


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulo);

        inicializacion();

    }

    private void inicializacion()
    {
        articuloListView_A = (ListView)findViewById(R.id.articuloListView_A);
        buscarArticuloEditText_C = findViewById(R.id.buscarArticuloEditText_C);
    }

    public static VwArticulosDao getVwArticulosDao()
    {
        return VwArticulosDaoFactory.create();
    }

    public void filtar(View v)
    {
        String nombre = buscarArticuloEditText_C.getText().toString();
        if(TextUtils.isEmpty(nombre))
        {
            //sin filtro = todos
            try
            {
                VwArticulosDao _dao = getVwArticulosDao();
                result = _dao.findAll();
                adaptador = new ArticuloAdapter(this,  R.layout.listview_articulos, result);
                articuloListView_A.setAdapter(adaptador);
            }
            catch(Exception e)
            {
                Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            //todos los que se parecen con el where
            try
            {
                nombre = "%" + nombre;
                nombre += "%";
                VwArticulosDao _dao = getVwArticulosDao();
                result = _dao.findWhereNombreEquals(nombre);
                adaptador = new ArticuloAdapter(this,  R.layout.listview_articulos, result);
                articuloListView_A.setAdapter(adaptador);
            }
            catch(Exception e)
            {
                Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
