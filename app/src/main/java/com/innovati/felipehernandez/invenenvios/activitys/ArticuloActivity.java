package com.innovati.felipehernandez.invenenvios.activitys;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ArticuloAdapter;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwArticulosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwArticulos;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwArticulosDaoFactory;
import com.innovati.felipehernandez.invenenvios.fragments.ArticuloFragment;
import com.innovati.felipehernandez.invenenvios.fragments.ClienteFragment;

public class ArticuloActivity extends AppCompatActivity
{
    private ArticuloAdapter adaptador;
    private ListView articuloListView_A;
    private EditText buscarArticuloEditText_A;
    private FloatingActionButton AgregarFAB_A;

    VwArticulos result[];
    MetodosInternos metodosInternos = new MetodosInternos(this);
    String actividad;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulo);

        inicializacion();

        actividad = getIntent().getExtras().getString("actividad");

        if(actividad.equals("Pedidos"))
        {
            this.setTitle(R.string.tituloPedidos);
            AgregarFAB_A.setImageResource(R.drawable.ic_pedir);
        }
        else if(actividad.equals("Articulos"))
        {
            this.setTitle(R.string.tituloArticulo);
            AgregarFAB_A.setImageResource(R.drawable.ic_suma);
        }

        articuloListView_A.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ArticuloFragment fragment = new ArticuloFragment();
                Bundle args;

                if(actividad.equals("Articulo"))
                {
                    //DETALLE
                    args = new Bundle();
                    args.putString("fragmento", "Detalles");
                    fragment.setArguments(args);
                }
                else if(actividad.equals("Pedidos"))
                {
                    //AGREGAR ARTICULOS A PERDIDO
                    args = new Bundle();
                    args.putString("fragmento", "Agregar");
                    fragment.setArguments(args);
                    AgregarFAB_A.setImageResource(R.drawable.ic_agregar_carrito);
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.ArticuloConstraintLayout, fragment).addToBackStack(null).commit();
            }
        });

    }

    private void inicializacion()
    {
        articuloListView_A = (ListView)findViewById(R.id.articuloListView_A);
        buscarArticuloEditText_A = (EditText) findViewById(R.id.buscarArticuloEditText_A);
        AgregarFAB_A = (FloatingActionButton) findViewById(R.id.AgregarFAB_A);
    }

    @Override
    public void onBackPressed()
    {
        //cantidad de fragmentos que estan actualmente apilados.
        if(getSupportFragmentManager().getBackStackEntryCount() != 0)
        {
            super.onBackPressed();
            getSupportFragmentManager().popBackStack();

            if(actividad.equals("Pedidos"))
                AgregarFAB_A.setImageResource(R.drawable.ic_pedir);
            else if(actividad.equals("Articulos"))
                AgregarFAB_A.setImageResource(R.drawable.ic_suma);
        }
        else
            getSupportFragmentManager().popBackStack();
    }

    public static VwArticulosDao getVwArticulosDao()
    {
        return VwArticulosDaoFactory.create();
    }

    public void filtar(View v)
    {
        String nombre = buscarArticuloEditText_A.getText().toString();
        if(TextUtils.isEmpty(nombre))
        {
            if(metodosInternos.conexionRed())
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
                //código para buscar en la bd interna
            }
        }
        else
        {
            //todos los que se parecen con el where
            if(metodosInternos.conexionRed())
            {
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
            else
            {
                //código para buscar en la bd interna
            }
        }
    }
}
