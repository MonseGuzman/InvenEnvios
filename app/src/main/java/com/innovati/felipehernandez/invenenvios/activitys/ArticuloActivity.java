package com.innovati.felipehernandez.invenenvios.activitys;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.adapters.ArticuloAdapter;
import com.innovati.felipehernandez.invenenvios.app.MyApp;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwArticulosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwArticulos;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwArticulosDaoFactory;
import com.innovati.felipehernandez.invenenvios.database.DaoSession;
import com.innovati.felipehernandez.invenenvios.database.VwArticulos_I;
import com.innovati.felipehernandez.invenenvios.database.VwArticulos_IDao;
import com.innovati.felipehernandez.invenenvios.fragments.ArticuloFragment;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class ArticuloActivity extends AppCompatActivity
{
    private ArticuloAdapter adaptador;
    private static ListView datitosListView;
    private static EditText buscarEditText;
    private static ImageButton BuscarImageButton;
    private FloatingActionButton AgregarFAB_A;

    VwArticulos result[];
    MetodosInternos metodosInternos = new MetodosInternos(this);
    Bundle args;
    static String fragmento = "";
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulo);

        inicializacion();

        this.setTitle(R.string.tituloArticulo);
        buscarEditText.setHint(R.string.seleccionarArticulos);
        daoSession = ((MyApp) getApplication()).getDaoSession();

        datitosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ArticuloFragment fragment = new ArticuloFragment();
                datitosListView.setVisibility(View.INVISIBLE);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_regresar);
                fragmento = "Agregar";
                //DETALLE
                args = new Bundle();
                args.putString("fragmento", "Agregar");
                args.putString("clave", result[position].getClave());
                args.putString("nombre", result[position].getNombre());
                args.putString("activo", result[position].getActivo());
                args.putDouble("tiempoSurtido", result[position].getTiempoSurtido());
                args.putDouble("existencias", result[position].getExistenciaTotal());
                args.putDouble("precio", result[position].getPrecio1());
                args.putString("unidad", result[position].getUnidadPrimaria());
                fragment.setArguments(args);


                getSupportFragmentManager().beginTransaction().replace(R.id.ArticuloConstraintLayout, fragment).addToBackStack(null).commit();
            }
        });

        BuscarImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtar(v);
            }
        });

    }

    private void inicializacion()
    {
        datitosListView = (ListView)findViewById(R.id.datitosListView);
        buscarEditText = (EditText) findViewById(R.id.buscarEditText);
        BuscarImageButton = (ImageButton) findViewById(R.id.BuscarImageButton);
        AgregarFAB_A = (FloatingActionButton) findViewById(R.id.AgregarFAB_A);
    }

    @Override
    public void onBackPressed()
    {
        //cantidad de fragmentos que estan actualmente apilados.
        if(getSupportFragmentManager().getBackStackEntryCount() != 0)
        {
            //regresa
            super.onBackPressed();
            getSupportFragmentManager().popBackStack();

            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            fragmento = "";
        }
        else
            finish();
    }

    public static VwArticulosDao getVwArticulosDao()
    {
        return VwArticulosDaoFactory.create();
    }

    public void filtar(View v)
    {
        String nombre = buscarEditText.getText().toString();
        if(metodosInternos.conexionRed())
        {
            if(TextUtils.isEmpty(nombre))
            {
                //sin filtro = todos
                try
                {
                    VwArticulosDao _dao = getVwArticulosDao();
                    ConsultaArticulos c = new ConsultaArticulos(nombre);
                    c.execute(_dao);
                }
                catch(Exception e)
                {
                    Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                try
                {
                    nombre = "%" + nombre;
                    nombre += "%";
                    VwArticulosDao _dao = getVwArticulosDao();
                    ConsultaArticulos c = new ConsultaArticulos(nombre);
                    c.execute(_dao);

                }
                catch(Exception e)
                {
                    Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
        else //código para buscar en la bd interna
            internaBD();
    }

    private void internaBD()
    {
        String nombre = buscarEditText.getText().toString();
        VwArticulos_IDao vwArticulosIDao = daoSession.getVwArticulos_IDao();

        //si esta vacio
        if(TextUtils.isEmpty(nombre))
        {
            metodosInternos.Alerta(R.string.error, R.string.errorBDInterna);
        }
        else
        {
            nombre = "%" + nombre;
            nombre += "%";

            QueryBuilder<VwArticulos_I> qb = vwArticulosIDao.queryBuilder();
            qb.where(VwArticulos_IDao.Properties.Nombre.like(nombre));

            List<VwArticulos_I> articulos = qb.list();
            result = new VwArticulos[articulos.size()];

            for(int x=0; x<articulos.size(); x++)
            {
                VwArticulos objetoArticulos = new VwArticulos();

                objetoArticulos.setClave(articulos.get(x).getClave());
                objetoArticulos.setNombre(articulos.get(x).getNombre());
                objetoArticulos.setStatus(articulos.get(x).getStatus());
                objetoArticulos.setTiempoSurtido(Float.parseFloat(articulos.get(x).getTiempoSurtido()));
                objetoArticulos.setExistenciaTotal(articulos.get(x).getExistenciaTotal());
                objetoArticulos.setPrecio1(articulos.get(x).getPrecio1());
                objetoArticulos.setUnidadPrimaria(articulos.get(x).getUnidadPrimaria());

                result[x] = objetoArticulos;
            }
        }

        cargarDatos(result);
    }

    private void cargarDatos(VwArticulos[] lista)
    {
        adaptador = new ArticuloAdapter(this,  R.layout.listview_articulos, lista);
        datitosListView.setAdapter(adaptador);
    }

    public void agregarFAB(View v)
    {
        Toast.makeText(this, "Agregar", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_home:
                finish();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

     public class ConsultaArticulos extends AsyncTask<VwArticulosDao, VwArticulos, VwArticulos[]>
     {
         String nombre;

         public ConsultaArticulos(String nombre)
         {
             this.nombre = nombre;
         }

         @Override
         protected VwArticulos[] doInBackground(VwArticulosDao... vwArticulosDaos)
         {
             try
             {
                 if(nombre.equals(""))
                     result = vwArticulosDaos[0].findAll();
                 else
                     result = vwArticulosDaos[0].findWhereNombreEquals(nombre);
             }
             catch (Exception e){

             }
             return result;
         }

         @Override
         protected void onPostExecute(VwArticulos[] vwArticulos)
         {
             super.onPostExecute(vwArticulos);
             cargarDatos(result);
         }
     }

     public static void blockeo(){
         datitosListView.setVisibility(View.VISIBLE);
     }
}
