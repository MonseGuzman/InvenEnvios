package com.innovati.felipehernandez.invenenvios.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
    String actividad, clave, nombre;
    Bundle args;
    private static boolean ban = false;
    static String fragmento = "";

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
            clave = getIntent().getExtras().getString("clave");
            nombre = getIntent().getExtras().getString("nombre");
        }
        else if(actividad.equals("Articulos"))
        {
            this.setTitle(R.string.tituloArticulo);
            AgregarFAB_A.setImageResource(R.drawable.ic_suma);
        }

        datitosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ArticuloFragment fragment = new ArticuloFragment();

                if(actividad.equals("Articulos"))
                {
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
                }
                else if(actividad.equals("Pedidos"))
                {
                    fragmento = "Detalles";
                    //AGREGAR ARTICULOS A PERDIDO
                    args = new Bundle();
                    args.putString("fragmento", "Detalles");
                    args.putString("clave", result[position].getClave());
                    args.putString("nombre", result[position].getNombre());
                    args.putString("activo", result[position].getActivo());
                    args.putDouble("tiempoSurtido", result[position].getTiempoSurtido());
                    args.putDouble("existencias", result[position].getExistenciaTotal());
                    args.putDouble("precio", result[position].getPrecio1());
                    args.putString("unidad", result[position].getUnidadPrimaria());
                    fragment.setArguments(args);
                    AgregarFAB_A.setImageResource(R.drawable.ic_agregar_carrito);
                    disableControl();
                }
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
            super.onBackPressed();
            getSupportFragmentManager().popBackStack();

            if(actividad.equals("Pedidos"))
                AgregarFAB_A.setImageResource(R.drawable.ic_pedir);
            else if(actividad.equals("Articulos"))
                AgregarFAB_A.setImageResource(R.drawable.ic_suma);
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
                    datitosListView.setAdapter(adaptador);
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
                    datitosListView.setAdapter(adaptador);
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

    public void pruebas(View v)
    {

        ban =false;
        if(actividad.equals("Articulos"))
        {
            //abre el fragmento sin nada ni nada
            args = new Bundle();
            args.putString("fragmento", "NuevoArticulo");
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction().replace(R.id.ArticuloConstraintLayout, fragment).addToBackStack(null).commit();
        }
       else if(actividad.equals("Pedidos"))
        {
            if(fragmento != "Detalles"){
                Gson gson = new Gson();
                Type type = new TypeToken<List<ArticulosPedido>>() {}.getType();
                String json = gson.toJson(ListCarritoPedido, type);
                Intent intent = new Intent(getBaseContext(), ArticulosPedidosActivity.class);
                intent.putExtra("articulos", json);
                intent.putExtra("nombre",nombre);
                intent.putExtra("clave",clave);
                startActivity(intent);
            }else{
                Toast.makeText(this,"si",Toast.LENGTH_SHORT).show();
                ban=true;
                getSupportFragmentManager().popBackStack();
            }

        }

    }
    public static void disableLista(String s){
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
    }
}
