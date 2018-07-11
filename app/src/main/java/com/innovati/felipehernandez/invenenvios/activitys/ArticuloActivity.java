package com.innovati.felipehernandez.invenenvios.activitys;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
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
import com.innovati.felipehernandez.invenenvios.clases.dao.VwArticulosDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwArticulos;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwArticulosDaoFactory;
import com.innovati.felipehernandez.invenenvios.fragments.ArticuloFragment;
import com.innovati.felipehernandez.invenenvios.pojos.ArticulosPedido;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ArticuloActivity extends AppCompatActivity
{
    private ArticuloAdapter adaptador;
    private static ListView datitosListView;
    private static EditText buscarEditText;
    private static ImageButton BuscarImageButton;
    private FloatingActionButton AgregarFAB_A;

    private static ArrayList<ArticulosPedido> ListCarritoPedido = new ArrayList<>();
    ArticuloFragment fragment = new ArticuloFragment();
    VwArticulos result[];
    MetodosInternos metodosInternos = new MetodosInternos(this);
    Bundle args;
    private static boolean ban = false;
    static String fragmento = "";
    String nombre = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulo);

        inicializacion();

        this.setTitle(R.string.tituloArticulo);
        buscarEditText.setHint(R.string.seleccionarArticulos);

        datitosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ArticuloFragment fragment = new ArticuloFragment();

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

                //disableControl();

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
        nombre = buscarEditText.getText().toString();
        if(TextUtils.isEmpty(nombre))
        {
            if(metodosInternos.conexionRed())
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
                //código para buscar en la bd interna
                metodosInternos.Alerta(R.string.error, R.string.errorBDInterna);
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
                //código para buscar en la bd interna
                metodosInternos.Alerta(R.string.error, R.string.errorBDInterna);
            }
        }
    }

    private void cargarDatos(VwArticulos[] result)
    {
        adaptador = new ArticuloAdapter(this,  R.layout.listview_articulos, result);
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

     /*public static void disableLista(String s){
        ArticuloActivity.disableListaPedido(s);
    }
    public static void disableListaPedido(String s){
        datitosListView.setEnabled(true);
        buscarEditText.setEnabled(true);
        BuscarImageButton.setEnabled(true);
        fragmento = s;
    }
    private void disableControl(){
        datitosListView.setEnabled(false);
        buscarEditText.setEnabled(false);
        BuscarImageButton.setEnabled(false);
    }

   public static void addArticulo(ArticulosPedido a){
        if (ban){
            ArticuloActivity.addArticuloList(a);
        }

    }
    public static void addArticuloList(ArticulosPedido a){
        ListCarritoPedido.add(a);
    }*/

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
}
