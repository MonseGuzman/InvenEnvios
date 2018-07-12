package com.innovati.felipehernandez.invenenvios.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.innovati.felipehernandez.invenenvios.MetodosInternos;
import com.innovati.felipehernandez.invenenvios.R;
import com.innovati.felipehernandez.invenenvios.activitys.PedidoActivity;
import com.innovati.felipehernandez.invenenvios.adapters.ListaArticulosAdapter;
import com.innovati.felipehernandez.invenenvios.adapters.PedidosAdapter;
import com.innovati.felipehernandez.invenenvios.clases.dao.PedidosDao;
import com.innovati.felipehernandez.invenenvios.clases.dao.VwAbastecimientoDao;
import com.innovati.felipehernandez.invenenvios.clases.dto.Pedidos;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwAbastecimiento;
import com.innovati.felipehernandez.invenenvios.clases.dto.VwDetallePedido;
import com.innovati.felipehernandez.invenenvios.clases.factory.PedidosDaoFactory;
import com.innovati.felipehernandez.invenenvios.clases.factory.VwAbastecimientoDaoFactory;
import com.innovati.felipehernandez.invenenvios.fragments.DatosPedidoFragment;
import com.innovati.felipehernandez.invenenvios.fragments.DetallePedidoFragment;

public class AbastecimientosActivity extends AppCompatActivity {

    private ListView AbastecimientoListView;
    private RecyclerView recyclerView2;
    private Animation animationUp, animationDown;
    private MenuItem menu_cambia;

    private ListaArticulosAdapter listaArticulosAdapter;
    private VwAbastecimiento lista[];
    private boolean ban = false;
    private MetodosInternos metodosInternos = new MetodosInternos(this);

    private PedidosAdapter adaptador;
    Pedidos result[];
    int tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abastecimientos);

        inicializar();

        tipo = getIntent().getExtras().getInt("Tipo", 0);
        this.setTitle(R.string.tituloAbastecimiento);

        switch (tipo)
        {
            case 1:
                AbastecimientoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        DetallePedidoFragment datosPedidoFragment = new DetallePedidoFragment();
                        Bundle args;
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_regresar);
                        args = new Bundle();
                        args.putString("pedido", result[position].getIdPedido());
                        datosPedidoFragment.setArguments(args);
                        getSupportFragmentManager().beginTransaction().replace(R.id.abastecimientoConsulta, datosPedidoFragment).addToBackStack(null).commit();
                    }
                });
                break;
            case 2:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recyclerView2.setLayoutManager(linearLayoutManager);

                animationUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
                animationDown = AnimationUtils.loadAnimation(this, R.anim.slide_down);
                break;
        }

        cargarDatos();
    }

    private void inicializar()
    {
        AbastecimientoListView = (ListView)findViewById(R.id.AbastecimientoListView);
        recyclerView2 = (RecyclerView) findViewById(R.id.recycleView2);
        menu_cambia = (MenuItem) findViewById(R.id.menu_cambia);
    }

    private void cargarDatos()
    {
        if(metodosInternos.conexionRed())
        {
            switch (tipo)
            {
                case 1:
                    try
                    {
                        PedidosDao _dao = getPedidosDao();
                        result = _dao.findAll();
                        adaptador = new PedidosAdapter(this,  R.layout.listview_pedidos, result, 1);
                        AbastecimientoListView.setAdapter(adaptador);
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                    break;
                case 2:
                    try
                    {
                        VwAbastecimientoDao _dao = getVwAbastecimientoDao();
                        lista = _dao.findAll();

                        listaArticulosAdapter = new ListaArticulosAdapter(this, animationUp, animationDown, lista);
                        recyclerView2.setAdapter(listaArticulosAdapter);
                    }
                    catch(Exception e)
                    {
                        Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
        else
        {
            //bd interna
            metodosInternos.Alerta(R.string.error, R.string.errorBDInterna);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_abastecimiento, menu);
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
            case R.id.menu_cambia:
                if(!ban)
                {
                    menu_cambia.setIcon(R.drawable.ic_contenido); //da error aquí

                    recyclerView2.setVisibility(View.INVISIBLE);
                    AbastecimientoListView.setVisibility(View.VISIBLE);
                }
                else
                {
                    menu_cambia.setIcon(R.drawable.ic_detalle);

                    recyclerView2.setVisibility(View.VISIBLE);
                    AbastecimientoListView.setVisibility(View.INVISIBLE);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed()
    {
        //cantidad de fragmentos que estan actualmente apilados.
        if(getSupportFragmentManager().getBackStackEntryCount() != 0)
        {
            //regresa
            super.onBackPressed();
            //getSupportFragmentManager().popBackStack();
        }
        else
            finish();
    }

    public static VwAbastecimientoDao getVwAbastecimientoDao()
    {
        return VwAbastecimientoDaoFactory.create();
    }

    public static PedidosDao getPedidosDao()
    {
        return PedidosDaoFactory.create();
    }
}
